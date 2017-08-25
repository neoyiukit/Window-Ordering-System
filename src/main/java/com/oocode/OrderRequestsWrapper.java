package com.oocode;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public class OrderRequestsWrapper implements OrderRequestsWrapperInterface {

    private OrderInfoHandler orderInfoHandler;

    public OrderRequestsWrapper(OrderInfoHandler orderInfoHandler) {
        this.orderInfoHandler = orderInfoHandler;
    }

    @Override
    public Request getRequest() throws Exception {
        Request request = RequestBuilder.placeAnyOrder(orderInfoHandler.getRequestBody(), orderInfoHandler.getOrderURL());
        return request;
    }

    @Override
    public Response getResponse() throws Exception {
        Response response = ResponseBuilder.responseGenerator(getRequest(), orderInfoHandler.getClient());
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
