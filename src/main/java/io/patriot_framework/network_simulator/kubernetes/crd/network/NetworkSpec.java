package io.patriot_framework.network_simulator.kubernetes.crd.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents spec of the Network custom resource definition.
 */
@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NetworkSpec implements KubernetesResource {
    @JsonProperty("disableInsideIngressTraffic")
    private boolean disableInsideIngressTraffic;
    @JsonProperty("disableInsideEgressTraffic")
    private boolean disableInsideEgressTraffic;
    @JsonProperty("networkIngressRules")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ConnectionRules> networkIngressRules = new ArrayList<>();
    @JsonProperty("networkEgressRules")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ConnectionRules> networkEgressRules = new ArrayList<>();


    /**
     * Gets the value which is specifying if the inside ingress traffic is disabled or not
     *
     * @return true if the traffic is disabled, false otherwise
     */
    public boolean isDisableInsideIngressTraffic() {
        return disableInsideIngressTraffic;
    }

    /**
     * Sets the value which is specifying if the inside ingress traffic is disabled or not
     *
     * @param disableInsideIngressTraffic true if the traffic should be disabled, false otherwise
     */
    public void setDisableInsideIngressTraffic(boolean disableInsideIngressTraffic) {
        this.disableInsideIngressTraffic = disableInsideIngressTraffic;
    }

    /**
     * Gets the value which is specifying if the inside egress traffic is disabled or not
     *
     * @return true if the traffic is disabled, false otherwise
     */
    public boolean isDisableInsideEgressTraffic() {
        return disableInsideEgressTraffic;
    }

    /**
     * Sets the value which is specifying if the inside egress traffic is disabled or not
     *
     * @param disableInsideEgressTraffic true if the traffic should be disabled, false otherwise
     */
    public void setDisableInsideEgressTraffic(boolean disableInsideEgressTraffic) {
        this.disableInsideEgressTraffic = disableInsideEgressTraffic;
    }

    /**
     * Gets the list of network ingress rules
     *
     * @return list of the network ingress rules
     */
    public List<ConnectionRules> getNetworkIngressRules() {
        return networkIngressRules;
    }

    /**
     * Sets the list of network ingress rules
     *
     * @param networkIngressRules list of the network ingress rules
     */
    public void setNetworkIngressRules(List<ConnectionRules> networkIngressRules) {
        this.networkIngressRules = networkIngressRules;
    }

    /**
     * Gets the list of network egress rules
     *
     * @return list of the network egress rules
     */
    public List<ConnectionRules> getNetworkEgressRules() {
        return networkEgressRules;
    }

    /**
     * Sets the list of network egress rules
     *
     * @param networkEgressRules list of the network egress rules
     */
    public void setNetworkEgressRules(List<ConnectionRules> networkEgressRules) {
        this.networkEgressRules = networkEgressRules;
    }
}
