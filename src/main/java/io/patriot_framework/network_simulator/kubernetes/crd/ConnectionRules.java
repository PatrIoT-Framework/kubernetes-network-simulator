package io.patriot_framework.network_simulator.kubernetes.crd;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing connection rule between Network/Device and another Network/Device.
 */
@JsonDeserialize()
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionRules {
    @JsonProperty("deviceName")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String deviceName;
    @JsonProperty("networkName")
    private String networkName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("networkPolicyPorts")
    private List<NetworkPolicyPort> networkPolicyPorts = new ArrayList<>();

    /**
     * Gets the device name
     *
     * @return device name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the device name
     *
     * @param deviceName device name to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * Gets the network name
     *
     * @return network name
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Sets the network name
     *
     * @param networkName network name to set
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * Gets the list of network policy ports
     * {@link io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort}
     *
     * @return network policy ports
     */
    public List<NetworkPolicyPort> getNetworkPolicyPorts() {
        return networkPolicyPorts;
    }

    /**
     * Sets the list of network policy ports
     * {@link io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort}
     *
     * @param networkPolicyPorts list of the network policy ports to set
     */
    public void setNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        this.networkPolicyPorts = networkPolicyPorts;
    }
}
