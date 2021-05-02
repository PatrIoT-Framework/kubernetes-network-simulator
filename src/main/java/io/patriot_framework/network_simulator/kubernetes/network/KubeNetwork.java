package io.patriot_framework.network_simulator.kubernetes.network;


public class KubeNetwork {
    private String name;

    public KubeNetwork(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
