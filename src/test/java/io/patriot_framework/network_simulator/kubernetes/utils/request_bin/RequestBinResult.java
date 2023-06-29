package io.patriot_framework.network_simulator.kubernetes.utils.request_bin;

/**
 * Represents once object retrieved from request bin.
 */
public class RequestBinResult {
    private String body;

    /**
     * Constructor with body parameter
     *
     * @param body request body from requestBin data
     */
    public RequestBinResult(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
