package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network.simulator.api.model.devices.Device;
import io.patriot_framework.network.simulator.api.model.network.Network;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator implements KubeDevice {
    private String name;
    private Network network;
    private io.patriot_framework.generator.device.Device device;
    private DeviceConfig deviceConfig;
    private String publicIpAddress;
    private String privateIpAddress;
    private List<Network> connectedNetworks = new ArrayList<>();
    private List<Device> connectedDevices = new ArrayList<>();
    private Object creator;
    private DeviceConfigPort managementPort;
    public static final DeviceConfigPort DEFAULT_MANAGEMENT_PORT = new DeviceConfigPort(5683, "UDP");

    public DataGenerator(String name, Network network, io.patriot_framework.generator.device.Device device) {
        this.name = name;
        this.network = network;
        this.device = device;
    }

    public void finalizeConfiguration() {
        deviceConfig = DataGeneratorConverter.convertToDeviceConfig(device, deviceConfig);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public io.patriot_framework.generator.device.Device getDevice() {
        return device;
    }

    public void setDevice(io.patriot_framework.generator.device.Device device) {
        this.device = device;
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

    public List<Network> getConnectedNetworks() {
        return connectedNetworks;
    }

    public void setConnectedNetworks(List<Network> connectedNetworks) {
        this.connectedNetworks = connectedNetworks;
    }

    public List<io.patriot_framework.network.simulator.api.model.devices.Device> getConnectedDevices() {
        return connectedDevices;
    }

    public void setConnectedDevices(List<io.patriot_framework.network.simulator.api.model.devices.Device> connectedDevices) {
        this.connectedDevices = connectedDevices;
    }

    public String getCreator() {
        return creator.toString();
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public void setManagementPort(DeviceConfigPort managementPort) {
        this.managementPort = managementPort;
    }

    public DeviceConfigPort getManagementPort() {
        if (managementPort == null) {
            return DEFAULT_MANAGEMENT_PORT;
        }
        return managementPort;
    }


}
