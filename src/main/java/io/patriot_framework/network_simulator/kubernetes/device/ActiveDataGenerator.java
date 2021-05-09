package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.active.Active;
import io.patriot_framework.generator.device.active.ActiveDevice;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

public class ActiveDataGenerator extends KubeDevice {
    private Active activeDevice;
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(5683, "UDP");

    public ActiveDataGenerator(String name, KubeNetwork network, Active activeDevice) {
        super(name, network);
        this.activeDevice = activeDevice;
        setManagementPort(DEFAULT_MANAGEMENT_PORT);
    }

    public Active getActiveDevice() {
        return activeDevice;
    }

    public void setActiveDevice(Active activeDevice) {
        this.activeDevice = activeDevice;
    }

    @Override
    public void finalizeConfiguration() {
        setDeviceConfig(DataGeneratorConverter.convertToDeviceConfig(activeDevice, getDeviceConfig()));
    }
}
