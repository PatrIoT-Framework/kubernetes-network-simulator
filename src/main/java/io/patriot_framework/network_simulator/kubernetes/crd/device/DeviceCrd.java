package io.patriot_framework.network_simulator.kubernetes.crd.device;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import io.patriot_framework.network_simulator.kubernetes.crd.Crd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkSpec;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkStatus;

@Version(NetworkCrd.VERSION)
@Group(NetworkCrd.GROUP)
@Kind(NetworkCrd.KIND)
public class DeviceCrd extends CustomResource<NetworkSpec, NetworkStatus> implements Crd {
    public static final String GROUP = "network-simulator.patriot-framework.io";
    public static final String VERSION = "v1";
    public static final String KIND = "Device";
}
