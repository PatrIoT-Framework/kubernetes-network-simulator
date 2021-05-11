package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.active.Active;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

/**
 * Class represents Data Generator Active deployed inside simulated network.
 * {@link io.patriot_framework.generator.device.active.Active}
 */
public class ActiveDataGenerator extends KubeDevice {
    private Active active;
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(5683, "UDP");

    /**
     * Constructor for ActiveDataGenerator
     *
     * @param name    name of the deployed device
     * @param network network where the device should be deployed
     * @param active  active {@link io.patriot_framework.generator.device.active.Active}
     */
    public ActiveDataGenerator(String name, KubeNetwork network, Active active) {
        super(name, network);
        this.active = active;
        setManagementPort(DEFAULT_MANAGEMENT_PORT);
    }

    /**
     * Gets the active
     *
     * @return active {@link io.patriot_framework.generator.device.active.Active}
     */
    public Active getActive() {
        return active;
    }

    /**
     * Sets the active
     *
     * @param active active {@link io.patriot_framework.generator.device.active.Active}
     */
    public void setActive(Active active) {
        this.active = active;
    }

    @Override
    public void finalizeConfiguration() {
        DataGeneratorUtils.updateDeviceConfigWithActive(active, getDeviceConfig());
    }
}
