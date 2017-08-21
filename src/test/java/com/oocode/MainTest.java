package com.oocode;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;

public class MainTest {

    OrderGlass orderGlass;
//    RequestBody mockRequestBody = mock(RequestBody.class);
//    Request mockRequest = mock(Request.class);

    @Test
    public void testIfTheInputMatchedFieldsInOrderGlass() throws Exception {
        // do not run without changing "Ivan's Windows" in com.oocode.BodyBuilder to "test"
        // otherwise you have to pay for the order

        // When
        orderGlass = new OrderGlass(50, 10, 10, "Churchill");
        orderGlass.orderDetermination();
//        System.out.println(orderGlass.ReturnWidthThicknessAllowance(orderGlass.getModelName(), true));
//        System.out.println(orderGlass.ReturnWidthThicknessAllowance(orderGlass.getModelName(), false));
//        System.out.println(orderGlass.getWindowType());
//        int widthThicknessAllowance = ReturnThicknessAllowance.ReturnWidthThicknessAllowance(orderGlass.getModelName());
//        int heightThicknessAllowance = ReturnThicknessAllowance.ReturnHeightThicknessAllowance(orderGlass.getModelName());
//        int total = orderGlass.getCalculatedTotal(orderGlass.getWidthOfWindow(), orderGlass.getHeightOfWindow(), orderGlass.getNumberOfWindow(), widthThicknessAllowance, heightThicknessAllowance);
//        System.out.println(total);

        // Then
        assertEquals("Unmatched Width of Window Passed", 50, orderGlass.getWidthOfWindow());
        assertEquals("Unmatched Height of Window Passed", 10, orderGlass.getHeightOfWindow());
        assertEquals("Unmatched Number of Window Passed", 10, orderGlass.getNumberOfWindow());
        assertEquals("Unmatched Window Model", "Churchill", orderGlass.getModelName());
        assertEquals("Unmatched Width Thickness Allowance", 4, ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(orderGlass.getModelName()));
        assertEquals("Unmatched Height Thickness Allowance", 3, ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(orderGlass.getModelName()));
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
        int widthThicknessAllowance = ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(orderGlass.getModelName());
        int heightThicknessAllowance = ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(orderGlass.getModelName());
        int total = orderGlass.getCalculatedTotal(orderGlass.getWidthOfWindow(), orderGlass.getHeightOfWindow(), orderGlass.getNumberOfWindow(), widthThicknessAllowance, heightThicknessAllowance);

        // Then
        assertThat("Total Area is smaller than 20000", total, greaterThan(20000));
        // assertTrue("Total Area is smaller than 20000", total > 20000);
        // TODO: Make sure to compare the url if it goes to the large endpoint
    }

    @Test
    public void testIfCallsSmallerOrderEndpoint() throws Exception{

        // when
        orderGlass = new OrderGlass(20, 20, 20, "Churchill");
        orderGlass.orderDetermination();
        int widthThicknessAllowance = ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(orderGlass.getModelName());
        int heightThicknessAllowance = ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(orderGlass.getModelName());
        int total = orderGlass.getCalculatedTotal(orderGlass.getWidthOfWindow(), orderGlass.getHeightOfWindow(), orderGlass.getNumberOfWindow(), widthThicknessAllowance, heightThicknessAllowance);

        // Then
        assertThat("Total Area is larger than 20000", total, lessThanOrEqualTo(20000));
         // assertTrue("Total Area is larger than 20000", total < 20000);
         // TODO: Make sure to compare the url if it goes to the small endpoint
    }
}
