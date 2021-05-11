package io.patriot_framework.network_simulator.kubernetes.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class represents configuration of the device.
 * It tries to create a possibility to set everything that is possible by Kubernetes objects used in the simulation.
 */
public class DeviceConfig {
    private String image;
    private Map<String, String> env = new HashMap<>();
    private List<DeviceConfigPort> ports = new ArrayList<>();
    private Map<String, String> filesConfiguration = new HashMap<>();
    private String fileConfigurationPrefixPath = "/etc/config/";
    private DeviceConfigPort managementPort;
    private boolean enableInternet;

    /**
     * Default constructor
     */
    public DeviceConfig() {
    }

    /**
     * Constructor to create DeviceConfig
     *
     * @param image image of the device
     */
    public DeviceConfig(String image) {
        this.image = image;
    }

    /**
     * Constructor to create DeviceConfig
     *
     * @param image          image of the device
     * @param managementPort management port of the device
     */
    public DeviceConfig(String image, DeviceConfigPort managementPort) {
        this(image);
        this.managementPort = managementPort;
        ports.add(managementPort);
    }

    /**
     * Gets the image of the device
     *
     * @return image of the device
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image of the device
     *
     * @param image image of the device
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the environment variables of given device
     *
     * @return map of envinment variables
     */
    public Map<String, String> getEnv() {
        return env;
    }

    /**
     * Sets the environment variables for the device
     *
     * @param env environment variables to set
     */
    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

    /**
     * Adds single environment variable
     *
     * @param key   key of the variable
     * @param value value of the variable
     */
    public void addEnv(String key, String value) {
        env.put(key, value);
    }

    /**
     * Gets the ports used for device
     *
     * @return list of the ports used by device
     */
    public List<DeviceConfigPort> getPorts() {
        return ports;
    }

    /**
     * Sets list of ports for the device
     *
     * @param ports list of ports
     */
    public void setPorts(List<DeviceConfigPort> ports) {
        this.ports = ports;
    }

    /**
     * Adds single port for the device
     *
     * @param port port to add
     */
    public void addPort(DeviceConfigPort port) {
        ports.add(port);
    }

    /**
     * Gets the file configuration. The configuration is represented by map, where:
     * key:   is the name of the file
     * value: is the content of the file
     *
     * @return the map of file configurations
     */
    public Map<String, String> getFilesConfiguration() {
        return filesConfiguration;
    }

    /**
     * Sets the file configuration. The configuration is represented by map, where:
     * key:   is the name of the file
     * value: is the content of the file
     *
     * @param filesConfiguration map with the file configurations
     */
    public void setFilesConfiguration(Map<String, String> filesConfiguration) {
        this.filesConfiguration = filesConfiguration;
    }

    /**
     * Sets single <key,value> file configuration to the map.
     *
     * @param name  name of the file
     * @param value content of the file
     */
    public void setFileConfiguration(String name, String value) {
        this.filesConfiguration.put(name, value);
    }

    /**
     * Gets the file configuration prefix path.
     *
     * @return file configuration prefix path
     */
    public String getFileConfigurationPrefixPath() {
        return fileConfigurationPrefixPath;
    }


    /**
     * Sets the file configuration prefix path.
     *
     * @param fileConfigurationPrefixPath file configuration prefix path to set
     */
    public void setFileConfigurationPrefixPath(String fileConfigurationPrefixPath) {
        this.fileConfigurationPrefixPath = fileConfigurationPrefixPath;
    }

    /**
     * Gets the management port of the device
     *
     * @return management port of the device
     */
    public DeviceConfigPort getManagementPort() {
        return managementPort;
    }

    /**
     * Sets the management port of the device
     *
     * @param managementPort management port to set
     */
    public void setManagementPort(DeviceConfigPort managementPort) {
        this.managementPort = managementPort;
    }

    /**
     * Gets the value if the internet connection is enabled for the device
     *
     * @return true if the internet is enabled, false otherwise
     */
    public boolean isEnableInternet() {
        return enableInternet;
    }

    /**
     * Sets the value if the internet connection is enabled for the device
     *
     * @param enableInternet true if the internet should be enabled, false otherwise
     */
    public void setEnableInternet(boolean enableInternet) {
        this.enableInternet = enableInternet;
    }
}
