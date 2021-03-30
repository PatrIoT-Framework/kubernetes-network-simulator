package io.patriot_framework.network_simulator.kubernetes.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceConfig {
    private String image;
    private Map<String, String> env = new HashMap<>();
    private List<DeviceConfigPort> ports = new ArrayList<>();
    private Map<String, String> filesConfiguration = new HashMap<>();
    private String fileConfigurationPrefixPath = "/etc/config/";
    private DeviceConfigPort managementPort;

    public DeviceConfig() {
    }

    public DeviceConfig(String image) {
        this.image = image;
    }

    public DeviceConfig(String image, DeviceConfigPort managementPort) {
        this(image);
        this.managementPort = managementPort;
        ports.add(managementPort);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getEnv() {
        return env;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

    public void addEnv(String key, String value) {
        env.put(key, value);
    }

    public List<DeviceConfigPort> getPorts() {
        return ports;
    }

    public void setPorts(List<DeviceConfigPort> ports) {
        this.ports = ports;
    }

    public void addPort(DeviceConfigPort port) {
        ports.add(port);
    }

    public Map<String, String> getFilesConfiguration() {
        return filesConfiguration;
    }

    public void setFilesConfiguration(Map<String, String> filesConfiguration) {
        this.filesConfiguration = filesConfiguration;
    }

    public void setFileConfiguration(String name, String value) {
        this.filesConfiguration.put(name, value);
    }

    public String getFileConfigurationPrefixPath() {
        return fileConfigurationPrefixPath;
    }

    public void setFileConfigurationPrefixPath(String fileConfigurationPrefixPath) {
        this.fileConfigurationPrefixPath = fileConfigurationPrefixPath;
    }

    public DeviceConfigPort getManagementPort() {
        return managementPort;
    }

    public void setManagementPort(DeviceConfigPort managementPort) {
        this.managementPort = managementPort;
    }
}
