package io.patriot_framework.network_simulator.kubernetes.crd.device.builders;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DevicePort;

import java.util.List;

public class DevicePortBuilder {
    private DevicePort devicePort = new DevicePort();

    public DevicePort build() {
        return devicePort;
    }

    public DevicePortBuilder withDeviceName(String deviceName) {
        devicePort.setDeviceName(deviceName);
        return this;
    }

    public DevicePortBuilder withNetworkName(String networkName) {
        devicePort.setNetworkName(networkName);
        return this;
    }

    public DevicePortBuilder withNetworkPolicyPorts(List<NetworkPolicyPort> networkPolicyPorts) {
        devicePort.setNetworkPolicyPorts(networkPolicyPorts);
        return this;
    }

    public DevicePortBuilder withNetworkPolicyPort(NetworkPolicyPort networkPolicyPort) {
        devicePort.getNetworkPolicyPorts().add(networkPolicyPort);
        return this;
    }
}
