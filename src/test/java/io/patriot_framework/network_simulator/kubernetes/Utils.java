package io.patriot_framework.network_simulator.kubernetes;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;

public class Utils {
    public static final String HTTP_COAP_TESTING_APP_IMAGE = "jsmadis/patriot-http-coap-testing-app:latest";

    public static Namespace getNamespaceByName(KubernetesClient client, String name) {
        return client.namespaces().withName(name).get();
    }

    public static String httpTestingHostname(KubeDevice device, String path) {
        return String.format("http://%s:%s%s", device.getPublicIpAddress(), device.getManagementPort().getPort(), path);
    }
}
