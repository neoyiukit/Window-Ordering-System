package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class ResponseBuilder {
    public static void responseBuilder(Request request, OkHttpClient client) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            try (ResponseBody body = response.body()) {
                assert body != null;
                System.out.println(body.string());
            }
        }
    }
}