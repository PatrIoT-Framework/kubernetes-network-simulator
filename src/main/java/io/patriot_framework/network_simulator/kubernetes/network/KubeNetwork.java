package io.patriot_framework.network_simulator.kubernetes.network;


public class KubeNetwork {
    private String name;
    private boolean disableInsideIngressTraffic;

    private boolean disableInsideEgressTraffic;

    public KubeNetwork(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisableInsideIngressTraffic() {
        return disableInsideIngressTraffic;
    }

    public void setDisableInsideIngressTraffic(boolean disableInsideIngressTraffic) {
        this.disableInsideIngressTraffic = disableInsideIngressTraffic;
    }

    public boolean isDisableInsideEgressTraffic() {
        return disableInsideEgressTraffic;
    }

    public void setDisableInsideEgressTraffic(boolean disableInsideEgressTraffic) {
        this.disableInsideEgressTraffic = disableInsideEgressTraffic;
    }
}
