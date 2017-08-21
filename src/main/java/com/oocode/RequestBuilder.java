package com.oocode;

import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {
    public static Request placeLargeOrder (RequestBody requestBody){
        return new Request.Builder()
                .url("https://immense-fortress-19979.herokuapp.com/large-order")
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();
    }

    public static Request placeSmallOrder (RequestBody requestBody){
        return new Request.Builder()
                .url("https://immense-fortress-19979.herokuapp.com/order")
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();
    }
}
