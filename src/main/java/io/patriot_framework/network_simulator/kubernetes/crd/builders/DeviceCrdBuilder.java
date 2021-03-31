package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceSpec;

public class DeviceCrdBuilder {
    private DeviceCrd deviceCrd;


    public DeviceCrdBuilder() {
        deviceCrd = new DeviceCrd();
    }

    public DeviceCrd build() {
        return deviceCrd;
    }

    public DeviceCrdBuilder withName(String name) {
        deviceCrd.getMetadata().setName(name);
        return this;
    }

    public DeviceCrdBuilder withSpec(DeviceSpec deviceSpec) {
        deviceCrd.setSpec(deviceSpec);
        return this;
    }


}
