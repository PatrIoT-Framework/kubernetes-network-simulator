package io.patriot_framework.network_simulator.kubernetes.network;


/**
 * Class representing simulated network in the kubernetes network simulation.
 * It contains all necessary attributes to define simulated network.
 */
public class KubeNetwork {
    private String name;
    private boolean disableInsideIngressTraffic;
    private boolean disableInsideEgressTraffic;

    /**
     * Constructor with single parameter.
     * @param name name of the network
     */
    public KubeNetwork(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name network name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets boolean value if the inside ingress traffic is disabled.
     *
     * @return value if the inside ingress traffic is disabled
     */
    public boolean isDisableInsideIngressTraffic() {
        return disableInsideIngressTraffic;
    }

    /**
     * Sets boolean value if the inside ingress traffic is disabled.
     *
     * @param disableInsideIngressTraffic the value if the inside ingress traffic is disabled
     */
    public void setDisableInsideIngressTraffic(boolean disableInsideIngressTraffic) {
        this.disableInsideIngressTraffic = disableInsideIngressTraffic;
    }

    /**
     * Gets boolean value if the inside egress traffic is disabled.
     *
     * @return value if the inside egress traffic is disabled
     */
    public boolean isDisableInsideEgressTraffic() {
        return disableInsideEgressTraffic;
    }

    /**
     * Sets boolean value if the inside egress traffic is disabled.
     *
     * @param disableInsideEgressTraffic the value if the inside egress traffic is disabled
     */
    public void setDisableInsideEgressTraffic(boolean disableInsideEgressTraffic) {
        this.disableInsideEgressTraffic = disableInsideEgressTraffic;
    }
}
