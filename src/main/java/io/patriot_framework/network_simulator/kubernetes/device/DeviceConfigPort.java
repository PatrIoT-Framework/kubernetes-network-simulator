package io.patriot_framework.network_simulator.kubernetes.device;

import java.util.Objects;

/**
 * Class representing Port and the protocol.
 * It's used mainly because Kubernetes distinguish also by protocol.
 */
public class DeviceConfigPort {
    private Integer port;
    private String protocol;

    /**
     * Constructor to create DeviceConfigPort
     *
     * @param port port
     */
    public DeviceConfigPort(Integer port) {
        this.port = port;
    }

    /**
     * Constructor to create DeviceConfigPort
     *
     * @param port     port
     * @param protocol protocol
     */
    public DeviceConfigPort(Integer port, String protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * Gets ports
     *
     * @return port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Sets port
     *
     * @param port port
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * Gets protocol
     *
     * @return protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Sets protocol
     *
     * @param protocol protocol
     */
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
