package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceSpec;

/**
 * Class representing DeviceCrd builder.
 */
public class DeviceCrdBuilder {
    private DeviceCrd deviceCrd;


    /**
     * Constructor of the DeviceCrdBuilder
     */
    public DeviceCrdBuilder() {
        deviceCrd = new DeviceCrd();
    }

    /**
     * Builds the DeviceCrd
     *
     * @return DeviceCrd
     */
    public DeviceCrd build() {
        return deviceCrd;
    }

    /**
     * Adds name to the DeviceCrd
     *
     * @param name name of the DeviceCrd
     * @return instance of the builder
     */
    public DeviceCrdBuilder withName(String name) {
        deviceCrd.getMetadata().setName(name);
        return this;
    }

    /**
     * Adds DeviceSpec to the DeviceCrd {@link io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceSpec}
     *
     * @param deviceSpec true if the internet should be enabled, false otherwise
     * @return instance of the builder
     */
    public DeviceCrdBuilder withSpec(DeviceSpec deviceSpec) {
        deviceCrd.setSpec(deviceSpec);
        return this;
    }


}
