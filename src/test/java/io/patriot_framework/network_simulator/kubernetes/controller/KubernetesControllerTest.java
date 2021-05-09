package io.patriot_framework.network_simulator.kubernetes.controller;

import io.patriot_framework.generator.controll.client.CoapControlClient;
import io.patriot_framework.generator.dataFeed.ConstantDataFeed;
import io.patriot_framework.generator.dataFeed.DataFeed;
import io.patriot_framework.generator.dataFeed.NormalDistVariateDataFeed;
import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.active.Active;
import io.patriot_framework.generator.device.active.ActiveDevice;
import io.patriot_framework.generator.device.impl.basicSensors.Thermometer;
import io.patriot_framework.generator.network.NetworkAdapter;
import io.patriot_framework.generator.network.Rest;
import io.patriot_framework.generator.network.wrappers.JSONWrapper;
import io.patriot_framework.network_simulator.kubernetes.device.ActiveDataGenerator;
import io.patriot_framework.network_simulator.kubernetes.device.DataGenerator;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;
import io.patriot_framework.network_simulator.kubernetes.utils.RequestBin;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KubernetesControllerTest extends AbstractControllerTest {
    private static final String NETWORK_NAME = "kubernetes-controller-network";


    @Test
    public void createDataGeneratorDeviceTest() throws ConnectorException, IOException {
        KubeNetwork network = new KubeNetwork(NETWORK_NAME);
        controller.createNetwork(network);

        DataFeed dataFeed = new NormalDistVariateDataFeed(18, 2);
        Device device = new Thermometer("simpleThermometer", dataFeed);

        KubeDevice kubeDevice = new DataGenerator("simple-thermometer", network, device);

        controller.deployDevice(kubeDevice);

        controller.deviceIsSeenBy(kubeDevice, "192.168.49.1/24");

        CoapControlClient client = new CoapControlClient(
                String.format("coap://%s:%s",
                        kubeDevice.getPublicIpAddress(),
                        kubeDevice.getManagementPort().getPort()));

        assertTrue(new String(client.get("/sensor/simpleThermometer").getPayload()).contains("Thermometer"));
    }


    @Test
    public void createActiveDataGeneratorDeviceTest() throws IOException, InterruptedException {
        RequestBin requestBin = new RequestBin();

        KubeNetwork network = new KubeNetwork("network123");
        controller.createNetwork(network);

        DataFeed df = new NormalDistVariateDataFeed(18, 2);
        Device temperature = new Thermometer("thermometer", df);
        NetworkAdapter na = new Rest(requestBin.url(), new JSONWrapper());
        temperature.setNetworkAdapter(na);

        DataFeed tf = new ConstantDataFeed(2000);
        Active activeDevice = new ActiveDevice(temperature, tf);

        KubeDevice kubeDevice = new ActiveDataGenerator("simple-thermometer", network, activeDevice);

        kubeDevice.getDeviceConfig().setEnableInternet(true);

        controller.deployDevice(kubeDevice);


       Thread.sleep(60000);

       controller.destroyDevice(kubeDevice);

       assertTrue(requestBin
               .getLatestResults()
               .stream()
               .filter(result -> result
                       .getBody()
                       .contains("data"))
               .count() > 4);
    }
}
