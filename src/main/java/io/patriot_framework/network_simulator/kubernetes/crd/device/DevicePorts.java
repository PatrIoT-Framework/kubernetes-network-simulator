package io.patriot_framework.network_simulator.kubernetes.crd.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;

@JsonDeserialize()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevicePorts {
    @JsonProperty("deviceName")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String deviceName;
    @JsonProperty("networkName")
    private String networkName;
    @JsonProperty("networkPolicyPorts")
    private NetworkPolicyPort[] networkPolicyPorts;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public NetworkPolicyPort[] getNetworkPolicyPorts() {
        return networkPolicyPorts;
    }

    public void setNetworkPolicyPorts(NetworkPolicyPort[] networkPolicyPorts) {
        this.networkPolicyPorts = networkPolicyPorts;
    }
}
