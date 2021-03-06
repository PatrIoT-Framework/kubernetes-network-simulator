package io.patriot_framework.network_simulator.kubernetes.control;


import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.extensions.IPBlockBuilder;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyIngressRuleBuilder;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPeerBuilder;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicySpec;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicySpecBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.patriot_framework.network_simulator.kubernetes.control.utils.ConnectionUtils;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkSpec;
import io.patriot_framework.network_simulator.kubernetes.device.DeviceConfigPort;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.exceptions.KubernetesSimulationException;
import io.patriot_framework.network_simulator.kubernetes.exceptions.WaitTimeExceededException;
import io.patriot_framework.network_simulator.kubernetes.manager.KubernetesManager;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents implementation of the Controller,
 * which is responsible to provide methods to work with simulated network.
 */
public class KubernetesController implements Controller {
    private final KubernetesManager kubernetesManager;

    /**
     * Constructor for KubernetesController
     *
     * @param kubernetesManager KubernetesManager which is used by the controller
     */
    public KubernetesController(KubernetesManager kubernetesManager) {
        this.kubernetesManager = kubernetesManager;
    }


    /**
     * Constructor for KubernetesController
     *
     * @param client KubernetesClient which is used by the controller
     */
    public KubernetesController(KubernetesClient client) {
        this.kubernetesManager = new KubernetesManager(client);
    }

    @Override
    public void createNetwork(KubeNetwork network) {
        NetworkCrd networkCrd = new NetworkCrd();
        NetworkSpec spec = new NetworkSpec();
        spec.setDisableInsideEgressTraffic(network.isDisableInsideEgressTraffic());
        spec.setDisableInsideIngressTraffic(network.isDisableInsideIngressTraffic());

        networkCrd.getMetadata().setName(network.getName());
        networkCrd.setSpec(spec);
        kubernetesManager.networkCrd().create(networkCrd);
    }

    @Override
    public void destroyNetwork(KubeNetwork network) {
        kubernetesManager.networkCrd().delete(network.getName());

    }

    @Override
    public void deployDevice(KubeDevice device) throws KubernetesSimulationException {
        device.finalizeConfiguration();

        ConfigMap configMap = null;
        if (device.getDeviceConfig().getFilesConfiguration().size() != 0) {
            configMap = kubernetesManager.createConfigMap(device.getDeviceConfig().getFilesConfiguration(),
                    device.getNetwork().getName(),
                    deviceConfigMapName(device));
        }

        DeviceCrd deviceCrd = DeviceConverter.deviceToCrd(device, configMap);
        kubernetesManager.deviceCrd().create(deviceCrd);
        try {
            kubernetesManager.waitPodCreation(deviceCrd);
        } catch (WaitTimeExceededException e) {
            throw new KubernetesSimulationException(e.getMessage(), e.getCause());
        }
        updateDeviceInformationFromKubernetes(device, deviceCrd);

    }

    @Override
    public void destroyDevice(KubeDevice device) {
        kubernetesManager.deviceCrd().delete(device.getName());
        kubernetesManager.deleteConfigMap(device.getNetwork().getName(), deviceConfigMapName(device));

    }

    @Override
    public void deviceIsSeenBy(KubeDevice device, String cidr) {
        DeviceCrd deviceCrd = kubernetesManager.deviceCrd().get(device.getName());

        NetworkPolicySpec spec = new NetworkPolicySpecBuilder()
                .withPolicyTypes("Ingress")
                .withIngress(new NetworkPolicyIngressRuleBuilder()
                        .withFrom(new NetworkPolicyPeerBuilder()
                                .withIpBlock(new IPBlockBuilder()
                                        .withCidr(cidr)
                                        .build())
                                .build())
                        .build())
                .build();
        updateDeviceNetworkPolicy(deviceCrd, spec);

        kubernetesManager.deviceCrd().update(deviceCrd);
    }

    @Override
    public void connectDevicesOneWay(KubeDevice source, KubeDevice target) {
        DeviceCrd sourceCrd = kubernetesManager.deviceCrd().get(source.getName());
        DeviceCrd targetCrd = kubernetesManager.deviceCrd().get(target.getName());

        ConnectionUtils.connectDevices(sourceCrd, targetCrd);

        kubernetesManager.deviceCrd().update(sourceCrd);
        kubernetesManager.deviceCrd().update(targetCrd);
    }

