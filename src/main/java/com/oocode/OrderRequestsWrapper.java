package com.oocode;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public class OrderRequestsWrapper implements OrderRequestsWrapperInterface {

    @Override
    public Request getRequest() throws Exception {
        Request request = RequestBuilder.placeAnyOrder(OrderInfoHandler.getRequestBody(), OrderInfoHandler.getOrderURL());
        return request;
    }

    @Override
    public Response getResponse() throws Exception {
        Response response = ResponseBuilder.responseGenerator(getRequest(), OrderInfoHandler.getClient());
        return response;
    }

    @Override
    public ResponseBody getResponseBody() throws Exception {
        ResponseBody responseBody = ResponseBodyBuilder.printResponseBody(getResponse());
        return responseBody;
    }

    @Override
    public String getResponseMessage() throws Exception {
        String responseMessage = ResponseMessage.returnResponseMessage(getResponseBody());
        return responseMessage;
    }
}
