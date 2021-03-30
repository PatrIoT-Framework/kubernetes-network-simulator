package io.patriot_framework.network_simulator.kubernetes.crd.device.builders;

import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DevicePort;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceSpec;

import java.util.List;

public class DeviceSpecBuilder {
    private DeviceSpec deviceSpec;

    public DeviceSpecBuilder() {
        deviceSpec = new DeviceSpec();
    }

    public DeviceSpec build() {
        return deviceSpec;
    }

    public DeviceSpecBuilder withNetworkName(String networkName) {
        deviceSpec.setNetworkName(networkName);
        return this;
    }

    public DeviceSpecBuilder withPodSpec(PodSpec podSpec) {
        deviceSpec.setPodSpec(podSpec);
        return this;
    }

    public DeviceSpecBuilder withServiceSpec(ServiceSpec serviceSpec) {
        deviceSpec.setServiceSpec(serviceSpec);
        return this;
    }

    public DeviceSpecBuilder withDeviceIngressPorts(List<DevicePort> deviceIngressPorts) {
        deviceSpec.setDeviceIngressPorts(deviceIngressPorts);
        return this;
    }

    public DeviceSpecBuilder withDeviceEgressPorts(List<DevicePort> deviceEgressPorts) {
        deviceSpec.setDeviceEgressPorts(deviceEgressPorts);
        return this;
    }

    public DeviceSpecBuilder withDeviceIngressPort(DevicePort devicePort) {
        deviceSpec.getDeviceIngressPorts().add(devicePort);
        return this;
    }

    public DeviceSpecBuilder withDeviceEgressPort(DevicePort devicePort) {
        deviceSpec.getDeviceEgressPorts().add(devicePort);
        return this;
    }


}
