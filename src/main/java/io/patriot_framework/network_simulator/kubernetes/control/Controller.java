package io.patriot_framework.network_simulator.kubernetes.control;

import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.exceptions.KubernetesSimulationException;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;

/**
 * Class represents an interface which defines methods used for controlling network simulation.
 */
public interface Controller {

    /**
     * Creates the given KubeNetwork in the Kubernetes
     *
     * @param network KubeNetwork which will be created
     */
    void createNetwork(KubeNetwork network);


    /**
     * Destroys given network in the Kubernetes
     *
     * @param network KubeNetwork to destroy
     */
    void destroyNetwork(KubeNetwork network);

    /**
     * Deploys given KubeDevice to the Kubernetes.
     *
     * @param device KubeDevice to deploy
     * @throws KubernetesSimulationException throws if something went wrong during the deployment
     */
    void deployDevice(KubeDevice device) throws KubernetesSimulationException;

    /**
     * Destroys the KubeDevice in the Kubernetes
     *
     * @param device KubeDevice to destroy
     */
    void destroyDevice(KubeDevice device);


    /**
     * Allows the deployed device to be seen by devices which are in the CIDR range.
     *
     * @param device Device which should be seen
     * @param cidr   string representation of the CIDR range of IP addresses
     */
    void deviceIsSeenBy(KubeDevice device, String cidr);

    /**
     * Connects 2 KubeDevices one way, only source can make connection to the target, not the other way around.
     *
     * @param source source KubeDevice
     * @param target target KubeDevice
     */
    void connectDevicesOneWay(KubeDevice source, KubeDevice target);

    /**
     * Connects 2 KubeDevices both ways, both devices can connect to each other.
     *
     * @param device  first KubeDevice
     * @param device2 second KubeDevice
     */
    void connectDevicesBothWays(KubeDevice device, KubeDevice device2);

    /**
     * Connects 2 KubeNetwork both ways, devices in the both network can connect to each other.
     *
     * @param network  first KubeNetwork
     * @param network2 second KubeNetwork
     */
    void connectNetworksBothWays(KubeNetwork network, KubeNetwork network2);

    /**
     * Connects KubeDevice to the KubeNetwork one way, device can connect with any device from KubeNetwork.
     * But any device from the KubeNetwork can not connect to the KubeDevice.
     *
     * @param device  source KubeDevice
     * @param network target KubeNetwork
     */
    void connectDeviceToNetworkOneWay(KubeDevice device, KubeNetwork network);

    /**
     * Connects KubeDevice and KubeNetwork both ways. Both entities can communicate to each other.
     *
     * @param device  KubeDevice
     * @param network KubeNetwork
     */
    void connectDeviceToNetworkBothWays(KubeDevice device, KubeNetwork network);

    /**
     * Connects KubeNetwork To the KubeDevice one way, any device from the KubeNetwork can connect to the KubeDevice.
     * But, the KubeDevice can not connect to any of the devices inside KubeNetwork.
     *
     * @param source
     * @param target
     */
    void connectNetworkToDeviceOneWay(KubeNetwork source, KubeDevice target);
}
