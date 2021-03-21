package io.patriot_framework.network_simulator.kubernetes.crd.device.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DevicePorts;

import java.util.List;

public class DevicePortsBuilder {
    private DevicePorts devicePorts = new DevicePorts();

    public DevicePorts build() {
        return devicePorts;
    }

    public DevicePortsBuilder withDeviceName(String deviceName) {
        devicePorts.setDeviceName(deviceName);
        return this;
    }

    public DevicePortsBuilder withNetworkName(String networkName) {
        devicePorts.setNetworkName(networkName);
        return this;
    }

    public DevicePortsBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        devicePorts.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    public DevicePortsBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        devicePorts.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
