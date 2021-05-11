package io.patriot_framework.network_simulator.kubernetes.crd.builders;

import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceSpec;

import java.util.List;

/**
 * Class representing builder of the DeviceSpec.
 */
public class DeviceSpecBuilder {
    private DeviceSpec deviceSpec;

    /**
     * Constructor of the builder.
     */
    public DeviceSpecBuilder() {
        deviceSpec = new DeviceSpec();
    }

    /**
     * Builds the Device spec
     *
     * @return DeviceSpec
     */
    public DeviceSpec build() {
        return deviceSpec;
    }

    /**
     * Adds Network name to the DeviceSpec
     *
     * @param networkName network name
     * @return instance of the builder
     */
    public DeviceSpecBuilder withNetworkName(String networkName) {
        deviceSpec.setNetworkName(networkName);
        return this;
    }

    /**
     * Adds PodSpec to the DeviceSpec
     *
     * @param podSpec PodSpec
     * @return instance of the builder
     */
    public DeviceSpecBuilder withPodSpec(PodSpec podSpec) {
        deviceSpec.setPodSpec(podSpec);
        return this;
    }

    /**
     * Adds ServiceSpec to the DeviceSpec
     *
     * @param serviceSpec ServiceSpec
     * @return instance of the builder
     */
    public DeviceSpecBuilder withServiceSpec(ServiceSpec serviceSpec) {
        deviceSpec.setServiceSpec(serviceSpec);
        return this;
    }

    /**
     * Adds list of the device ingress ports to the DeviceSpec
     *
     * @param deviceIngressPorts list of the device ingress ports
     * @return instance of the builder
     */
    public DeviceSpecBuilder withDeviceIngressPorts(List<ConnectionRules> deviceIngressPorts) {
        deviceSpec.setDeviceIngressRules(deviceIngressPorts);
        return this;
    }

    /**
     * Adds list of the device egress ports to the DeviceSpec
     *
     * @param deviceEgressPorts list of the device egress ports
     * @return instance of the builder
     */
    public DeviceSpecBuilder withDeviceEgressPorts(List<ConnectionRules> deviceEgressPorts) {
        deviceSpec.setDeviceEgressRules(deviceEgressPorts);
        return this;
    }

    /**
     * Adds single device ingress port to the DeviceSpec
     *
     * @param connectionRules connection rule  which will be added to the device ingress ports
     * @return instance of the builder
     */
    public DeviceSpecBuilder withDeviceIngressPort(ConnectionRules connectionRules) {
        deviceSpec.getDeviceIngressRules().add(connectionRules);
        return this;
    }

    /**
     * Adds single device egress port to the DeviceSpec
     *
     * @param connectionRules connection rule  which will be added to the device egress ports
     * @return instance of the builder
     */
    public DeviceSpecBuilder withDeviceEgressPort(ConnectionRules connectionRules) {
        deviceSpec.getDeviceEgressRules().add(connectionRules);
        return this;
    }

    /**
     * Adds value if the internet should be enabled
     *
     * @param enabledInternet true if the internet should be enabled, false otherwise
     * @return instance of the builder
     */
    public DeviceSpecBuilder withEnabledInternet(boolean enabledInternet) {
        deviceSpec.setEnableInternet(enabledInternet);
        return this;
    }
}
