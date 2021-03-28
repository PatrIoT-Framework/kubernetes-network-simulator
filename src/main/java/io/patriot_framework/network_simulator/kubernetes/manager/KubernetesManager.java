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

public class KubernetesManager implements Manager {
    private KubernetesClient kubernetesClient;
    private CrdManager<NetworkCrd> networkCrdManager;
    private CrdManager<DeviceCrd> deviceCrdManager;

    private static final long DEFAULT_WAIT_TIME = 300;

    public KubernetesManager(Config config) {
        this(new DefaultKubernetesClient(config));
    }

    public KubernetesManager(KubernetesClient client) {
        kubernetesClient = client;
        networkCrdManager = new NetworkCrdManager(kubernetesClient);
        deviceCrdManager = new DeviceCrdManager(kubernetesClient);
    }

    public CrdManager<NetworkCrd> networkCrd() {
        return networkCrdManager;
    }

    public CrdManager<DeviceCrd> deviceCrd() {
        return deviceCrdManager;
    }

    public Pod getDevicePod(DeviceCrd deviceCrd) {
        return kubernetesClient
                .pods()
                .inNamespace(deviceCrd
                        .getSpec()
                        .getNetworkName())
                .withName(deviceCrd.podName())
                .get();
    }

    public Service getDeviceService(DeviceCrd deviceCrd) {
        return kubernetesClient
                .services()
                .inNamespace(deviceCrd
                        .getSpec()
                        .getNetworkName())
                .withName(deviceCrd.serviceName())
                .get();
    }

    public ConfigMap createConfigMap(Map<String, String> data, String network, String name) {
        return kubernetesClient
                .configMaps()
                .inNamespace(network)
                .create(new ConfigMapBuilder()
                        .addToData(data)
                        .withNewMetadata()
                        .withName(name)
                        .endMetadata()
                        .build());
    }


    public boolean deleteConfigMap(String namespace, String name) {
        return kubernetesClient
                .configMaps()
                .inNamespace(namespace)
                .withName(name)
                .delete();
    }

    public void waitPodCreation(DeviceCrd deviceCrd) {
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
