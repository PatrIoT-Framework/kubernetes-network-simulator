package io.patriot_framework.network_simulator.kubernetes.crd.device.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.Ports;

import java.util.List;

public class DevicePortBuilder {
    private Ports ports = new Ports();

    public Ports build() {
        return ports;
    }

    public DevicePortBuilder withDeviceName(String deviceName) {
        ports.setDeviceName(deviceName);
        return this;
    }

    public DevicePortBuilder withNetworkName(String networkName) {
        ports.setNetworkName(networkName);
        return this;
    }

    public DevicePortBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        ports.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    public DevicePortBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        ports.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
