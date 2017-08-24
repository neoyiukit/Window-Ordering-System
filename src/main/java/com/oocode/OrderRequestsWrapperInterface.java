package com.oocode;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public interface OrderRequestsWrapperInterface {
    Request getRequest() throws Exception;
    Response getResponse() throws Exception;
    ResponseBody getResponseBody() throws Exception;
    String getResponseMessage() throws Exception;
}
