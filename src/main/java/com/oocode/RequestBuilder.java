package com.oocode;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {
    public static Request placeAnyOrder (RequestBody requestBody, String orderURL){
        return new Request.Builder()
                .url(orderURL)
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();
    }
}
