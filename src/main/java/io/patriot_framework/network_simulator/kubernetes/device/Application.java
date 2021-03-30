package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network.simulator.api.model.network.Network;

public class Application extends AbstractDevice {
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(8080);

    public Application(String name, Network network) {
        super(name, network);
    }

    public Application(String name, Network network, DeviceConfig deviceConfig) {
        super(name, network, deviceConfig);
    }

    @Override
    public void finalizeConfiguration() {
        if (getDeviceConfig().getManagementPort() == null) {
            getDeviceConfig().setManagementPort(DEFAULT_MANAGEMENT_PORT);
        }
    }


}