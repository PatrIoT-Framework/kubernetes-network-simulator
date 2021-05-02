package io.patriot_framework.network_simulator.kubernetes.crd.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;

import java.util.ArrayList;
import java.util.List;

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

    public List<ConnectionRules> getNetworkIngressRules() {
        return networkIngressRules;
    }

    public void setNetworkIngressRules(List<ConnectionRules> networkIngressRules) {
        this.networkIngressRules = networkIngressRules;
    }

    public List<ConnectionRules> getNetworkEgressRules() {
        return networkEgressRules;
    }

    public void setNetworkEgressRules(List<ConnectionRules> networkEgressRules) {
        this.networkEgressRules = networkEgressRules;
    }
}
