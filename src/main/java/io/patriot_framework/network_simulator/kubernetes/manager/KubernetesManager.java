package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkList;

public class KubernetesManager implements Manager {
    private KubernetesClient kubernetesClient;
    private NonNamespaceOperation<NetworkCrd, NetworkList, Resource<NetworkCrd>> networkCrdClient;

    public KubernetesManager() {
        kubernetesClient = new DefaultKubernetesClient();
        networkCrdClient = kubernetesClient.customResources(NetworkCrd.class, NetworkList.class);
    }

    public NetworkCrd createNetwork(NetworkCrd networkCrd) {
        return networkCrdClient.create(networkCrd);
    }

    public NetworkCrd replaceNetwork(NetworkCrd networkCrd) {
        return networkCrdClient.replace(networkCrd);
    }

    public boolean deleteNetwork(NetworkCrd networkCrd) {
        return networkCrdClient.delete(networkCrd);
    }
}
