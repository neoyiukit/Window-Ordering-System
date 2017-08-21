package com.oocode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {

    OrderGlass orderGlass;
//    RequestBody mockRequestBody = mock(RequestBody.class);
//    Request mockRequest = mock(Request.class);

    @Test
    public void testIfTheInputMatchedFieldsInOrderGlass() throws Exception {
        // do not run without changing "Ivan's Windows" in com.oocode.BodyBuilder to "test"
        // otherwise you have to pay for the order

        // When
        orderGlass = new OrderGlass(123, 456, 789, "Churchill");
        orderGlass.orderDetermination();
//        System.out.println(orderGlass.ReturnWidthThicknessAllowance(orderGlass.getModelName(), true));
//        System.out.println(orderGlass.ReturnWidthThicknessAllowance(orderGlass.getModelName(), false));
//        System.out.println(orderGlass.getWindowType());

        // Then
        assertEquals("Unmatched Width of Window Passed", 123, orderGlass.getWidthOfWindow());
        assertEquals("Unmatched Height of Window Passed", 456, orderGlass.getHeightOfWindow());
        assertEquals("Unmatched Number of Window Passed", 789, orderGlass.getNumberOfWindow());
        assertEquals("Unmatched Window Model", "Churchill", orderGlass.getModelName());
        assertEquals("Unmatched Width Thickness Allowance", 4, ReturnThicknessAllowance.ReturnWidthThicknessAllowance(orderGlass.getModelName()));
        assertEquals("Unmatched Height Thickness Allowance", 3, ReturnThicknessAllowance.ReturnHeightThicknessAllowance(orderGlass.getModelName()));
        assertEquals("Unmatched Window Type", "toughened", orderGlass.getWindowType());


//        main(new String[]{"123", "456", "789", "Churchill"});
//        main(new String[]{"48", "36", "1", "Victoria"});
        /* EXPECTED OUTPUT
Thank you "test" for your large order (q=789, w=119, h=453, toughened). Order not really placed - nothing to pay
Thank you "test" for your order (q=1, w=46, h=33, plain). Order not really placed - nothing to pay
         */
    }

    @Test
    public void testIfCallsLargeOrderEndpoint() throws Exception{

        // when
        orderGlass = new OrderGlass(123, 456, 789, "Churchill");
        orderGlass.orderDetermination();
        int widthThicknessAllowance = ReturnThicknessAllowance.ReturnWidthThicknessAllowance(orderGlass.getModelName());
        int heightThicknessAllowance = ReturnThicknessAllowance.ReturnHeightThicknessAllowance(orderGlass.getModelName());
        int total = orderGlass.getCalculatedTotal(orderGlass.getWidthOfWindow(), orderGlass.getHeightOfWindow(), orderGlass.getNumberOfWindow(), widthThicknessAllowance, heightThicknessAllowance);

        // Then
//        assertThat("Total Area < 20000", totalSum.greaterThan(20000)); - TODO: use hamcrest
        assertTrue("Total Area is smaller than 20000", total > 20000);
    }

    @Test
    public void testIfCallsSmallerOrderEndpoint() throws Exception{

        // when
        orderGlass = new OrderGlass(20, 20, 20, "Churchill");
        orderGlass.orderDetermination();
        int widthThicknessAllowance = ReturnThicknessAllowance.ReturnWidthThicknessAllowance(orderGlass.getModelName());
        int heightThicknessAllowance = ReturnThicknessAllowance.ReturnHeightThicknessAllowance(orderGlass.getModelName());
        int total = orderGlass.getCalculatedTotal(orderGlass.getWidthOfWindow(), orderGlass.getHeightOfWindow(), orderGlass.getNumberOfWindow(), widthThicknessAllowance, heightThicknessAllowance);

        // Then
//        assertThat("Total Area < 20000", totalSum.greaterThan(20000)); - TODO: use hamcrest
        assertTrue("Total Area is larger than 20000", total < 20000);
    }
}
