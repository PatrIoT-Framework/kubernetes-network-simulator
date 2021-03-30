package io.patriot_framework.network_simulator.kubernetes.crd.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicySpec;

import java.util.ArrayList;
import java.util.List;

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
    private List<DevicePort> deviceIngressPorts = new ArrayList<>();
    @JsonProperty("deviceEgressPorts")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DevicePort> deviceEgressPorts = new ArrayList<>();
    @JsonProperty("enableInternet")
    private boolean enableInternet;
    @JsonProperty("networkPolicySpec")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private NetworkPolicySpec networkPolicySpec;

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

    public List<DevicePort> getDeviceIngressPorts() {
        return deviceIngressPorts;
    }

    public void setDeviceIngressPorts(List<DevicePort> deviceIngressPorts) {
        this.deviceIngressPorts = deviceIngressPorts;
    }

    public List<DevicePort> getDeviceEgressPorts() {
        return deviceEgressPorts;
    }

    public void setDeviceEgressPorts(List<DevicePort> deviceEgressPorts) {
        this.deviceEgressPorts = deviceEgressPorts;
    }

    public boolean isEnableInternet() {
        return enableInternet;
    }

    public void setEnableInternet(boolean enableInternet) {
        this.enableInternet = enableInternet;
    }

    public NetworkPolicySpec getNetworkPolicySpec() {
        return networkPolicySpec;
    }

    public void setNetworkPolicySpec(NetworkPolicySpec networkPolicySpec) {
        this.networkPolicySpec = networkPolicySpec;
    }
}
