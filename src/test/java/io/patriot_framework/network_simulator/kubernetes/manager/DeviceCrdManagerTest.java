package io.patriot_framework.network_simulator.kubernetes.manager;

import io.fabric8.kubernetes.api.model.ContainerPortBuilder;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.PodSpecBuilder;
import io.fabric8.kubernetes.api.model.ServicePortBuilder;
import io.fabric8.kubernetes.api.model.ServiceSpecBuilder;
import io.patriot_framework.network_simulator.kubernetes.Utils;
import io.patriot_framework.network_simulator.kubernetes.crd.device.DeviceCrd;
import io.patriot_framework.network_simulator.kubernetes.crd.builders.DeviceCrdBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.builders.DeviceSpecBuilder;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeviceCrdManagerTest extends AbstractManagerTest {
    private static final String NETWORK_NAME = "my-network-for-device";
    private static final String DEVICE_NAME = "my-device";


    /**
     * Clean up of network will also clean up every device in the network
     */
    @AfterAll
    public static void cleanUp() {
        NetworkCrd networkCrd = kubernetesManager.networkCrd().get(NETWORK_NAME);
        if (networkCrd != null) {
            kubernetesManager.networkCrd().delete(networkCrd);
        }
        DeviceCrd deviceCrd = kubernetesManager.deviceCrd().get(DEVICE_NAME);
        kubernetesManager.deviceCrd().delete(deviceCrd);
    }

    @Test
    public void testCreateDevice() throws InterruptedException {
        NetworkCrd networkCrd = new NetworkCrd();
        networkCrd.getMetadata().setName(NETWORK_NAME);
        kubernetesManager.networkCrd().create(networkCrd);
        Thread.sleep(5000);
        assertNotNull(Utils.getNamespaceByName(kubernetesClient, NETWORK_NAME));


        DeviceCrd deviceCrd = new DeviceCrdBuilder()
                .withName(DEVICE_NAME)
                .withSpec(new DeviceSpecBuilder()
                        .withNetworkName(NETWORK_NAME)
                        .withPodSpec(new PodSpecBuilder()
                                .addNewContainer()
                                .withName("patriot-data-generator-runner")
                                .withImage("jsmadis/patriot-data-generator-runner:latest")
                                .withPorts(new ContainerPortBuilder()
                                        .withContainerPort(8080)
                                        .build())
                                .withEnv(new EnvVarBuilder()
                                        .withName("PATRIOT_DATA_GENERATOR_DEVICE_FILE")
                                        .withValue("/var/test.json")
                                        .build())
                                .and()
                                .build())
                        .withServiceSpec(new ServiceSpecBuilder()
                                .withType("NodePort")
                                .withPorts(new ServicePortBuilder()
                                        .withProtocol("UDP")
                                        .withPort(5683)
                                        .withTargetPort(new IntOrString(5683))
                                        .build())
                                .build())
                        .build())
                .build();

        kubernetesManager.deviceCrd().create(deviceCrd);

        Thread.sleep(50000);

        assertNotNull(kubernetesManager.getDevicePod(deviceCrd));


    }
}
