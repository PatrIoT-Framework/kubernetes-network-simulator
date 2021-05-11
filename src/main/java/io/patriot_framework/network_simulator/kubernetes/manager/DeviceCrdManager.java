package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceList;

/**
 * Class representing custom resource of device deployed inside simulated network.
 */
public class DeviceCrdManager implements CrdManager<DeviceCrd> {
    private final NonNamespaceOperation<DeviceCrd, DeviceList, Resource<DeviceCrd>> deviceCrdClient;

    /**
     * Constructor for DeviceCrdManager
     *
     * @param client instance of KubernetesClient
     */
    public DeviceCrdManager(KubernetesClient client) {
        deviceCrdClient = client.customResources(DeviceCrd.class, DeviceList.class);
    }

    @Override
    public DeviceCrd create(DeviceCrd crd) {
        return deviceCrdClient.create(crd);
    }

    @Override
    public DeviceCrd update(DeviceCrd crd) {
        return deviceCrdClient.createOrReplace(crd);
    }

    @Override
    public boolean delete(DeviceCrd crd) {
        return deviceCrdClient.delete(crd);
    }

    @Override
    public boolean delete(String name) {
        return delete(get(name));
    }

    @Override
    public boolean deleteAll() {
        return deviceCrdClient.delete(deviceCrdClient.list().getItems());
    }

    @Override
    public DeviceCrd get(String name) {
        return deviceCrdClient
                .withName(name)
                .get();
    }
}
