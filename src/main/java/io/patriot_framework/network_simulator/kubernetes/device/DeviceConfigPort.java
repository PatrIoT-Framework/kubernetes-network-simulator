package io.patriot_framework.network_simulator.kubernetes.device;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceConfigPort that = (DeviceConfigPort) o;
        return Objects.equals(port, that.port) && protocol.equals(that.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, protocol);
    }
}
