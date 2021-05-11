package io.patriot_framework.network_simulator.kubernetes.device;

import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

/**
 * Abstract class representing device deployed inside Simulated network.
 * It contains all necessary information about deployed device.
 * <p>
 * This class is intended to be extended with specific implementations like:
 * Application, DataGenerator or ActiveDataGenerator.
 */
public abstract class KubeDevice {
    private String name;
    private KubeNetwork network;
    private DeviceConfig deviceConfig = new DeviceConfig();
    private String publicIpAddress;
    private String privateIpAddress;
    private DeviceConfigPort managementPort;

    /**
     * Constructor to create KubeDevice based only on name and KubeNetwork.
     *
     * @param name    Name of the device
     * @param network KubeNetwork, where device will be deployed
     */
    public KubeDevice(String name, KubeNetwork network) {
        this.name = name;
        this.network = network;
    }

    /**
     * Constructor to create KubeDevice based only on name and KubeNetwork.
     *
     * @param name         Name of the device
     * @param network      KubeNetwork, where device will be deployed
     * @param deviceConfig configuration of device describing how it should be deployed
     */
    public KubeDevice(String name, KubeNetwork network, DeviceConfig deviceConfig) {
        this(name, network);
        this.deviceConfig = deviceConfig;
    }

    /**
     * Gets name of the device
     *
     * @return name of the device
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the device
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the KubeNetwork of the device
     *
     * @return KubeNetwork of the device
     */
    public KubeNetwork getNetwork() {
        return network;
    }

    /**
     * Sets the KubeNetwork for the device
     *
     * @param network KubeNetwork which will be set to the device
     */
    public void setNetwork(KubeNetwork network) {
        this.network = network;
    }


    /**
     * Gets the device configuration.
     *
     * @return device configuration
     */
    public DeviceConfig getDeviceConfig() {
        return deviceConfig;
    }

    /**
     * Sets the device configuration
     *
     * @param deviceConfig device configuration to set
     */
    public void setDeviceConfig(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }

    /**
     * Gets the public IP address of deployed device
     *
     * @return String representation of the public IP address
     */
    public String getPublicIpAddress() {
        return publicIpAddress;
    }

    /**
     * Sets the public IP address of device
     *
     * @param publicIpAddress String representation of the public IP address
     */
    public void setPublicIpAddress(String publicIpAddress) {
        this.publicIpAddress = publicIpAddress;
    }

    /**
     * Gets the private IP address of deployed device
     *
     * @return String representation of the private IP address
     */
    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    /**
     * Sets the private IP address of device
     *
     * @param privateIpAddress String representation of the private IP address
     */
    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    /**
     * Gets the management port of the device.
     *
     * @return management port of the device
     */
    public DeviceConfigPort getManagementPort() {
        return managementPort;
    }

    /**
     * Sets the management port of the device
     *
     * @param managementPort management port of the device
     */
    public void setManagementPort(DeviceConfigPort managementPort) {
        this.managementPort = managementPort;
    }

    /**
     * Method which is called right before the device is deployed to the Kubernetes.
     * It finishes the configuration of the device.
     */
    public abstract void finalizeConfiguration();
}

