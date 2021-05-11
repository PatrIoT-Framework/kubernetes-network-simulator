package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkList;

/**
 * Class representing custom resource of simulated network.
 */
public class NetworkCrdManager implements CrdManager<NetworkCrd> {
    private final NonNamespaceOperation<NetworkCrd, NetworkList, Resource<NetworkCrd>> networkCrdClient;

    /**
     * Constructor for NetworkCrdManager
     *
     * @param client instance of KubernetesClient
     */
    public NetworkCrdManager(KubernetesClient client) {
        networkCrdClient = client.customResources(NetworkCrd.class, NetworkList.class);
    }

    @Override
    public NetworkCrd create(NetworkCrd crd) {
        return networkCrdClient.create(crd);
    }

    @Override
    public NetworkCrd update(NetworkCrd crd) {
        return networkCrdClient.createOrReplace(crd);
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
