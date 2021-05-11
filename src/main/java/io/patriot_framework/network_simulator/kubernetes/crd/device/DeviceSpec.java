package io.patriot_framework.network_simulator.kubernetes.crd.device;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicySpec;
import io.patriot_framework.network_simulator.kubernetes.crd.ConnectionRules;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing Spec of the Device custom resource definition
 */
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
    @JsonProperty("deviceIngressRules")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ConnectionRules> deviceIngressRules = new ArrayList<>();
    @JsonProperty("deviceEgressRules")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ConnectionRules> deviceEgressRules = new ArrayList<>();
    @JsonProperty("enableInternet")
    private boolean enableInternet;
    @JsonProperty("networkPolicySpec")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private NetworkPolicySpec networkPolicySpec;

    /**
     * Gets the network name
     *
     * @return network name
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Sets the network name
     *
     * @param networkName network name
     */
    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    /**
     * Gets the PodSpec {@link io.fabric8.kubernetes.api.model.PodSpec}
     *
     * @return PodSpec
     */
    public PodSpec getPodSpec() {
        return podSpec;
    }

    /**
     * Sets the PodSpec {@link io.fabric8.kubernetes.api.model.PodSpec}
     *
     * @param podSpec PodSpec
     */
    public void setPodSpec(PodSpec podSpec) {
        this.podSpec = podSpec;
    }

    /**
     * Gets the ServiceSpec {@link io.fabric8.kubernetes.api.model.ServiceSpec}
     *
     * @return ServiceSpec
     */
    public ServiceSpec getServiceSpec() {
        return serviceSpec;
    }

    /**
     * Sets the ServiceSpec {@link io.fabric8.kubernetes.api.model.ServiceSpec}
     *
     * @param serviceSpec ServiceSpec
     */
    public void setServiceSpec(ServiceSpec serviceSpec) {
        this.serviceSpec = serviceSpec;
    }

    /**
     * Gets the list of device ingress rules
     *
     * @return list of the device ingress rules
     */
    public List<ConnectionRules> getDeviceIngressRules() {
        return deviceIngressRules;
    }

    /**
     * Sets the list of the device ingress rules
     *
     * @param deviceIngressRules list of the device ingress rules
     */
    public void setDeviceIngressRules(List<ConnectionRules> deviceIngressRules) {
        this.deviceIngressRules = deviceIngressRules;
    }

    /**
     * Gets the list of device egress rules
     *
     * @return list of the device ergress rules
     */
    public List<ConnectionRules> getDeviceEgressRules() {
        return deviceEgressRules;
    }

    /**
     * Sets the list of the device egress rules
     *
     * @param deviceEgressRules list of the device egress rules
     */
    public void setDeviceEgressRules(List<ConnectionRules> deviceEgressRules) {
        this.deviceEgressRules = deviceEgressRules;
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

    /**
     * Gets the network policy spec {@link io.fabric8.kubernetes.api.model.extensions.NetworkPolicySpec}
     *
     * @return the network policy spec
     */
    public NetworkPolicySpec getNetworkPolicySpec() {
        return networkPolicySpec;
    }

    /**
     * Sets the network policy spec {@link io.fabric8.kubernetes.api.model.extensions.NetworkPolicySpec}
     *
     * @param networkPolicySpec the network policy spec
     */
    public void setNetworkPolicySpec(NetworkPolicySpec networkPolicySpec) {
        this.networkPolicySpec = networkPolicySpec;
    }
}
