package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkList;

public class NetworkCrdManager implements CrdManager<NetworkCrd> {
    private KubernetesClient client;
    private NonNamespaceOperation<NetworkCrd, NetworkList, Resource<NetworkCrd>> networkCrdClient;

    public NetworkCrdManager(KubernetesClient client) {
        this.client = client;
        networkCrdClient = client.customResources(NetworkCrd.class, NetworkList.class);
    }

    @Override
    public NetworkCrd create(NetworkCrd crd) {
        return networkCrdClient.create(crd);
    }

    @Override
    public NetworkCrd update(NetworkCrd crd) {
        return networkCrdClient.replace(crd);
    }

    @Override
    public boolean delete(NetworkCrd crd) {
        return networkCrdClient.delete(crd);
    }

    @Override
    public boolean delete(String name) {
        return delete(get(name));
    }

    @Override
    public boolean deleteAll() {
        return networkCrdClient.delete(networkCrdClient.list().getItems());
    }

    @Override
    public NetworkCrd get(String name) {
        return networkCrdClient
                .withName(name)
                .get();
    }
}
