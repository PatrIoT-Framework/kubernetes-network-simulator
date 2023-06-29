package io.patriot_framework.network_simulator.kubernetes.utils.request_bin;

import java.io.IOException;
import java.util.List;

public interface RequestBin {
    public String url();

    /**
     * Returns list of received webhooks
     *
     * @return List of RequestBinResults
     */
    public List<RequestBinResult> getLatestResults() throws IOException;
}
