package io.patriot_framework.network_simulator.kubernetes.controller;

import io.patriot_framework.generator.dataFeed.DataFeed;
import io.patriot_framework.generator.dataFeed.NormalDistVariateDataFeed;
import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.impl.basicSensors.Thermometer;
import io.patriot_framework.network.simulator.api.model.network.Network;
import io.patriot_framework.network_simulator.kubernetes.HttpClient;
import io.patriot_framework.network_simulator.kubernetes.Utils;
import io.patriot_framework.network_simulator.kubernetes.device.Application;
import io.patriot_framework.network_simulator.kubernetes.device.DataGenerator;
import io.patriot_framework.network_simulator.kubernetes.device.DeviceConfig;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.SocketTimeoutException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NetworkRestrictionTest extends AbstractControllerTest {
    private Network deviceNetwork;
    private KubeDevice kubeDevice;
    private HttpClient httpClient = new HttpClient();

    @BeforeEach
    public void setupTest() {
        deviceNetwork = new KubeNetwork("my-nice-network");
        controller.createNetwork(deviceNetwork);

        DataFeed dataFeed = new NormalDistVariateDataFeed(18, 2);
        Device device = new Thermometer("simpleThermometer", dataFeed);

        kubeDevice = new DataGenerator("simple-thermometer", deviceNetwork, device);
        controller.deployDevice(kubeDevice);
    }


    @Test
    public void deviceCanSeeDeviceInsideNetworkTest() throws IOException {
        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app", deviceNetwork, deviceConfig);

        controller.deployDevice(app);

        controller.deviceIsSeenBy(app, "192.168.49.1/24");

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

    @Test
    public void deviceCanSeeDeviceInOtherNetwork() throws IOException {
        Network anotherNetwork = new KubeNetwork("another-network");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app2", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");

        controller.connectDevicesBothWays(kubeDevice, app);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }


    @Test
    public void deviceCanNotSeeDeviceInOtherNetwork() {
        Network anotherNetwork = new KubeNetwork("another-network1");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app3", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");


        String hostname = Utils.httpTestingHostname(app, "/get");

        assertThrows(SocketTimeoutException.class, () -> {
            httpClient.get(hostname,
                    kubeDevice.getPrivateIpAddress(),
                    5683,
                    "/sensor/simpleThermometer");
        });
    }


    @Test
    public void networksCanSeeToEachOther() throws IOException, InterruptedException {
        Network anotherNetwork = new KubeNetwork("another-network12");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app3", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");

        controller.connectNetworksBothWays(deviceNetwork, anotherNetwork);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

    @Test
    public void networkWontSeeEachOther() throws InterruptedException, IOException {
        Network anotherNetwork = new KubeNetwork("another-network13");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app4", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");



        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertFalse(result.contains("Thermometer"), result);
    }

    @Test
    public void deviceCanSeeOneWay() throws IOException, InterruptedException {
        Network anotherNetwork = new KubeNetwork("another-network14");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app5", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");

        controller.connectDevicesOneWay(app, kubeDevice);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

    @Test
    public void deviceWontSeeOneWay() throws IOException, InterruptedException {
        Network anotherNetwork = new KubeNetwork("another-network14");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app5", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");

        controller.connectDevicesOneWay(kubeDevice, app);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertFalse(result.contains("Thermometer"), result);
    }


    @Test
    public void deviceCanSeeInsideNetwork() throws IOException, InterruptedException {
        Network anotherNetwork = new KubeNetwork("another-network15");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app6", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, "192.168.49.1/24");

        controller.connectDeviceToNetworkOneWay(app, deviceNetwork);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

}
