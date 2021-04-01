package io.patriot_framework.network_simulator.kubernetes.control.utils;

import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyPort;
import io.patriot_framework.network_simulator.kubernetes.crd.Ports;
import io.patriot_framework.network_simulator.kubernetes.crd.builders.PortBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;

import java.util.ArrayList;
import java.util.List;

public class ConnectionUtils {

    public static void connectDeviceToNetwork(DeviceCrd source, NetworkCrd target) {
        connectDeviceToNetwork(source, target, new ArrayList<>(), new ArrayList<>());
    }

    public static void connectDeviceToNetwork(DeviceCrd source, NetworkCrd target,
                                              List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getDeviceEgressPorts()
                .add(buildPorts(null,
                        target.getMetadata().getName(),
                        targetPorts));

        target.getSpec()
                .getNetworkIngressPorts()
                .add(buildPorts(source.getMetadata().getName(),
                        source.getSpec().getNetworkName(),
                        sourcePorts));
    }


    public static void connectNetworks(NetworkCrd source, NetworkCrd target) {
        connectNetworks(source, target, new ArrayList<>(), new ArrayList<>());
    }

    public static void connectNetworks(NetworkCrd source, NetworkCrd target,
                                       List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getNetworkEgressPorts()
                .add(buildPorts(null,
                        target.getMetadata().getName(),
                        targetPorts));
        target.getSpec()
                .getNetworkIngressPorts()
                .add(buildPorts(null,
                        source.getMetadata().getName(),
                        sourcePorts));
    }


    public static void connectDevices(DeviceCrd source, DeviceCrd target) {
        connectDevices(source, target, new ArrayList<>(), new ArrayList<>());
    }


    public static void connectDevices(DeviceCrd source, DeviceCrd target,
                                      List<NetworkPolicyPort> sourcePorts, List<NetworkPolicyPort> targetPorts) {
        source.getSpec()
                .getDeviceEgressPorts()
                .add(buildPorts(target.getMetadata().getName(),
                        target.getSpec().getNetworkName(),
                        targetPorts));
        target.getSpec()
                .getDeviceIngressPorts()
                .add(buildPorts(source.getMetadata().getName(),
                        source.getSpec().getNetworkName(),
                        sourcePorts));
    }


    private static Ports buildPorts(String deviceName, String networkName, List<NetworkPolicyPort> targetPorts) {
        return new PortBuilder()
                .withDeviceName(deviceName)
                .withNetworkName(networkName)
                .withNetworkPolicyPorts(targetPorts)
                .build();
    }
}
