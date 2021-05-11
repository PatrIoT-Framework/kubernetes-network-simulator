package io.patriot_framework.network_simulator.kubernetes.controller;

import io.patriot_framework.generator.dataFeed.DataFeed;
import io.patriot_framework.generator.dataFeed.NormalDistVariateDataFeed;
import io.patriot_framework.generator.device.Device;
import io.patriot_framework.generator.device.impl.basicSensors.Thermometer;
import io.patriot_framework.network_simulator.kubernetes.device.Application;
import io.patriot_framework.network_simulator.kubernetes.device.DataGenerator;
import io.patriot_framework.network_simulator.kubernetes.device.DeviceConfig;
import io.patriot_framework.network_simulator.kubernetes.device.KubeDevice;
import io.patriot_framework.network_simulator.kubernetes.network.KubeNetwork;
import io.patriot_framework.network_simulator.kubernetes.utils.HttpClient;
import io.patriot_framework.network_simulator.kubernetes.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NetworkRestrictionTest extends AbstractControllerTest {
    private KubeNetwork deviceNetwork;
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
    public void deviceCanSeeDeviceInsideNetworkTest() throws IOException, InterruptedException {
        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app", deviceNetwork, deviceConfig);

        controller.deployDevice(app);

        controller.deviceIsSeenBy(app, Utils.getLocalIp());

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

    @Test
    public void deviceCanSeeDeviceInOtherNetwork() throws IOException, InterruptedException {
        KubeNetwork anotherNetwork = new KubeNetwork("another-network");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app2", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

        controller.connectDevicesBothWays(kubeDevice, app);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }


    @Test
    public void deviceCanNotSeeDeviceInOtherNetwork() throws IOException, InterruptedException {
        KubeNetwork anotherNetwork = new KubeNetwork("another-network1");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app3", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

        Thread.sleep(10000);

        String hostname = Utils.httpTestingHostname(app, "/get");

        String response = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(response.contains("\"status\":404"));
        assertTrue(response.contains("\"error\":\"Not Found\""));
    }


    @Test
    public void networksCanSeeToEachOther() throws IOException, InterruptedException {
        KubeNetwork anotherNetwork = new KubeNetwork("another-network12");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app3", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

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
        KubeNetwork anotherNetwork = new KubeNetwork("another-network13");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app4", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());


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
        KubeNetwork anotherNetwork = new KubeNetwork("another-network14");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app5", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

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
        KubeNetwork anotherNetwork = new KubeNetwork("another-network14");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app5", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

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
        KubeNetwork anotherNetwork = new KubeNetwork("another-network15");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app6", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

        controller.connectDeviceToNetworkOneWay(app, deviceNetwork);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }


    @Test
    public void deviceCanSeeInsideNetworkBothWays() throws InterruptedException, IOException {
        KubeNetwork anotherNetwork = new KubeNetwork("another-network16");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app7", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

        controller.connectDeviceToNetworkBothWays(app, deviceNetwork);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

    @Test
    public void networkCanSeeDevice() throws InterruptedException, IOException {
        KubeNetwork anotherNetwork = new KubeNetwork("another-network17");
        controller.createNetwork(anotherNetwork);

        DeviceConfig deviceConfig = new DeviceConfig(Utils.HTTP_COAP_TESTING_APP_IMAGE);
        KubeDevice app = new Application("my-app8", anotherNetwork, deviceConfig);
        controller.deployDevice(app);
        controller.deviceIsSeenBy(app, Utils.getLocalIp());

        controller.connectNetworkToDeviceOneWay(anotherNetwork, kubeDevice);

        Thread.sleep(5000);

        String hostname = Utils.httpTestingHostname(app, "/get");
        String result = httpClient.get(hostname,
                kubeDevice.getPrivateIpAddress(),
                5683,
                "/sensor/simpleThermometer");

        assertTrue(result.contains("Thermometer"), result);
    }

}
