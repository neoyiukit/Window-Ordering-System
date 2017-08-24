package com.oocode;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public class OrderPlacementHelper {
    public static void orderRequestGeneratpr(OrderInfoHandler orderInfoHandler, OrderRequestsWrapper orderRequestsWrapper) throws Exception {
        orderInfoHandler.getRequestBody();
        orderRequestsWrapper.getRequest();
        orderRequestsWrapper.getResponse();
        orderRequestsWrapper.getResponseBody();
        orderRequestsWrapper.getResponseMessage();
    }
}
