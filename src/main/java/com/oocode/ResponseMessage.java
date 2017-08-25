package com.oocode;

import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public class ResponseMessage {
    public static String returnResponseMessage(ResponseBody responseBody) throws IOException {
        String responseMessage = responseBody.string();
//        System.out.println(responseMessage); // good to have in order to print the response messages for comparison of testing results
        if(responseBody != null)
            return responseMessage;
        throw new IOException();
    }
}
