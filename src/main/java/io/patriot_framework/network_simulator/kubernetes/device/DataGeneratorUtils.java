package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.active.Active;
import io.patriot_framework.generator.utils.JSONSerializer;
import io.patriot_framework.network_simulator.kubernetes.Constants;

/**
 * Helper class used for updating DeviceConfig with data of the Device or Active interfaces.
 */
public class DataGeneratorUtils {

    /**
     * Updates the Device Config with all necessary configuration of the Device interface.
     * {@link io.patriot_framework.generator.device.Device}
     *
     * @param device       Device
     * @param deviceConfig device config to update
     */
    public static void updateDeviceConfigWithDevice(Device device, DeviceConfig deviceConfig) {
        fillDeviceConfig(deviceConfig);

        deviceConfig.setFileConfiguration(Constants.DEFAULT_CONFIG_NAME, JSONSerializer.serialize(device));

        String configFilePath = deviceConfig.getFileConfigurationPrefixPath() + Constants.DEFAULT_CONFIG_NAME;
        deviceConfig.addEnv(Constants.DEVICE_CONFIG_FILE, configFilePath);
    }

    /**
     * Updates the DeviceConfig with all necessary configuration of the Active interface.
     * {@link io.patriot_framework.generator.device.active.Active}
     *
     * @param active       Active
     * @param deviceConfig device config to update
     */
    public static void updateDeviceConfigWithActive(Active active, DeviceConfig deviceConfig) {
        fillDeviceConfig(deviceConfig);

        deviceConfig.setFileConfiguration(Constants.DEFAULT_CONFIG_NAME, JSONSerializer.serialize(active));
        String configFilePath = deviceConfig.getFileConfigurationPrefixPath() + Constants.DEFAULT_CONFIG_NAME;
        deviceConfig.addEnv(Constants.ACTIVE_CONFIG_FILE, configFilePath);
    }

    private static void fillDeviceConfig(DeviceConfig deviceConfig) {
        if (deviceConfig.getImage() == null) {
            deviceConfig.setImage(Constants.DEFAULT_DATA_GENERATOR_RUNNER_IMAGE);
        }
        deviceConfig.addPort(new DeviceConfigPort(5683, "UDP"));
        deviceConfig.setManagementPort(new DeviceConfigPort(5683, "UDP"));
    }
}
