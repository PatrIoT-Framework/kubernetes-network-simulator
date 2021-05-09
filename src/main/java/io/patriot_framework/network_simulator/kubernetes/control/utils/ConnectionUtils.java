package io.patriot_framework.network_simulator.kubernetes.control.utils;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;
import io.patriot_framework.network_simulator.kubernetes.crd.builders.ConnectionRulesBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;

import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {

    public static void connectNetworkToDevice(NetworkCrd source, DeviceCrd target) {
        connectNetworkToDevice(source, target, new ArrayList<>(), new ArrayList<>());
    }

    public static void connectNetworkToDevice(NetworkCrd source, DeviceCrd target,
                                              List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getNetworkEgressRules()
                .add(buildPorts(target.getMetadata().getName(),
                        target.getSpec().getNetworkName(),
                        targetPorts));

        target.getSpec()
                .getDeviceIngressRules()
                .add(buildPorts(null,
                        source.getMetadata().getName(),
                        sourcePorts));
    }


    public static void connectDeviceToNetwork(DeviceCrd source, NetworkCrd target) {
        connectDeviceToNetwork(source, target, new ArrayList<>(), new ArrayList<>());
    }

    public static void connectDeviceToNetwork(DeviceCrd source, NetworkCrd target,
                                              List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getDeviceEgressRules()
                .add(buildPorts(null,
                        target.getMetadata().getName(),
                        targetPorts));

        target.getSpec()
                .getNetworkIngressRules()
                .add(buildPorts(source.getMetadata().getName(),
                        source.getSpec().getNetworkName(),
                        sourcePorts));
    }


    public static void connectNetworks(NetworkCrd source, NetworkCrd target) {
        connectNetworks(source, target, new ArrayList<>(), new ArrayList<>());
    }

    public static void connectNetworks(NetworkCrd source, NetworkCrd target,
                                       List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getNetworkEgressRules()
                .add(buildPorts(null,
                        target.getMetadata().getName(),
                        targetPorts));
        target.getSpec()
                .getNetworkIngressRules()
                .add(buildPorts(null,
                        source.getMetadata().getName(),
                        sourcePorts));
    }


    public static void connectDevices(DeviceCrd source, DeviceCrd target) {
        connectDevices(source, target, new ArrayList<>(), new ArrayList<>());
    }


    public static void connectDevices(DeviceCrd source, DeviceCrd target,
                                      List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getDeviceEgressRules()
                .add(buildPorts(target.getMetadata().getName(),
                        target.getSpec().getNetworkName(),
                        targetPorts));
        target.getSpec()
                .getDeviceIngressRules()
                .add(buildPorts(source.getMetadata().getName(),
                        source.getSpec().getNetworkName(),
                        sourcePorts));
    }


    private static ConnectionRules buildPorts(String deviceName, String networkName, List<NetworkPolicyPort> targetPorts) {
        return new ConnectionRulesBuilder()
                .withDeviceName(deviceName)
                .withNetworkName(networkName)
                .withNetworkPolicyPorts(targetPorts)
                .build();
    }
}
