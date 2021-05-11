package io.patriot_framework.network_simulator.kubernetes.exceptions;

/**
 * Class representing exception during network simulation.
 */
public class KubernetesSimulationException extends Exception {

    /**
     * Constructor with message
     *
     * @param message message of the exception
     * @param cause   cause of the excetion
     */
    public KubernetesSimulationException(String message, Throwable cause) {
        super(message, cause);
    }

}
