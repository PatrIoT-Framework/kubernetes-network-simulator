package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network.simulator.api.model.network.Network;

public interface KubeDevice {

    String getName();

    void setName(String name);

    Network getNetwork();

    void setNetwork(Network network);

    DeviceConfig getDeviceConfig();

    void setDeviceConfig(DeviceConfig deviceConfig);

    String getPublicIpAddress();

    void setPublicIpAddress(String publicIpAddress);

    String getPrivateIpAddress();

    void setPrivateIpAddress(String privateIpAddress);

    DeviceConfigPort getManagementPort();

    void setManagementPort(DeviceConfigPort managementPort);

    void finalizeConfiguration();
}