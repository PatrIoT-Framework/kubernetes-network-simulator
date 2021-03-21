package io.patriot_framework.network_simulator.kubernetes.crd.device;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import io.patriot_framework.network_simulator.kubernetes.crd.Crd;

@Version(DeviceCrd.VERSION)
@Group(DeviceCrd.GROUP)
@Kind(DeviceCrd.KIND)
public class DeviceCrd extends CustomResource<DeviceSpec, DeviceStatus> implements Crd {
    public static final String GROUP = "network-simulator.patriot-framework.io";
    public static final String VERSION = "v1";
    public static final String KIND = "Device";


    public String podName(){
        return getMetadata().getName() + "-pod";
    }
}