    @Override
    public void connectDevicesBothWays(KubeDevice device, KubeDevice device2) {
        DeviceCrd firstCrd = kubernetesManager.deviceCrd().get(device.getName());
        DeviceCrd secondCrd = kubernetesManager.deviceCrd().get(device2.getName());

        ConnectionUtils.connectDevices(firstCrd, secondCrd);
        ConnectionUtils.connectDevices(secondCrd, firstCrd);

        kubernetesManager.deviceCrd().update(firstCrd);
        kubernetesManager.deviceCrd().update(secondCrd);

    }

    @Override
    public void connectNetworksBothWays(KubeNetwork network, KubeNetwork network2) {
        NetworkCrd firstCrd = kubernetesManager.networkCrd().get(network.getName());
        NetworkCrd secondCrd = kubernetesManager.networkCrd().get(network2.getName());

        ConnectionUtils.connectNetworks(firstCrd, secondCrd);
        ConnectionUtils.connectNetworks(secondCrd, firstCrd);

        kubernetesManager.networkCrd().update(firstCrd);
        kubernetesManager.networkCrd().update(secondCrd);
    }

    @Override
    public void connectDeviceToNetworkOneWay(KubeDevice device, KubeNetwork network) {
        DeviceCrd deviceCrd = kubernetesManager.deviceCrd().get(device.getName());
        NetworkCrd networkCrd = kubernetesManager.networkCrd().get(network.getName());

        ConnectionUtils.connectDeviceToNetwork(deviceCrd, networkCrd);

        kubernetesManager.deviceCrd().update(deviceCrd);
        kubernetesManager.networkCrd().update(networkCrd);
    }

    @Override
    public void connectDeviceToNetworkBothWays(KubeDevice device, KubeNetwork network) {
        DeviceCrd deviceCrd = kubernetesManager.deviceCrd().get(device.getName());
        NetworkCrd networkCrd = kubernetesManager.networkCrd().get(network.getName());

        ConnectionUtils.connectDeviceToNetwork(deviceCrd, networkCrd);
        ConnectionUtils.connectNetworkToDevice(networkCrd, deviceCrd);

        kubernetesManager.deviceCrd().update(deviceCrd);
        kubernetesManager.networkCrd().update(networkCrd);
    }

    @Override
    public void connectNetworkToDeviceOneWay(KubeNetwork source, KubeDevice target) {
        NetworkCrd networkCrd = kubernetesManager.networkCrd().get(source.getName());
        DeviceCrd deviceCrd = kubernetesManager.deviceCrd().get(target.getName());

        ConnectionUtils.connectNetworkToDevice(networkCrd, deviceCrd);

        kubernetesManager.networkCrd().update(networkCrd);
        kubernetesManager.deviceCrd().update(deviceCrd);
    }


    private void updateDeviceInformationFromKubernetes(KubeDevice device, DeviceCrd deviceCrd) {
        Pod pod = kubernetesManager.getDevicePod(deviceCrd);
        Service service = kubernetesManager.getDeviceService(deviceCrd);
        device.setPublicIpAddress(pod.getStatus().getHostIP());
        device.setPrivateIpAddress(pod.getStatus().getPodIP());

        service.getSpec()
                .getPorts()
                .stream()
                .filter(port -> port
                        .getPort()
                        .equals(device
                                .getDeviceConfig()
                                .getManagementPort()
                                .getPort()))
                .findFirst()
                .ifPresent(servicePort -> device
                        .setManagementPort(new DeviceConfigPort(servicePort.getNodePort(), servicePort.getProtocol())));

    }

    private String deviceConfigMapName(KubeDevice device) {
        return device.getName() + "-config-map";
    }

    private void updateDeviceNetworkPolicy(DeviceCrd deviceCrd, NetworkPolicySpec networkPolicySpec) {
        if (deviceCrd.getSpec().getNetworkPolicySpec() == null) {
            deviceCrd.getSpec().setNetworkPolicySpec(new NetworkPolicySpec());
            deviceCrd.getSpec().getNetworkPolicySpec().setPodSelector(new LabelSelector());
        }

        NetworkPolicySpec existingNP = deviceCrd.getSpec().getNetworkPolicySpec();
        Set<String> types = new HashSet<>(existingNP.getPolicyTypes());
        types.addAll(networkPolicySpec.getPolicyTypes());
        existingNP.setPolicyTypes(new ArrayList<>(types));

        existingNP.getEgress().addAll(networkPolicySpec.getEgress());
        existingNP.getIngress().addAll(networkPolicySpec.getIngress());
    }
}
