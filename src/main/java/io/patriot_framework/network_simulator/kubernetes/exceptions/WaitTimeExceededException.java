package io.patriot_framework.network_simulator.kubernetes.exceptions;

/**
 * Exception representing that wait time exceeded given limit.
 */
public class WaitTimeExceededException extends Exception {
    /**
     * Constructor with message
     *
     * @param message message of the exception
     */
    public WaitTimeExceededException(String message) {
        super(message);
    }

}
