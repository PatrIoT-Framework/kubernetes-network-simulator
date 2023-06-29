package io.patriot_framework.network_simulator.kubernetes.utils.request_bin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.patriot_framework.network_simulator.kubernetes.utils.HttpClient;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Class representing request bin object of https://webhook.site website.
 * It provides a service where we can send HTTP requests, the requests
 * are stored, and we can collect them afterwards.
 */
public class RBImplWebHook implements RequestBin {
    private static final String REQUEST_BIN_URL = "https://webhook.site";
    private static final String TOKEN_SUFFIX = "/token";
    private final String uuid;
    OkHttpClient client = new OkHttpClient.Builder().build();
    private final Gson gson = new Gson();

    public RBImplWebHook() throws IOException {
        Request request = new Request
                .Builder()
                .post(RequestBody.create(HttpClient.JSON, ""))
                .url(REQUEST_BIN_URL + TOKEN_SUFFIX)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();

            RBImplWebHook.Token token =
                    gson.fromJson(Objects.requireNonNull(responseBody).string(), RBImplWebHook.Token.class);
            this.uuid = token.getUuid();
        }
    }

    public String url() {
        return String.format("%s/%s", REQUEST_BIN_URL, uuid);
    }

    public List<RequestBinResult> getLatestResults() throws IOException {
        Request request = new Request
                .Builder()
                .get()
                .url(String.format("%s%s/%s/requests", REQUEST_BIN_URL, TOKEN_SUFFIX, uuid))
                .build();
        String responseData;
        try (Response response = client.newCall(request).execute()) {
            responseData = Objects.requireNonNull(response.body()).string();
        }
        JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
        MyRequestBinResult[] results =
                gson.fromJson(jsonObject.getAsJsonArray("data"), MyRequestBinResult[].class);

        RequestBinResult[] mappedResults = Stream.of(results)
                .map(o -> new RequestBinResult(o.getContent()))
                .toArray(RequestBinResult[]::new);

        return new ArrayList<>(Arrays.asList(mappedResults));
    }

    private static class Token {
        private String uuid;

        public Token(String name) {
            this.uuid = name;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    /**
     * Represents once object retrieved from webhook.site.
     */
    private static class MyRequestBinResult {
        private String content;

        /**
         * Constructor with body parameter
         *
         * @param content content from retrieved data
         */
        public MyRequestBinResult(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setBody(String content) {
            this.content = content;
        }
    }
}
