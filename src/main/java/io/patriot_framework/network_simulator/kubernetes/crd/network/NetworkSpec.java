package io.patriot_framework.network_simulator.kubernetes.crd.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.fabric8.kubernetes.api.model.KubernetesResource;

public class NetworkSpec implements KubernetesResource {
    @JsonProperty("disableInsideIngressTraffic")
    private boolean disableInsideIngressTraffic;
    @JsonProperty("disableInsideEgressTraffic")
    private boolean disableInsideEgressTraffic;

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
