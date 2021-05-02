package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;

import java.util.List;

public class PortBuilder {
    private ConnectionRules connectionRules = new ConnectionRules();

    public ConnectionRules build() {
        return connectionRules;
    }

    public PortBuilder withDeviceName(String deviceName) {
        connectionRules.setDeviceName(deviceName);
        return this;
    }

    public PortBuilder withNetworkName(String networkName) {
        connectionRules.setNetworkName(networkName);
        return this;
    }

    public PortBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        connectionRules.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    public PortBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        connectionRules.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
