package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;

import java.util.List;

public class ConnectionRulesBuilder {
    private ConnectionRules connectionRules = new ConnectionRules();

    public ConnectionRules build() {
        return connectionRules;
    }

    public ConnectionRulesBuilder withDeviceName(String deviceName) {
        connectionRules.setDeviceName(deviceName);
        return this;
    }

    public ConnectionRulesBuilder withNetworkName(String networkName) {
        connectionRules.setNetworkName(networkName);
        return this;
    }

    public ConnectionRulesBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        connectionRules.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    public ConnectionRulesBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        connectionRules.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
