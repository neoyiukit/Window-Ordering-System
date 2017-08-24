package com.oocode;

// do not run without changing "Ivan's Windows" in com.oocode.BodyBuilder to "test"
// otherwise you have to pay for the order

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(.class)

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderInfoHandlerTest {

    WindowsOrderingSystem windowsOrderingSystem;

    @Test
    public void testingIfWidthArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"-1", "20", "20", "Victoria", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -1.");
        }
    }

    @Test
    public void testingIfHeightArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"20", "-2", "20", "Victoria", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -2.");
        }
    }

    @Test
     public void testingIfNumberArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"20", "20", "-3", "Victoria", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -3.");
        }
    }

    @Test
    public void testingIfWindowModelArgumentInappropriate() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"20", "20", "20", "N/A", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Model name should be equal to Churchill, Victoria or Albert");
        }
    }

    @Test
     public void testingIfWidthArgumentPassedCorrectly() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"19", "20", "21", "Churchill", "test"});

        assertEquals("Unmatched Width of Window Passed", OrderInfoHandler.getWidthOfWindow(), 19);

    }

    @Test
         public void testingIfHeightArgumentPassedCorrectly() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"19", "20", "21", "Churchill", "test"});

        assertEquals("Unmatched Height of Window Passed", OrderInfoHandler.getHeightOfWindow(), 20);

    }

    @Test
     public void testingIfNumberArgumentPassedCorrectly() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"19", "20", "21", "Churchill", "test"});

        assertEquals("Unmatched Number of Window Passed", OrderInfoHandler.getNumberOfWindow(), 21);

    }

    @Test
     public void testingIfWindowModelArgumentPassedCorrectly() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"19", "20", "21", "Churchill", "test"});

        assertEquals("Unmatched Window Model Passed", OrderInfoHandler.getModelName(), "Churchill");
    }

    @Test
    public void testingIfUserNameArgumentPassedCorrectly() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"19", "20", "21", "Churchill", "test"});

        assertEquals("Unmatched UserName Passed", OrderInfoHandler.getUserName(), "test");
    }

//    @Test
//    public void SystemTestNegativeInvalidInputArea1() throws Exception {
//        try {
//            windowsOrderingSystem = new WindowsOrderingSystem();
//            windowsOrderingSystem.OrderGenerator(new String[]{"2", "120", "5", "Victoria", "test"});
//        } catch (Exception e) {
//            assertEquals(e.getMessage(), "Inputs are negative");
//        }
//    }

//    @Test
//    public void testIfTheInputMatchedFieldsInOrderGlass() throws Exception {
//
//        // When
//        newOrder = new OrderInfoHandler(50, 10, 10, "Churchill");
//        newOrder.orderPlacementHelper();
//        System.out.println(newOrder.getOrderURL());
//
//        // Then
//        assertEquals("Unmatched Width of Window Passed", 50, newOrder.getWidthOfWindow());
//        assertEquals("Unmatched Height of Window Passed", 10, newOrder.getHeightOfWindow());
//        assertEquals("Unmatched Number of Window Passed", 10, newOrder.getNumberOfWindow());
//        assertEquals("Unmatched Window Model", "Churchill", newOrder.getModelName());
//        assertEquals("Unmatched Width Thickness Allowance", 4, ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(newOrder.getModelName()));
//        assertEquals("Unmatched Height Thickness Allowance", 3, ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(newOrder.getModelName()));
//        assertEquals("Unmatched Window Type", "toughened", newOrder.getWindowType());
//    }
//
//    @Test
//    public void testIfCallsLargeOrderEndpoint() throws Exception{
//
//        // when
//        newOrder = new OrderInfoHandler(123, 456, 789, "Churchill");
//        newOrder.orderPlacementHelper();
//        System.out.println(newOrder.getOrderURL());
//
//
//        // Then
//        assertThat("Total Area is smaller than 20000", newOrder.getCalculatedTotal(), greaterThan(20000));
//        assertThat("The request is not sending to Large Order Endpoint", newOrder.getOrderURL(), endsWith("/large-order"));
//        // assertTrue("Total Area is smaller than 20000", total > 20000);
//        // TODO: Make sure to compare the url if it goes to the large endpoint
//    }
//
//    @Test
//    public void testIfCallsSmallerOrderEndpoint() throws Exception{
//
//        // when
//        newOrder = new OrderInfoHandler(1, 119, 1, "Churchill");
//        newOrder.orderPlacementHelper();
//        System.out.println(newOrder.getOrderURL());
//
//
//        // Then
//        assertThat("Total Area is larger than 20000", newOrder.getCalculatedTotal(), lessThanOrEqualTo(20000));
//        assertThat("The request is not sending to Large Order Endpoint", newOrder.getOrderURL(), endsWith("/order"));
//
////        Mockito.verify(mockBodyBuilder, Mockito.times(1)).someMethod("was called exactly three times");
//
//
//        // assertTrue("Total Area is larger than 20000", total < 20000);
//         // TODO: Make sure to compare the url if it goes to the small endpoint
//    }
//    @Test
//    public void testIfCallsLargeOrderEndpoint() throws Exception{
//        WindowsOrderingSystem.OrderGenerator(new String[]{"30", "30", "40", "Victoria","test"});
//    }

//    @Test
//    public void SystemTestInvalidInputArea() throws Exception {
//        try {
//            WindowsOrderingSystem windowsOrderingSystem = new WindowsOrderingSystem();
//            windowsOrderingSystem.OrderGenerator(new String[]{"1", "1", "1", "invalid", "test"});
//        } catch (Exception e) {
//            assertEquals(e.getMessage(), " Check your window dimensions, glassArea appears to be negative");
//        }
//    }
}