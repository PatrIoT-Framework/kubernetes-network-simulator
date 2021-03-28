package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.utils.JSONSerializer;

public class DataGeneratorConverter {
    public static final String DEFAULT_DATA_GENERATOR_RUNNER_IMAGE = "jsmadis/patriot-data-generator-runner:latest";
    public static final String DEFAULT_CONFIG_NAME = "config.json";
    public static final String DEVICE_CONFIG_FILE = "PATRIOT_DATA_GENERATOR_DEVICE_FILE";

    public static DeviceConfig convertToDeviceConfig(Device device, DeviceConfig deviceConfig) {
        if (deviceConfig == null) {
            deviceConfig = new DeviceConfig();
        }
        deviceConfig.setImage(DEFAULT_DATA_GENERATOR_RUNNER_IMAGE);
        deviceConfig.addPort(new DeviceConfigPort(5683, "UDP"));
        deviceConfig.setManagementPort(new DeviceConfigPort(5683, "UDP"));
        deviceConfig.setFileConfiguration(DEFAULT_CONFIG_NAME, JSONSerializer.serialize(device));
        deviceConfig.addEnv(DEVICE_CONFIG_FILE, deviceConfig.getFileConfigurationPrefixPath() + DEFAULT_CONFIG_NAME);
        return deviceConfig;
    }
}
