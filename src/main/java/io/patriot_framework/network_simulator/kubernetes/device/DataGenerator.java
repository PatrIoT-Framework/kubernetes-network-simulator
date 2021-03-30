package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.Device;
import io.patriot_framework.network.simulator.api.model.network.Network;

public class DataGenerator extends AbstractDevice {
    private io.patriot_framework.generator.device.Device device;
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(5683, "UDP");

    public DataGenerator(String name, Network network, io.patriot_framework.generator.device.Device device) {
        super(name, network);
        this.device = device;
        setManagementPort(DEFAULT_MANAGEMENT_PORT);
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public void finalizeConfiguration() {
        setDeviceConfig(DataGeneratorConverter.convertToDeviceConfig(device, getDeviceConfig()));

    }
}
