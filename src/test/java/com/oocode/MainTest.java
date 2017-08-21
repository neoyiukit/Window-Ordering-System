package com.oocode;

import okhttp3.RequestBody;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

// do not run without changing "Ivan's Windows" in com.oocode.BodyBuilder to "test"
// otherwise you have to pay for the order

public class MainTest {

    OrderGenerator newOrder;
    RequestBody mockRequestBody = mock(RequestBody.class);
    BodyBuilder mockBodyBuilder = mock(BodyBuilder.class);

    @Test
    public void testIfTheInputMatchedFieldsInOrderGlass() throws Exception {

        // When
        newOrder = new OrderGenerator(50, 10, 10, "Churchill");
        newOrder.orderPlacementHelper();

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
        newOrder = new OrderGenerator(123, 456, 789, "Churchill");
        newOrder.orderPlacementHelper();

        // Then
        assertThat("Total Area is smaller than 20000", newOrder.getCalculatedTotal(), greaterThan(20000));
        // assertTrue("Total Area is smaller than 20000", total > 20000);
        // TODO: Make sure to compare the url if it goes to the large endpoint
    }

    @Test
    public void testIfCallsSmallerOrderEndpoint() throws Exception{

        // when
        newOrder = new OrderGenerator(1, 121, 1, "Churchill");
        newOrder.orderPlacementHelper();

        // Then
        assertThat("Total Area is larger than 20000", newOrder.getCalculatedTotal(), lessThanOrEqualTo(20000));
         // assertTrue("Total Area is larger than 20000", total < 20000);
         // TODO: Make sure to compare the url if it goes to the small endpoint
    }
}
