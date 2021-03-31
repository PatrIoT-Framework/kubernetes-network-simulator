package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.Ports;

import java.util.List;

public class PortBuilder {
    private Ports ports = new Ports();

    public Ports build() {
        return ports;
    }

    public PortBuilder withDeviceName(String deviceName) {
        ports.setDeviceName(deviceName);
        return this;
    }

    public PortBuilder withNetworkName(String networkName) {
        ports.setNetworkName(networkName);
        return this;
    }

    public PortBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        ports.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    public PortBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        ports.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
