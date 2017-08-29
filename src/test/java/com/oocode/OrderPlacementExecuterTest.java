package com.oocode;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by neo.yiu on 25/08/2017.
 */
public class OrderPlacementExecuterTest {
    @Test(expected = org.mockito.exceptions.misusing.InvalidUseOfMatchersException.class)
    public void testingIfRequestBodyBuilderGetsCalledAtLeastOnce() throws Exception{

        // GIVEN
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(5, 119, 1000, "Churchill", "test");
        OrderRequestsWrapper orderRequestsWrapper = new OrderRequestsWrapper(orderInfoHandler);
        RequestBodyBuilder mockRequestBodyBuilder = mock(RequestBodyBuilder.class);
        RequestBody requestBody = new RequestBody() { // requestBody couldn't be instantiated unless the RequestBody data type is not "abstract" in nature meaning all the constituent methods must not be abstract
            @Override
            public MediaType contentType() {
                // there is an abstract method of contentType() and should be overridden with dummy "return mediaType" which is null
                MediaType mediaType = null;
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                // there is an abstract method of writeTo() so it should be overridden with empty content to get rid of the abstract status
            }
        };

        // WHEN
        when(mockRequestBodyBuilder.bodyBuilderForAnyOrders(anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(requestBody);
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, orderRequestsWrapper);

        // THEN
        verify(mockRequestBodyBuilder, atLeastOnce()).bodyBuilderForAnyOrders(OrderInfoHandler.getWidthOfWindow(), OrderInfoHandler.getHeightOfWindow(), OrderInfoHandler.getNumberOfWindow(), OrderInfoHandler.getWidthThicknessAllowance(), OrderInfoHandler.getHeightThicknessAllowance(), OrderInfoHandler.getGlassType(), OrderInfoHandler.getUserName());
        assertEquals(orderInfoHandler.getRequestBody(), requestBody);
    }

    @Test(expected = org.mockito.exceptions.misusing.InvalidUseOfMatchersException.class)
    public void testingIfRequestBodyBuilderMethodCouldBeSkipped() throws Exception{

        // GIVEN
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(5, 119, 1000, "Churchill", "test");
        OrderRequestsWrapper orderRequestsWrapper = new OrderRequestsWrapper(orderInfoHandler);
        RequestBodyBuilder mockRequestBodyBuilder = mock(RequestBodyBuilder.class);
        RequestBody requestBody = new RequestBody() { // requestBody couldn't be instantiated unless the RequestBody data type is not "abstract" in nature meaning all the constituent methods must not be abstract
            @Override
            public MediaType contentType() {
                // there is an abstract method of contentType() and should be overridden with dummy "return mediaType" which is null
                MediaType mediaType = null;
                return mediaType;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                // there is an abstract method of writeTo() so it should be overridden with empty content to get rid of the abstract status
            }
        };

        // WHEN
        when(mockRequestBodyBuilder.bodyBuilderForAnyOrders(anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(requestBody);
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, orderRequestsWrapper);

        // THEN
        verify(mockRequestBodyBuilder, times(0)).bodyBuilderForAnyOrders(OrderInfoHandler.getWidthOfWindow(), OrderInfoHandler.getHeightOfWindow(), OrderInfoHandler.getNumberOfWindow(), OrderInfoHandler.getWidthThicknessAllowance(), OrderInfoHandler.getHeightThicknessAllowance(), OrderInfoHandler.getGlassType(), OrderInfoHandler.getUserName());
        assertEquals(orderInfoHandler.getRequestBody(), requestBody);
    }
}
