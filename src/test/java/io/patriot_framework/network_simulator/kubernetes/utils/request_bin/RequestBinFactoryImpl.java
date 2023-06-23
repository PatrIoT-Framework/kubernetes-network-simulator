package io.patriot_framework.network_simulator.kubernetes.utils.request_bin;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class RequestBinFactoryImpl implements RequestBinFactory {
    private static final String WEBHOOK_URL = "https://webhook.site";   // default
    private static final String REQUEST_BIN_URL = "https://requestbin.io";
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestBinFactoryImpl.class);

    @Override
    public RequestBin create() throws IOException, NotImplementedException {
        String source = System.getenv("REQUEST_BIN_URL");
        if(source == null) {
            source = WEBHOOK_URL;
        }

        if(source.equals(REQUEST_BIN_URL)) {
            return new RBImplRequestBin();
        } else if(source.equals(WEBHOOK_URL)) {
            return new RBImplWebHook();
        }

        LOGGER.warn(String.format("For given source: %s, does not exist implementation. Returning null", source));
        return null;
    }
}
