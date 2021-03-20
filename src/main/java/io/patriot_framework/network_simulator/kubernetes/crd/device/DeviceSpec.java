package io.patriot_framework.network_simulator.kubernetes.crd.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.ServiceSpec;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceSpec implements KubernetesResource {
    @JsonProperty("networkName")
    private String networkName;
    @JsonProperty("podSpec")
    private PodSpec podSpec;
    @JsonProperty("serviceSpec")
    private ServiceSpec serviceSpec;
    @JsonProperty("deviceIngressPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private DevicePorts[] deviceIngressPorts;
    @JsonProperty("DeviceEgressPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private DevicePorts[] deviceEgressPorts;

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public PodSpec getPodSpec() {
        return podSpec;
    }

    public void setPodSpec(PodSpec podSpec) {
        this.podSpec = podSpec;
    }

    public ServiceSpec getServiceSpec() {
        return serviceSpec;
    }

    public void setServiceSpec(ServiceSpec serviceSpec) {
        this.serviceSpec = serviceSpec;
    }

    public DevicePorts[] getDeviceIngressPorts() {
        return deviceIngressPorts;
    }

    public void setDeviceIngressPorts(DevicePorts[] deviceIngressPorts) {
        this.deviceIngressPorts = deviceIngressPorts;
    }

    public DevicePorts[] getDeviceEgressPorts() {
        return deviceEgressPorts;
    }

    public void setDeviceEgressPorts(DevicePorts[] deviceEgressPorts) {
        this.deviceEgressPorts = deviceEgressPorts;
    }
}
