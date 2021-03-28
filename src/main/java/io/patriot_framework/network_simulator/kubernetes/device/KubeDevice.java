package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network.simulator.api.model.network.Network;

public interface KubeDevice {

    String getName();

    void setName(String name);

    Network getNetwork();

    void setNetwork(Network network);

    DeviceConfig getDeviceConfig();

    void setDeviceConfig(DeviceConfig deviceConfig);

    String getManagementIdAddress();

    void setManagementIdAddress(String managementIdAddress);

    io.patriot_framework.generator.device.Device getDevice();

    void setDevice(io.patriot_framework.generator.device.Device device);

    DeviceConfigPort getManagementPort();

    void setManagementPort(DeviceConfigPort managementPort);

    void finalizeConfiguration();
}
