package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;

public class KubernetesManager implements Manager {
    private KubernetesClient kubernetesClient;
    private CrdManager<NetworkCrd> networkCrdManager;

    public KubernetesManager(Config config) {
        kubernetesClient = new DefaultKubernetesClient(config);
        networkCrdManager = new NetworkCrdManager(kubernetesClient);
    }

    public CrdManager<NetworkCrd> networkCrd() {
        return networkCrdManager;
    }
}
