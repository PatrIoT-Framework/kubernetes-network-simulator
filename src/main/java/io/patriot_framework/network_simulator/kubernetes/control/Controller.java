package io.patriot_framework.network_simulator.kubernetes.control;

import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.exceptions.KubernetesSimulationException;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

public interface Controller {

    void createNetwork(KubeNetwork network);

    void destroyNetwork(KubeNetwork network);

    void deployDevice(KubeDevice device) throws KubernetesSimulationException;

    void destroyDevice(KubeDevice device);

    void deviceIsSeenBy(KubeDevice device, String cidr);

    void connectDevicesOneWay(KubeDevice source, KubeDevice target);

    void connectDevicesBothWays(KubeDevice device, KubeDevice device2);

    void connectNetworksBothWays(KubeNetwork network, KubeNetwork network2);

    void connectDeviceToNetworkOneWay(KubeDevice device, KubeNetwork network);

    void connectDeviceToNetworkBothWays(KubeDevice device, KubeNetwork network);

    void connectNetworkToDeviceOneWay(KubeNetwork source, KubeDevice target);
}
