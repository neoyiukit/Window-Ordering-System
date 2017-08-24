package com.oocode;

import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * Created by neo.yiu on 17/08/2017.
 */
public class ResponseBodyBuilder {
    public static ResponseBody printResponseBody(Response response) throws IOException {
        ResponseBody responsebody = response.body();
        if (responsebody != null) // throwing IOException is equivalent to "assert body != null"
            return responsebody;
        else
            throw new IOException();
    }
}
