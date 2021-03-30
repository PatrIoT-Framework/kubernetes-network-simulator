package io.patriot_framework.network_simulator.kubernetes.crd.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;

import java.util.ArrayList;
import java.util.List;

@JsonDeserialize()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevicePort {
    @JsonProperty("deviceName")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String deviceName;
    @JsonProperty("networkName")
    private String networkName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("networkPolicyPorts")
    private List<NetworkPolicyPort> networkPolicyPorts = new ArrayList<>();

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

    public List<NetworkPolicyPort> getNetworkPolicyPorts() {
        return networkPolicyPorts;
    }

    public void setNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        this.networkPolicyPorts = networkPolicyPorts;
    }
}
