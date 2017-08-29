package com.oocode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by neo.yiu on 25/08/2017.
 */
public class WindowOrderingSystemTest {

    WindowsOrderingSystem windowsOrderingSystem;

    @Test
    public void testingIfWidthArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"-1", "10", "10", "Albert", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -1.");
        }
    }

    @Test
    public void testingIfHeightArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"10", "-2", "10", "Albert", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -2.");
        }
    }

    @Test
    public void testingIfNumberArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"10", "10", "-3", "Albert", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -3.");
        }
    }

    @Test
    public void testingIfWindowModelArgumentInappropriate() throws Exception {
        try {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"10", "10", "10", "N/A", "test"});
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Model name should be equal to Churchill, Victoria or Albert");
        }
    }

    @Test
    public void testingIfTheCorrectOrderPlaced() throws Exception {
            windowsOrderingSystem = new WindowsOrderingSystem();
            windowsOrderingSystem.OrderGenerator(new String[]{"10", "10", "10", "Churchill", "test"});

            assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your order (q=10, w=6, h=7, plain). Order not really placed - nothing to pay");
    }

    @Test
    public void testingIfToughenedGlassOrderPlacedWithWindowHeightMoreThan120AndTotalAreaHigherThan3000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"3", "121", "100", "Victoria", "test"});        // make sure the window height will be more than 120, so 121
        // after applying the thick allowances the equation will be: (3-2) * (121-3) * 100 > 3000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your order (q=100, w=1, h=118, toughened). Order not really placed - nothing to pay");
    }

    @Test
    public void testingIfToughenedGlassOrderPlacedWithWindowHeightMoreThan120ButTotalAreaLowerThan3000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"3", "121", "1", "Victoria", "test"});        // make sure the window height will be more than 120, so 121
        // after applying the thick allowances the equation will be: (3-2) * (121-3) * 1 < 3000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your order (q=1, w=1, h=118, toughened). Order not really placed - nothing to pay");
    }

    @Test
    public void testingIfToughenedGlassOrderPlacedWithWindowHeightLessThanOrEqual120ButTotalAreaHigherThan3000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"3", "119", "100", "Victoria", "test"});        // make sure the window height will be less than 120, so 119
        // after applying the thick allowances the equation will be: (3-2) * (119-3) * 100 > 3000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your order (q=100, w=1, h=116, toughened). Order not really placed - nothing to pay");
    }

    @Test
    public void testingIfPlainGlassOrderPlacedWithWindowHeightLessThanOrEqual120AndTotalAreaLowerThan3000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"3", "119", "10", "Victoria", "test"});        // make sure the window height will be less than 120, so 119
        // after applying the thick allowances the equation will be: (3-2) * (119-3) * 10 < 3000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your order (q=10, w=1, h=116, plain). Order not really placed - nothing to pay");
    }

    @Test
    public void testingIfLargeEndpointOrderPlacedWhenToughenedGlassTypeAndTotalAreaMoreThan18000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"5", "121", "1000", "Churchill", "test"});        // Height = 121 to make sure toughened glass type will be returned
        // after applying the thick allowances the equation will be: (5-4) * (121-3) * 1000 > 18000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your large order (q=1000, w=1, h=118, toughened). Order not really placed - nothing to pay");
    }

    @Test
    public void testingIfNormalEndpointOrderPlacedWhenToughenedGlassTypeAndTotalAreaLessThan18000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"5", "121", "10", "Churchill", "test"});         // Height = 121 to make sure toughened glass type will be returned
        // after applying the thick allowances the equation will be: (5-4) * (121-3) * 10 < 18000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your order (q=10, w=1, h=118, toughened). Order not really placed - nothing to pay");
    }


    @Test // Remark for this test as Total Area larger than 3000 will anyway return toughened glass type so it will basically shift to the second condition: "if ((getWindowType().equals("toughened")) && (getCalculatedTotal()> 18000))"
    public void testingIfNormalEndpointOrderPlacedWhenPlainGlassTypeAndTotalAreaMoreThan20000() throws Exception {

        windowsOrderingSystem = new WindowsOrderingSystem();
        windowsOrderingSystem.OrderGenerator(new String[]{"5", "119", "1000", "Churchill", "test"});         // Height = 119 to make sure plain window type will be returned
        // after applying the thick allowances the equation will be: (5-4) * (119-3) * 10 > 20000

        assertEquals("Order Messages do not match!", windowsOrderingSystem.getOrderResponseMessage(), "Thank you \"test\" for your large order (q=1000, w=1, h=116, toughened). Order not really placed - nothing to pay");
    }

    @Test(expected = org.mockito.exceptions.misusing.InvalidUseOfMatchersException.class)
     public void testingIfGetOrderResponseMessageInWindowsOrderingSystemGetCalledOnce() throws Exception {
        // GIVEN
        WindowsOrderingSystem mockWindowsOrderingSystem = mock(WindowsOrderingSystem.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");
        OrderRequestsWrapper orderRequestsWrapper = new OrderRequestsWrapper(orderInfoHandler);

        // WHEN
        when(mockWindowsOrderingSystem.getOrderResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, orderRequestsWrapper);

        // THEN
        verify(mockWindowsOrderingSystem, times(1)).getOrderResponseMessage();
    }

    @Test(expected = org.mockito.exceptions.misusing.MissingMethodInvocationException.class)
    public void testingIfGetOrderResponseMessageInWindowsOrderingSystemCouldBeSkipped() throws Exception {
        // GIVEN
        WindowsOrderingSystem mockWindowsOrderingSystem = mock(WindowsOrderingSystem.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(20, 21, 22, "Victoria", "test");
        OrderRequestsWrapper orderRequestsWrapper = new OrderRequestsWrapper(orderInfoHandler);

        // WHEN
        when(mockWindowsOrderingSystem.getOrderResponseMessage()).thenReturn((null));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, orderRequestsWrapper);

        // THEN
        verify(mockWindowsOrderingSystem, times(0)).getOrderResponseMessage();
    }
}
