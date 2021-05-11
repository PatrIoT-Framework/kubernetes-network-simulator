package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.Device;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

/**
 * Class represents Data Generator Device deployed inside simulated network.
 * {@link io.patriot_framework.generator.device.Device}
 */
public class DataGenerator extends KubeDevice {
    private Device device;
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(5683, "UDP");

    /**
     * Constructor to creat Data Generator.
     *
     * @param name    name of the device
     * @param network network where the device should be deployed
     * @param device  device {@link io.patriot_framework.generator.device.Device}
     */
    public DataGenerator(String name, KubeNetwork network, Device device) {
        super(name, network);
        this.device = device;
        setManagementPort(DEFAULT_MANAGEMENT_PORT);
    }

    /**
     * Gets the device
     *
     * @return device {@link io.patriot_framework.generator.device.Device}
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Sets the device
     *
     * @param device device {@link io.patriot_framework.generator.device.Device}
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public void finalizeConfiguration() {
        DataGeneratorUtils.updateDeviceConfigWithDevice(device, getDeviceConfig());
    }
}
