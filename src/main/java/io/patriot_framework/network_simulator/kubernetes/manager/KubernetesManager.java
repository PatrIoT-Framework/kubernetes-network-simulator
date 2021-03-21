package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;

public class KubernetesManager implements Manager {
    private KubernetesClient kubernetesClient;
    private CrdManager<NetworkCrd> networkCrdManager;
    private CrdManager<DeviceCrd> deviceCrdManager;

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
}
