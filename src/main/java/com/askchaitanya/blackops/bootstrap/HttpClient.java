package com.askchaitanya.blackops.bootstrap;

import okhttp3.OkHttpClient;

public class HttpClient {

    private volatile static OkHttpClient client = null;

    private HttpClient() {
    }

    public static OkHttpClient getClient() {
        if (client == null) {
            synchronized (HttpClient.class) {
                if (client == null) {
                    client = new OkHttpClient();
                }
            }
        }
        return client;
    }
}