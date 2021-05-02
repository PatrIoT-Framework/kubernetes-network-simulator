package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

public abstract class KubeDevice {
    private String name;
    private KubeNetwork network;
    private DeviceConfig deviceConfig;
    private String publicIpAddress;
    private String privateIpAddress;
    private DeviceConfigPort managementPort;

    public KubeDevice(String name, KubeNetwork network) {
        this.name = name;
        this.network = network;
    }

    public KubeDevice(String name, KubeNetwork network, DeviceConfig deviceConfig) {
        this(name, network);
        this.deviceConfig = deviceConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KubeNetwork getNetwork() {
        return network;
    }

    public void setNetwork(KubeNetwork network) {
        this.network = network;
    }

    public DeviceConfig getDeviceConfig() {
        return deviceConfig;
    }

    public void setDeviceConfig(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }

    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    public DeviceConfigPort getManagementPort() {
        return managementPort;
    }

    public void setManagementPort(DeviceConfigPort managementPort) {
        this.managementPort = managementPort;
    }

    public abstract void finalizeConfiguration();
}

