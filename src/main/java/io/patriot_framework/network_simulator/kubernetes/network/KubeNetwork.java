package io.patriot_framework.network_simulator.kubernetes.network;

import io.patriot_framework.network.simulator.api.model.network.Network;

public class KubeNetwork extends Network {
    public KubeNetwork(String name) {
        setName(name);
    }

    @Override
    public String getCreator() {
        return null;
    }
}
