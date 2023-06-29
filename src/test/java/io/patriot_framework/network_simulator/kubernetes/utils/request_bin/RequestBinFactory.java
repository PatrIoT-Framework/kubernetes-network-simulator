package io.patriot_framework.network_simulator.kubernetes.utils.request_bin;

import org.apache.commons.lang3.NotImplementedException;
import java.io.IOException;

public interface RequestBinFactory {
    RequestBin create() throws IOException, NotImplementedException;
}
