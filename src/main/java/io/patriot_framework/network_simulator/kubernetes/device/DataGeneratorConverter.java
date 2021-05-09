package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.active.Active;
import io.patriot_framework.generator.utils.JSONSerializer;

public class DataGeneratorConverter {
    public static final String DEFAULT_DATA_GENERATOR_RUNNER_IMAGE = "jsmadis/patriot-data-generator-runner:latest";
    public static final String DEFAULT_CONFIG_NAME = "config.json";
    public static final String DEVICE_CONFIG_FILE = "PATRIOT_DATA_GENERATOR_DEVICE_FILE";
    public static final String ACTIVE_DEVICE_CONFIG_FILE = "PATRIOT_DATA_GENERATOR_ACTIVE_FILE";

    public static DeviceConfig convertToDeviceConfig(Device device, DeviceConfig deviceConfig) {
        fillDeviceConfig(deviceConfig);

        deviceConfig.setFileConfiguration(DEFAULT_CONFIG_NAME, JSONSerializer.serialize(device));
        deviceConfig.addEnv(DEVICE_CONFIG_FILE, deviceConfig.getFileConfigurationPrefixPath() + DEFAULT_CONFIG_NAME);
        return deviceConfig;
    }

    public static DeviceConfig convertToDeviceConfig(Active activeDevice, DeviceConfig deviceConfig) {
        fillDeviceConfig(deviceConfig);

        deviceConfig.setFileConfiguration(DEFAULT_CONFIG_NAME, JSONSerializer.serialize(activeDevice));
        deviceConfig.addEnv(ACTIVE_DEVICE_CONFIG_FILE, deviceConfig.getFileConfigurationPrefixPath() + DEFAULT_CONFIG_NAME);
        return deviceConfig;
    }

    private static void fillDeviceConfig(DeviceConfig deviceConfig) {
        if (deviceConfig.getImage() == null) {
            deviceConfig.setImage(DEFAULT_DATA_GENERATOR_RUNNER_IMAGE);
        }
        deviceConfig.addPort(new DeviceConfigPort(5683, "UDP"));
        deviceConfig.setManagementPort(new DeviceConfigPort(5683, "UDP"));
    }
}
