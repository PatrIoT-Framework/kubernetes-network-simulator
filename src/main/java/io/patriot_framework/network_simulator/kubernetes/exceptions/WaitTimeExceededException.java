package io.patriot_framework.network_simulator.kubernetes.exceptions;

public class WaitTimeExceededException extends RuntimeException {
    public WaitTimeExceededException() {
    }

    public WaitTimeExceededException(String message) {
        super(message);
    }

    public WaitTimeExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public WaitTimeExceededException(Throwable cause) {
        super(cause);
    }

    public WaitTimeExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
