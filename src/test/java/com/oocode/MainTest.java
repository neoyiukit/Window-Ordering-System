package com.oocode;

import okhttp3.RequestBody;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

// do not run without changing "Ivan's Windows" in com.oocode.BodyBuilder to "test"
// otherwise you have to pay for the order

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(.class)

public class MainTest {

    OrderInfoHandler newOrder;
    RequestBody mockRequestBody = mock(RequestBody.class);
    BodyBuilder mockBodyBuilder = mock(BodyBuilder.class);
    RequestBuilder mockRequestBuilder = mock(RequestBuilder.class);
    ResponseBuilder mockResponseBuilder = mock(ResponseBuilder.class);

    @Test
    public void testIfTheInputMatchedFieldsInOrderGlass() throws Exception {

        // When
        newOrder = new OrderInfoHandler(50, 10, 10, "Churchill");
        newOrder.orderPlacementHelper();
        System.out.println(newOrder.getOrderURL());

        // Then
        assertEquals("Unmatched Width of Window Passed", 50, newOrder.getWidthOfWindow());
        assertEquals("Unmatched Height of Window Passed", 10, newOrder.getHeightOfWindow());
        assertEquals("Unmatched Number of Window Passed", 10, newOrder.getNumberOfWindow());
        assertEquals("Unmatched Window Model", "Churchill", newOrder.getModelName());
        assertEquals("Unmatched Width Thickness Allowance", 4, ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(newOrder.getModelName()));
        assertEquals("Unmatched Height Thickness Allowance", 3, ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(newOrder.getModelName()));
        assertEquals("Unmatched Window Type", "toughened", newOrder.getWindowType());
    }

    @Test
    public void testIfCallsLargeOrderEndpoint() throws Exception{

        // when
        newOrder = new OrderInfoHandler(123, 456, 789, "Churchill");
        newOrder.orderPlacementHelper();
        System.out.println(newOrder.getOrderURL());


        // Then
        assertThat("Total Area is smaller than 20000", newOrder.getCalculatedTotal(), greaterThan(20000));
        assertThat("The request is not sending to Large Order Endpoint", newOrder.getOrderURL(), endsWith("/large-order"));
        // assertTrue("Total Area is smaller than 20000", total > 20000);
        // TODO: Make sure to compare the url if it goes to the large endpoint
    }

    @Test
    public void testIfCallsSmallerOrderEndpoint() throws Exception{

        // when
        newOrder = new OrderInfoHandler(1, 119, 1, "Churchill");
        newOrder.orderPlacementHelper();
        System.out.println(newOrder.getOrderURL());


        // Then
        assertThat("Total Area is larger than 20000", newOrder.getCalculatedTotal(), lessThanOrEqualTo(20000));
        assertThat("The request is not sending to Large Order Endpoint", newOrder.getOrderURL(), endsWith("/order"));

//        Mockito.verify(mockBodyBuilder, Mockito.times(1)).someMethod("was called exactly three times");


        // assertTrue("Total Area is larger than 20000", total < 20000);
         // TODO: Make sure to compare the url if it goes to the small endpoint
    }
}