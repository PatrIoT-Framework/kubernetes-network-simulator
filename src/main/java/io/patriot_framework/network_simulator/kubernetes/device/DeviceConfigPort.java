package io.patriot_framework.network_simulator.kubernetes.device;

public class DeviceConfigPort {
    private Integer port;
    private String protocol;

    public DeviceConfigPort(Integer port) {
        this.port = port;
    }

    public DeviceConfigPort(Integer port, String protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
