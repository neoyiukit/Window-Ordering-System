package com.oocode;

/**
 * Created by neo.yiu on 24/08/2017.
 */
public class OrderPlacementExecuter {
    public static void orderRequestGenerator(OrderInfoHandler orderInfoHandler, OrderRequestsWrapper orderRequestsWrapper) throws Exception {
        orderInfoHandler.getOrderURL();
        orderInfoHandler.getRequestBody();
        orderRequestsWrapper.getRequest();
        orderRequestsWrapper.getResponse();
        orderRequestsWrapper.getResponseBody();
        orderRequestsWrapper.getResponseMessage();
    }
}
