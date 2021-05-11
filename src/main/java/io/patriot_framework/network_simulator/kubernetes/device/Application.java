package io.patriot_framework.network_simulator.kubernetes.device;


import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

/**
 * Class representing general application deployed inside simulated network.
 */
public class Application extends KubeDevice {
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(8080);

    /**
     * Constructor to create Application
     *
     * @param name    name of the application
     * @param network network where the application should be deployed
     */
    public Application(String name, KubeNetwork network) {
        super(name, network);
    }

    /**
     * Constructor to create Application
     *
     * @param name         name of the application
     * @param network      network where the application should be deployed
     * @param deviceConfig configuration of the application
     */
    public Application(String name, KubeNetwork network, DeviceConfig deviceConfig) {
        super(name, network, deviceConfig);
    }

    @Override
    public void finalizeConfiguration() {
        if (getDeviceConfig().getManagementPort() == null) {
            getDeviceConfig().setManagementPort(DEFAULT_MANAGEMENT_PORT);
        }
    }


}
