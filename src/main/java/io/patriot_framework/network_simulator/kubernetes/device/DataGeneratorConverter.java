package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.active.Active;
import io.patriot_framework.generator.utils.JSONSerializer;
import io.patriot_framework.network_simulator.kubernetes.Constants;

public class DataGeneratorConverter {

    public static DeviceConfig convertToDeviceConfig(Device device, DeviceConfig deviceConfig) {
        fillDeviceConfig(deviceConfig);

        deviceConfig.setFileConfiguration(Constants.DEFAULT_CONFIG_NAME, JSONSerializer.serialize(device));

        String configFilePath = deviceConfig.getFileConfigurationPrefixPath() + Constants.DEFAULT_CONFIG_NAME;
        deviceConfig.addEnv(Constants.DEVICE_CONFIG_FILE, configFilePath);
        return deviceConfig;
    }

    public static DeviceConfig convertToDeviceConfig(Active activeDevice, DeviceConfig deviceConfig) {
        fillDeviceConfig(deviceConfig);

        deviceConfig.setFileConfiguration(Constants.DEFAULT_CONFIG_NAME, JSONSerializer.serialize(activeDevice));
        String configFilePath = deviceConfig.getFileConfigurationPrefixPath() + Constants.DEFAULT_CONFIG_NAME;
        deviceConfig.addEnv(Constants.ACTIVE_CONFIG_FILE, configFilePath);
        return deviceConfig;
    }

    private static void fillDeviceConfig(DeviceConfig deviceConfig) {
        if (deviceConfig.getImage() == null) {
            deviceConfig.setImage(Constants.DEFAULT_DATA_GENERATOR_RUNNER_IMAGE);
        }
        deviceConfig.addPort(new DeviceConfigPort(5683, "UDP"));
        deviceConfig.setManagementPort(new DeviceConfigPort(5683, "UDP"));
    }
}
