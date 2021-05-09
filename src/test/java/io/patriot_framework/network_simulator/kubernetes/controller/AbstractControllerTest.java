package io.patriot_framework.network_simulator.kubernetes.controller;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.patriot_framework.network_simulator.kubernetes.control.Controller;
import io.patriot_framework.network_simulator.kubernetes.control.KubernetesController;
import io.patriot_framework.network_simulator.kubernetes.manager.KubernetesManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractControllerTest {
    protected static Controller controller;
    private static KubernetesManager manager;

    @BeforeAll
    public static void setup() {
        Config config = new ConfigBuilder().withMasterUrl("https://192.168.49.2:8443").build();
        KubernetesClient client = new DefaultKubernetesClient(config);
        controller = new KubernetesController(new KubernetesManager(client));
        manager = new KubernetesManager(client);
    }

    @AfterEach
    public void cleanUp() throws InterruptedException {
        manager.deviceCrd().deleteAll();
        manager.networkCrd().deleteAll();
        Thread.sleep(90000);
    }
}
