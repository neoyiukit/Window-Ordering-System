package com.oocode;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by neo.yiu on 25/08/2017.
 */
public class OrderRequestWrapperTest {

    @Test
    public void testingIfEachOfTheMethodsInOrderRequestsWrapperGotCalledOnce() throws Exception {
        // GIVEN
        OrderRequestsWrapper mockOrderRequestsWrapper = mock(OrderRequestsWrapper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");

        // WHEN
        when(mockOrderRequestsWrapper.getRequest()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponse()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseBody()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, mockOrderRequestsWrapper);

        // THEN
        verify(mockOrderRequestsWrapper, times(1)).getRequest();
        verify(mockOrderRequestsWrapper, times(1)).getResponse();
        verify(mockOrderRequestsWrapper, times(1)).getResponseBody();
        verify(mockOrderRequestsWrapper, times(1)).getResponseMessage();
    }

    @Test(expected = org.mockito.exceptions.verification.TooLittleActualInvocations.class)
     public void testingIfEachOfTheMethodsInOrderRequestsWrapperGotCalledMoreThanOnce() throws Exception {
        // GIVEN
        OrderRequestsWrapper mockOrderRequestsWrapper = mock(OrderRequestsWrapper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");

        // WHEN
        when(mockOrderRequestsWrapper.getRequest()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponse()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseBody()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, mockOrderRequestsWrapper);

        // THEN
        verify(mockOrderRequestsWrapper, times(2)).getRequest();
        verify(mockOrderRequestsWrapper, times(2)).getResponse();
        verify(mockOrderRequestsWrapper, times(2)).getResponseBody();
        verify(mockOrderRequestsWrapper, times(2)).getResponseMessage();
    }

    @Test(expected = org.mockito.exceptions.verification.NeverWantedButInvoked.class)
    public void testingIfGetRequestCouldBeSkipped() throws Exception {
        // GIVEN
        OrderRequestsWrapper mockOrderRequestsWrapper = mock(OrderRequestsWrapper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");

        // WHEN
        when(mockOrderRequestsWrapper.getRequest()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponse()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseBody()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, mockOrderRequestsWrapper);

        // THEN
        verify(mockOrderRequestsWrapper, times(0)).getRequest();
        verify(mockOrderRequestsWrapper, times(1)).getResponse();
        verify(mockOrderRequestsWrapper, times(1)).getResponseBody();
        verify(mockOrderRequestsWrapper, times(1)).getResponseMessage();
    }

    @Test(expected = org.mockito.exceptions.verification.NeverWantedButInvoked.class)
    public void testingIfGetResponseCouldBeSkipped() throws Exception {
        // GIVEN
        OrderRequestsWrapper mockOrderRequestsWrapper = mock(OrderRequestsWrapper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");

        // WHEN
        when(mockOrderRequestsWrapper.getRequest()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponse()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseBody()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, mockOrderRequestsWrapper);

        // THEN
        verify(mockOrderRequestsWrapper, times(1)).getRequest();
        verify(mockOrderRequestsWrapper, times(0)).getResponse();
        verify(mockOrderRequestsWrapper, times(1)).getResponseBody();
        verify(mockOrderRequestsWrapper, times(1)).getResponseMessage();
    }

    @Test(expected = org.mockito.exceptions.verification.NeverWantedButInvoked.class)
    public void testingIfGetResponseBodyCouldBeSkipped() throws Exception {
        // GIVEN
        OrderRequestsWrapper mockOrderRequestsWrapper = mock(OrderRequestsWrapper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");

        // WHEN
        when(mockOrderRequestsWrapper.getRequest()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponse()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseBody()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, mockOrderRequestsWrapper);

        // THEN
        verify(mockOrderRequestsWrapper, times(1)).getRequest();
        verify(mockOrderRequestsWrapper, times(1)).getResponse();
        verify(mockOrderRequestsWrapper, times(0)).getResponseBody();
        verify(mockOrderRequestsWrapper, times(1)).getResponseMessage();
    }

    @Test(expected = org.mockito.exceptions.verification.NeverWantedButInvoked.class)
    public void testingIfGetResponseMessageCouldBeSkipped() throws Exception {
        // GIVEN
        OrderRequestsWrapper mockOrderRequestsWrapper = mock(OrderRequestsWrapper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");

        // WHEN
        when(mockOrderRequestsWrapper.getRequest()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponse()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseBody()).thenReturn((null));
        when(mockOrderRequestsWrapper.getResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, mockOrderRequestsWrapper);

        // THEN
        verify(mockOrderRequestsWrapper, times(1)).getRequest();
        verify(mockOrderRequestsWrapper, times(1)).getResponse();
        verify(mockOrderRequestsWrapper, times(1)).getResponseBody();
        verify(mockOrderRequestsWrapper, times(0)).getResponseMessage();
    }
}
