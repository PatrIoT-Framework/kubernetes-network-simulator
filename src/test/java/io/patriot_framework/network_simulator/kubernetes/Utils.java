package io.patriot_framework.network_simulator.kubernetes;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;

public class Utils {

    public static Namespace getNamespaceByName(KubernetesClient client, String name) {
        return client
                .namespaces()
                .list()
                .getItems()
                .stream()
                .filter(ns -> ns
                        .getMetadata()
                        .getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }
}
