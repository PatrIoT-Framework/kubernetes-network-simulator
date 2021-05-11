package io.patriot_framework.network_simulator.kubernetes.control;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapVolumeSourceBuilder;
import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.ContainerPort;
import io.fabric8.kubernetes.api.model.ContainerPortBuilder;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.PodSpec;
import io.fabric8.kubernetes.api.model.PodSpecBuilder;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.api.model.ServicePortBuilder;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.api.model.ServiceSpecBuilder;
import io.fabric8.kubernetes.api.model.VolumeBuilder;
import io.fabric8.kubernetes.api.model.VolumeMountBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.builders.DeviceCrdBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.builders.DeviceSpecBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceSpec;
import io.patriot_framework.network_simulator.kubernetes.device.DeviceConfig;
import io.patriot_framework.network_simulator.kubernetes.device.DeviceConfigPort;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class that contains all logic which converts KubeDevice to the DeviceCrd
 */
public class DeviceConverter {

    /**
     * Converts KubeDevice to the DeviceCrd
     *
     * @param device KubeDevice to convert
     * @return created DeviceCrd
     */
    public static DeviceCrd deviceToCrd(KubeDevice device) {
        return deviceToCrd(device, null);
    }

    /**
     * Converts KubeDevice to the DeviceCrd
     *
     * @param device    KubeDevice to convert
     * @param configMap ConfigMap which will be connected with the Pod object
     * @return created DeviceCrd
     */
    public static DeviceCrd deviceToCrd(KubeDevice device, ConfigMap configMap) {
        return new DeviceCrdBuilder()
                .withName(device.getName())
                .withSpec(deviceToSpec(device, configMap))
                .build();
    }

    /**
     * Converts KubeDevice and ConfigMap to the DeviceSpec
     *
     * @param device    KubeDevice to convert
     * @param configMap ConfigMap to use
     * @return DeviceSpec
     */
    private static DeviceSpec deviceToSpec(KubeDevice device, ConfigMap configMap) {
        return new DeviceSpecBuilder()
                .withNetworkName(device
                        .getNetwork()
                        .getName())
                .withPodSpec(deviceToPodSpec(device, configMap))
                .withServiceSpec(deviceToServiceSpec(device))
                .withEnabledInternet(device
                        .getDeviceConfig()
                        .isEnableInternet())
                .build();
    }

    /**
     * Converts KubeDevice and ConfigMap to the PodSpec
     *
     * @param device    KubeDevice to convert
     * @param configMap ConfigMap to use
     * @return PodSpec
     */
    private static PodSpec deviceToPodSpec(KubeDevice device, ConfigMap configMap) {
        DeviceConfig config = device.getDeviceConfig();
        PodSpecBuilder specBuilder = new PodSpecBuilder();

        ContainerBuilder containerBuilder = new ContainerBuilder()
                .withName(device.getName() + "-container")
                .withImage(config.getImage())
                .withPorts(configToContainerPorts(config))
                .withEnv(configToEnvVar(config));

        if (configMap != null) {
            specBuilder.withVolumes(new VolumeBuilder()
                    .withName(device.getName() + "-volume")
                    .withConfigMap(new ConfigMapVolumeSourceBuilder()
                            .withName(configMap
                                    .getMetadata()
                                    .getName())

                            .build())
                    .build());

            containerBuilder.withVolumeMounts(new VolumeMountBuilder()
                    .withName(device.getName() + "-volume")
                    .withMountPath(device
                            .getDeviceConfig()
                            .getFileConfigurationPrefixPath())
                    .build());
        }

        return specBuilder
                .withContainers(containerBuilder.build())
                .build();
    }

    /**
     * Converts KubeDevice to the ServiceSpec
     *
     * @param device KubeDevice to convert
     * @return ServiceSpec
     */
    private static ServiceSpec deviceToServiceSpec(KubeDevice device) {
        DeviceConfig config = device.getDeviceConfig();
        return new ServiceSpecBuilder()
                .withType("NodePort")
                .withPorts(configToServicePorts(config))
                .build();
    }


    /**
     * Converts DeviceConfig to the list of ContainerPorts
     *
     * @param config Device config
     * @return list of container ports
     */
    private static List<ContainerPort> configToContainerPorts(DeviceConfig config) {
        return devicePorts(config)
                .stream()
                .map(port -> new ContainerPortBuilder()
                        .withContainerPort(port.getPort())
                        .withProtocol(port.getProtocol())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Converts DeviceConfig to the list of EnvVars
     *
     * @param config Device config
     * @return list of environment variables used for PodSpec
     */
    private static List<EnvVar> configToEnvVar(DeviceConfig config) {
        return config
                .getEnv()
                .entrySet()
                .stream()
                .map(entry -> new EnvVarBuilder()
                        .withName(entry.getKey())
                        .withValue(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }


    /**
     * Converts DeviceConfig to the list of ServicePorts
     *
     * @param config Device config
     * @return list of ServicePorts
     */
    private static List<ServicePort> configToServicePorts(DeviceConfig config) {
        return devicePorts(config)
                .stream()
                .map(port -> new ServicePortBuilder()
                        .withProtocol(port.getProtocol())
                        .withPort(port.getPort())
                        .withTargetPort(new IntOrString(port.getPort()))
                        .build())
                .collect(Collectors.toList());
    }


    /**
     * Converts DeviceConfig to the list of DeviceConfigPorts
     *
     * @param config Device Config
     * @return list of the device config ports
     */
    private static List<DeviceConfigPort> devicePorts(DeviceConfig config) {
        List<DeviceConfigPort> ports = new ArrayList<>(config.getPorts());
        if (config.getManagementPort() != null && !config.getPorts().contains(config.getManagementPort())) {
            ports.add(config.getManagementPort());
        }
        return ports;
    }
}
