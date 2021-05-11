package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.Service;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.exceptions.WaitTimeExceededException;

import java.util.Map;

/**
 * Interface representing manager that is responsible of working with kubernetes,
 * It provides methods for working with custom CRD and manipulating with Kubernetes API.
 */
public interface Manager {

    /**
     * Returns CrdManager for the NetworkCrd. The CrdManager provides an interface to work with NetworkCrd objects.
     *
     * @return instance of the NetworkCrd manager
     */
    CrdManager<NetworkCrd> networkCrd();

    /**
     * Returns CrdManager for the DeviceCrd. The CrdManager provides an interface to work with DeviceCrd objects.
     *
     * @return instance of the DeviceCrd manager
     */
    CrdManager<DeviceCrd> deviceCrd();

    /**
     * Gets Pod object which is created for the DeviceCrd.
     * {@link io.fabric8.kubernetes.api.model.Pod}
     *
     * @param deviceCrd DeviceCrd instance
     * @return Pod object of given DeviceCrd
     */
    Pod getDevicePod(DeviceCrd deviceCrd);

    /**
     * Gets Service object which is created for the DeviceCrd.
     * {@link io.fabric8.kubernetes.api.model.Service}
     *
     * @param deviceCrd DeviceCrd instance
     * @return Service object of given DeviceCrd
     */
    Service getDeviceService(DeviceCrd deviceCrd);

    /**
     * Creates config map on Kubernetes cluster of given parameters.
     *
     * @param data      the map that contains key as the name of the file and value as contents of the file
     * @param namespace namespace name where should be the ConfigMap created
     * @param name      name of the config map
     * @return Instance of created config map {@link io.fabric8.kubernetes.api.model.ConfigMap}
     */
    ConfigMap createConfigMap(Map<String, String> data, String namespace, String name);

    /**
     * Deletes config map of given namespace and name.
     *
     * @param namespace namespace name where the config map is located
     * @param name      name of the config map
     * @return true if the call was successful, false otherwise
     */
    boolean deleteConfigMap(String namespace, String name);


    /**
     * Waits for pod to be created.
     *
     * @param deviceCrd DeviceCrd which is creating the pod.
     * @throws WaitTimeExceededException If the waiting for the pod to create fails,
     *                                   it will throw WaitTimeExceededException
     */
    void waitPodCreation(DeviceCrd deviceCrd) throws WaitTimeExceededException;
}
