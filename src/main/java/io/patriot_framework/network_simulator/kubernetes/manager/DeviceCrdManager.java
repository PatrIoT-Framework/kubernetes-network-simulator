package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceList;

public class DeviceCrdManager implements CrdManager<DeviceCrd> {
    private KubernetesClient client;
    private NonNamespaceOperation<DeviceCrd, DeviceList, Resource<DeviceCrd>> deviceCrdClient;

    public DeviceCrdManager(KubernetesClient client) {
        this.client = client;
        deviceCrdClient = client.customResources(DeviceCrd.class, DeviceList.class);
    }

    @Override
    public DeviceCrd create(DeviceCrd crd) {
        return deviceCrdClient.create(crd);
    }

    @Override
    public DeviceCrd update(DeviceCrd crd) {
        return deviceCrdClient.replace(crd);
    }

    @Override
    public boolean delete(DeviceCrd crd) {
        return deviceCrdClient.delete(crd);
    }

    @Override
    public DeviceCrd get(String name) {
        return deviceCrdClient
                .list()
                .getItems()
                .stream()
                .filter(device -> device
                        .getMetadata()
                        .getName()
                        .equals(name))
                .findFirst()
                .orElse(null);
    }
}
