package io.patriot_framework.network_simulator.kubernetes.controller;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.patriot_framework.generator.controll.client.CoapControlClient;
import io.patriot_framework.generator.dataFeed.DataFeed;
import io.patriot_framework.generator.dataFeed.NormalDistVariateDataFeed;
import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.impl.basicSensors.Thermometer;
import io.patriot_framework.network.simulator.api.model.network.Network;
import io.patriot_framework.network_simulator.kubernetes.control.Controller;
import io.patriot_framework.network_simulator.kubernetes.control.KubernetesController;
import io.patriot_framework.network_simulator.kubernetes.device.DataGenerator;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.manager.KubernetesManager;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class KubernetesControllerTest {
    private static Controller controller;
    private static final String NETWORK_NAME = "kubernetes-controller-network";
    private static KubeDevice kubeDevice;

    @BeforeAll
    public static void setup() {
        Config config = new ConfigBuilder().withMasterUrl("https://192.168.49.2:8443").build();
        controller = new KubernetesController(new KubernetesManager(new DefaultKubernetesClient(config)));
    }

    @AfterAll
    public static void cleanUp() {
        controller.destroyNetwork(new KubeNetwork(NETWORK_NAME));
        controller.destroyDevice(kubeDevice);
    }

    @Test
    public void createDataGeneratorDeviceTest() throws ConnectorException, IOException {
        Network network = new KubeNetwork(NETWORK_NAME);
        controller.createNetwork(network);

        DataFeed dataFeed = new NormalDistVariateDataFeed(18, 2);
        Device device = new Thermometer("simpleThermometer", dataFeed);

        kubeDevice = new DataGenerator("simple-thermometer", network, device);

        controller.deployDevice(kubeDevice);

        controller.deviceIsSeenBy(kubeDevice, "192.168.49.1/24");

        CoapControlClient client = new CoapControlClient(
                String.format("coap://%s:%s",
                        kubeDevice.getManagementIdAddress(),
                        kubeDevice.getManagementPort().getPort()));

        assertNotNull(client.get("/sensor/simple-thermometer"));
    }
}
