package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network.simulator.api.model.devices.Device;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDevice implements KubeDevice {
    private String name;
    private KubeNetwork network;
    private DeviceConfig deviceConfig;
    private String publicIpAddress;
    private String privateIpAddress;
    private Set<KubeNetwork> connectedNetworks = new HashSet<>();
    private Set<Device> connectedDevices = new HashSet<>();
    private Object creator;
    private DeviceConfigPort managementPort;

    public AbstractDevice(String name, KubeNetwork network) {
        this.name = name;
        this.network = network;
    }

    public AbstractDevice(String name, KubeNetwork network, DeviceConfig deviceConfig) {
        this(name, network);
        this.deviceConfig = deviceConfig;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public KubeNetwork getNetwork() {
        return network;
    }

    @Override
    public void setNetwork(KubeNetwork network) {
        this.network = network;
    }

    @Override
    public DeviceConfig getDeviceConfig() {
        return deviceConfig;
    }

    @Override
    public void setDeviceConfig(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }

    @Override
    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    @Override
    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    @Override
    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    @Override
    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    @Override
    public DeviceConfigPort getManagementPort() {
        return managementPort;
    }

    @Override
    public void setManagementPort(DeviceConfigPort managementPort) {
        this.managementPort = managementPort;
    }
}
