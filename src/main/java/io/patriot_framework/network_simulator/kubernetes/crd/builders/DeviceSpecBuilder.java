package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.patriot_framework.network_simulator.kubernetes.crd.Ports;
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

    public DeviceSpecBuilder withDeviceIngressPorts(List<Ports> deviceIngressPorts) {
        deviceSpec.setDeviceIngressPorts(deviceIngressPorts);
        return this;
    }

    public DeviceSpecBuilder withDeviceEgressPorts(List<Ports> deviceEgressPorts) {
        deviceSpec.setDeviceEgressPorts(deviceEgressPorts);
        return this;
    }

    public DeviceSpecBuilder withDeviceIngressPort(Ports ports) {
        deviceSpec.getDeviceIngressPorts().add(ports);
        return this;
    }

    public DeviceSpecBuilder withDeviceEgressPort(Ports ports) {
        deviceSpec.getDeviceEgressPorts().add(ports);
        return this;
    }


}
