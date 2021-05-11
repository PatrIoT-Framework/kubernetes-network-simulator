package io.patriot_framework.network_simulator.kubernetes.crd.device;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;

/**
 * Class representing Device custom resource definition.
 */
@Version(DeviceCrd.VERSION)
@Group(DeviceCrd.GROUP)
@Kind(DeviceCrd.KIND)
public class DeviceCrd extends CustomResource<DeviceSpec, DeviceStatus> {
    public static final String GROUP = "network-simulator.patriot-framework.io";
    public static final String VERSION = "v1";
    public static final String KIND = "Device";


    /**
     * Returns name of the pod, which is created by the operator.
     *
     * @return name of the pod
     */
    public String podName() {
        return getMetadata().getName() + "-pod";
    }

    /**
     * Returns name of the service, which is created by the operator.
     *
     * @return name of the service
     */
    public String serviceName() {
        return getMetadata().getName() + "-service";
    }
}
