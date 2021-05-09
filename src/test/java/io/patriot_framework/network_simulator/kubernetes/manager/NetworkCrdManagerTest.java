package io.patriot_framework.network_simulator.kubernetes.manager;

import io.patriot_framework.network_simulator.kubernetes.utils.Utils;
import io.patriot_framework.network_simulator.kubernetes.crd.network.NetworkCrd;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NetworkCrdManagerTest extends AbstractManagerTest {
    private static final String NETWORK_NAME = "my-super-network";

    @AfterAll
    public static void cleanUp() {
        NetworkCrd networkCrd = kubernetesManager.networkCrd().get(NETWORK_NAME);
        if (networkCrd != null) {
            kubernetesManager.networkCrd().delete(networkCrd);
        }
    }


    @Test
    @Order(1)
    public void testCreateNetworkCrd() throws InterruptedException {
        NetworkCrd networkCrd = new NetworkCrd();
        networkCrd.getMetadata().setName(NETWORK_NAME);

        kubernetesManager.networkCrd().create(networkCrd);

        Thread.sleep(5000);

        assertNotNull(Utils.getNamespaceByName(kubernetesClient, NETWORK_NAME));
    }

    @Test
    @Order(2)
    public void testDeleteNetworkCrd() throws InterruptedException {
        NetworkCrd networkCrd = kubernetesManager.networkCrd().get(NETWORK_NAME);
        assertNotNull(networkCrd);

        boolean deleteResult = kubernetesManager.networkCrd().delete(networkCrd);
        assertTrue(deleteResult);

        Thread.sleep(10000);

        assertNull(Utils.getNamespaceByName(kubernetesClient, NETWORK_NAME));

        assertNull(kubernetesManager.networkCrd().get(NETWORK_NAME));
    }

}
