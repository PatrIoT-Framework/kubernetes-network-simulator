package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;

import java.util.List;

/**
 * Class representing ConnectionRule builder.
 */
public class ConnectionRulesBuilder {
    private ConnectionRules connectionRules = new ConnectionRules();

    /**
     * Builds the ConnectionRule
     *
     * @return ConnectionRule
     */
    public ConnectionRules build() {
        return connectionRules;
    }

    /**
     * Adds device name to the ConnectionRule
     *
     * @param deviceName name of the device
     * @return instance of the builder
     */
    public ConnectionRulesBuilder withDeviceName(String deviceName) {
        connectionRules.setDeviceName(deviceName);
        return this;
    }

    /**
     * Adds network name to the ConnectionRule
     *
     * @param networkName name of the network
     * @return instance of the builder
     */
    public ConnectionRulesBuilder withNetworkName(String networkName) {
        connectionRules.setNetworkName(networkName);
        return this;
    }

    /**
     * Adds list of network policy ports to the ConnectionRule
     * {@link io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort}
     *
     * @param networkPolicyPorts list of the network policy ports
     * @return instance of the builder
     */
    public ConnectionRulesBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        connectionRules.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    /**
     * Adds single network policy port to the ConnectionRule
     * {@link io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort}
     *
     * @param networkPolicyPort network policy port, which will be added to the network policy ports
     * @return instance of the builder
     */
    public ConnectionRulesBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        connectionRules.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
