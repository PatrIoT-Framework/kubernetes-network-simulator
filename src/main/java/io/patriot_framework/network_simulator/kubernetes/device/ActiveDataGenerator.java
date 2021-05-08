package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.active.ActiveDevice;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

public class ActiveDataGenerator extends KubeDevice {
    private ActiveDevice activeDevice;
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(5683, "UDP");

    public ActiveDataGenerator(String name, KubeNetwork network, ActiveDevice activeDevice) {
        super(name, network);
        this.activeDevice = activeDevice;
        setManagementPort(DEFAULT_MANAGEMENT_PORT);
    }

    public ActiveDevice getActiveDevice() {
        return activeDevice;
    }

    public void setActiveDevice(ActiveDevice activeDevice) {
        this.activeDevice = activeDevice;
    }

    @Override
    public void finalizeConfiguration() {
        setDeviceConfig(DataGeneratorConverter.convertToDeviceConfig(activeDevice, getDeviceConfig()));
    }
}
