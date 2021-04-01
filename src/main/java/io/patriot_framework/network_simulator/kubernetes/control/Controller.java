package io.patriot_framework.network_simulator.kubernetes.control;

import io.patriot_framework.network.simulator.api.model.network.Network;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;

public interface Controller {

    void createNetwork(Network network);

    void destroyNetwork(Network network);

    void deployDevice(KubeDevice device);

    void destroyDevice(KubeDevice device);

    void deviceIsSeenBy(KubeDevice device, String cidr);

    void connectDevicesOneWay(KubeDevice source, KubeDevice target);

    void connectDevicesBothWays(KubeDevice device, KubeDevice device2);

    void connectNetworksBothWays(Network network, Network network2);

    void connectDeviceToNetworkOneWay(KubeDevice source, Network target);

    void connectDeviceToNetworkBothWays(KubeDevice device, Network network);

    void connectNetworkToDeviceOneWay(Network source, KubeDevice target);

    void connectNetworkToDeviceBothWays(Network network, KubeDevice device);
}
