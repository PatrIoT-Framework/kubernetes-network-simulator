package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.exceptions.WaitTimeExceededException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Class that represents Kubernetes manager.
 * It implements the Manager interface and provides methods to work with Kubernetes API and Custom CRDs.
 */
public class KubernetesManager implements Manager {
    private final KubernetesClient kubernetesClient;
    private final CrdManager<NetworkCrd> networkCrdManager;
    private final CrdManager<DeviceCrd> deviceCrdManager;

    private static final long DEFAULT_WAIT_TIME = 300;

    /**
     * Constructor which creates the KubernetesManager with Config object.
     * {@link io.fabric8.kubernetes.client.Config}
     *
     * @param config config object which represents configuration for connecting to the Kubernetes API
     */
    public KubernetesManager(Config config) {
        this(new DefaultKubernetesClient(config));
    }

    /**
     * Constructor which creates the KubernetesManager with KubernetesClient instance.
     * {@link io.fabric8.kubernetes.client.KubernetesClient}
     *
     * @param client KubernetesClient connected to the Kubernetes API
     */
    public KubernetesManager(KubernetesClient client) {
        kubernetesClient = client;
        networkCrdManager = new NetworkCrdManager(kubernetesClient);
        deviceCrdManager = new DeviceCrdManager(kubernetesClient);
    }

    @Override
    public CrdManager<NetworkCrd> networkCrd() {
        return networkCrdManager;
    }

    @Override
    public CrdManager<DeviceCrd> deviceCrd() {
        return deviceCrdManager;
    }

    @Override
    public Pod getDevicePod(DeviceCrd deviceCrd) {
        return kubernetesClient
                .pods()
                .inNamespace(deviceCrd
                        .getSpec()
                        .getNetworkName())
                .withName(deviceCrd.podName())
                .get();
    }

    @Override
    public Service getDeviceService(DeviceCrd deviceCrd) {
        return kubernetesClient
                .services()
                .inNamespace(deviceCrd
                        .getSpec()
                        .getNetworkName())
                .withName(deviceCrd.serviceName())
                .get();
    }

    @Override
    public ConfigMap createConfigMap(Map<String, String> data, String namespace, String name) {
        return kubernetesClient
                .configMaps()
                .inNamespace(namespace)
                .create(new ConfigMapBuilder()
                        .addToData(data)
                        .withNewMetadata()
                        .withName(name)
                        .endMetadata()
                        .build());
    }

    @Override
    public boolean deleteConfigMap(String namespace, String name) {
        return kubernetesClient
                .configMaps()
                .inNamespace(namespace)
                .withName(name)
                .delete();
    }

    @Override
    public void waitPodCreation(DeviceCrd deviceCrd) throws WaitTimeExceededException {
        try {
            kubernetesClient
                    .pods()
                    .inNamespace(deviceCrd.getSpec().getNetworkName())
                    .withName(deviceCrd.podName())
                    .waitUntilReady(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new WaitTimeExceededException(e.getMessage());
        }
    }
}
