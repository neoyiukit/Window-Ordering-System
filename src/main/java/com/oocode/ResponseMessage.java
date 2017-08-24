package com.oocode;

import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public class ResponseMessage {
    public static String returnResponseMessage(ResponseBody responseBody) throws IOException {
        String responseMessage = responseBody.string();
        System.out.println(responseMessage);
        if(responseBody != null)
            return responseMessage;
        throw new IOException();
    }
}
