package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ResponseBuilder {
    public static Response responseGenerator(Request request, OkHttpClient client) throws IOException {
        Response response = client.newCall(request).execute(); // newCall() needs IOException
        return response;
    }
}