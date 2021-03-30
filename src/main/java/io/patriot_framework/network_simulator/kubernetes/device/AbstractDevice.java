package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network.simulator.api.model.devices.Device;
import io.patriot_framework.network.simulator.api.model.network.Network;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDevice implements KubeDevice {
    private String name;
    private Network network;
    private DeviceConfig deviceConfig;
    private String publicIpAddress;
    private String privateIpAddress;
    private List<Network> connectedNetworks = new ArrayList<>();
    private List<Device> connectedDevices = new ArrayList<>();
    private Object creator;
    private DeviceConfigPort managementPort;

    public AbstractDevice(String name, Network network) {
        this.name = name;
        this.network = network;
    }

    public AbstractDevice(String name, Network network, DeviceConfig deviceConfig) {
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
    public Network getNetwork() {
        return network;
    }

    @Override
    public void setNetwork(Network network) {
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

    public List<Network> getConnectedNetworks() {
        return connectedNetworks;
    }

    public void setConnectedNetworks(List<Network> connectedNetworks) {
        this.connectedNetworks = connectedNetworks;
    }

    public List<Device> getConnectedDevices() {
        return connectedDevices;
    }

    public void setConnectedDevices(List<Device> connectedDevices) {
        this.connectedDevices = connectedDevices;
    }

    public Object getCreator() {
        return creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
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
