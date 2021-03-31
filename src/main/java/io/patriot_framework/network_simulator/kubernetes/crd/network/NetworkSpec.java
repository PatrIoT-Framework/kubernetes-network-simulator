package io.patriot_framework.network_simulator.kubernetes.crd.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.patriot_framework.network_simulator.kubernetes.crd.Ports;

import java.util.ArrayList;
import java.util.List;

public class NetworkSpec implements KubernetesResource {
    @JsonProperty("disableInsideIngressTraffic")
    private boolean disableInsideIngressTraffic;
    @JsonProperty("disableInsideEgressTraffic")
    private boolean disableInsideEgressTraffic;
    @JsonProperty("networkIngressPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Ports> networkIngressPorts = new ArrayList<>();
    @JsonProperty("networkEgressPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Ports> networkEgressPorts = new ArrayList<>();


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

    public List<Ports> getNetworkIngressPorts() {
        return networkIngressPorts;
    }

    public void setNetworkIngressPorts(List<Ports> networkIngressPorts) {
        this.networkIngressPorts = networkIngressPorts;
    }

    public List<Ports> getNetworkEgressPorts() {
        return networkEgressPorts;
    }

    public void setNetworkEgressPorts(List<Ports> networkEgressPorts) {
        this.networkEgressPorts = networkEgressPorts;
    }
}
