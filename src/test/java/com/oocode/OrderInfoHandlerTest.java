package com.oocode;

// do not run without changing "Ivan's Windows" in com.oocode.BodyBuilder to "test"
// otherwise you have to pay for the order

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderInfoHandlerTest {

    @Test
    public void testingIfWidthArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            new OrderInfoHandler(-1, 20, 20, "Victoria", "test");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -1.");
        }
    }

    @Test
    public void testingIfHeightArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            new OrderInfoHandler(20, -2, 20, "Victoria", "test");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -2.");
        }
    }

    @Test
     public void testingIfNumberArgumentParseUnsignedIntIsNegative() throws Exception {
        try {
            new OrderInfoHandler(20, 20, -3, "Victoria", "test");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Illegal leading minus sign on unsigned string -3.");
        }
    }

    @Test
    public void testingIfWindowModelArgumentInappropriate() throws Exception {
        try {
            new OrderInfoHandler(20, 20, 20, "N/A", "test");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Model name should be equal to Churchill, Victoria or Albert");
        }
    }

    @Test
     public void testingIfWidthArgumentPassedCorrectly() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched Width of Window Passed", OrderInfoHandler.getWidthOfWindow(), 19);

    }

    @Test
    public void testingIfHeightArgumentPassedCorrectly() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched Height of Window Passed", OrderInfoHandler.getHeightOfWindow(), 20);

    }

    @Test
    public void testingIfNumberArgumentPassedCorrectly() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched Number of Window Passed", OrderInfoHandler.getNumberOfWindow(), 21);
    }

    @Test
     public void testingIfWindowModelArgumentPassedCorrectly() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched Window Model Passed", OrderInfoHandler.getModelName(), "Churchill");
    }

    @Test
    public void testingIfUserNameArgumentPassedCorrectly() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched UserName Passed", OrderInfoHandler.getUserName(), "test");
    }

    @Test
         public void testingIfChurchillWidthThicknessAllowancesAreCorrectlyRetrieved() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched Width Thickness Allowance of Churchill", OrderInfoHandler.getWidthThicknessAllowance(), 4);
    }

    @Test
    public void testingIfVictoriaWidthThicknessAllowancesAreCorrectlyRetrieved() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Victoria", "test");
        assertEquals("Unmatched Width Thickness Allowance of Victoria", OrderInfoHandler.getWidthThicknessAllowance(), 2);
    }

    @Test
    public void testingIfAlbertWidthThicknessAllowancesAreCorrectlyRetrieved() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Albert", "test");
        assertEquals("Unmatched Width Thickness Allowance of Albert", OrderInfoHandler.getWidthThicknessAllowance(), 3);
    }

    @Test
    public void testingIfChurchillHeightThicknessAllowancesAreCorrectlyRetrieved() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Churchill", "test");
        assertEquals("Unmatched Height Thickness Allowance of Churchill", OrderInfoHandler.getHeightThicknessAllowance(), 3);
    }

    @Test
    public void testingIfVictoriaHeightThicknessAllowancesAreCorrectlyRetrieved() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Victoria", "test");
        assertEquals("Unmatched Height Thickness Allowance of Victoria", OrderInfoHandler.getHeightThicknessAllowance(), 3);
    }

    @Test
     public void testingIfAlbertHeightThicknessAllowancesAreCorrectlyRetrieved() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Albert", "test");
        assertEquals("Unmatched Height Thickness Allowance of Albert", OrderInfoHandler.getHeightThicknessAllowance(), 4);
    }

    @Test
    public void testingIfCalculatedAreaIsNegative() throws Exception {
        try {
            new OrderInfoHandler(0, 5, 1, "Albert", "test");
            OrderInfoHandler.getCalculatedTotal();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Total area of the window could not be in negative!");
        }
    }

    @Test
    public void testingIfCalculatedAreaIsEqualToExpectedOutput() throws Exception {

        new OrderInfoHandler(19, 20, 21, "Victoria", "test");
        // after applying the thick allowances the equation will be: (19-2) * (20-3) * 21 = 6069

        assertEquals("Unmatched Calculated Total area with the expect output", OrderInfoHandler.getCalculatedTotal(), 6069);
    }

    @Test
    public void testingIfToughenedGlassTypeReturnedWithWindowHeightMoreThan120AndTotalAreaHigherThan3000() throws Exception {

        new OrderInfoHandler(3, 121, 100, "Victoria", "test");
        // make sure the window height will be more than 120, so 121
        // after applying the thick allowances the equation will be: (3-2) * (121-3) * 100 > 3000

        assertEquals("Not returning toughened(type of glass) under the conditions given", OrderInfoHandler.getGlassType(), "toughened");
    }

    @Test
    public void testingIfToughenedGlassTypeReturnedWithWindowHeightMoreThan120ButTotalAreaLowerThan3000() throws Exception {

        new OrderInfoHandler(3, 121, 1, "Victoria", "test");
        // make sure the window height will be more than 120, so 121
        // after applying the thick allowances the equation will be: (3-2) * (121-3) * 1 < 3000

        assertEquals("Not returning toughened(type of glass) under the conditions given", OrderInfoHandler.getGlassType(), "toughened");
    }

    @Test
    public void testingIfToughenedGlassTypeReturnedWithWindowHeightLessThanOrEqual120ButTotalAreaHigherThan3000() throws Exception {

        new OrderInfoHandler(3, 119, 100, "Victoria", "test");
        // make sure the window height will be less than 120, so 119
        // after applying the thick allowances the equation will be: (3-2) * (119-3) * 100 > 3000

        assertEquals("Not returning toughened(type of glass) under the conditions given", OrderInfoHandler.getGlassType(), "toughened");
    }

    @Test
    public void testingIfPlainGlassTypeReturnedWithWindowHeightLessThanOrEqual120AndTotalAreaLowerThan3000() throws Exception {

        new OrderInfoHandler(3, 119, 10, "Victoria", "test");
        // make sure the window height will be less than 120, so 119
        // after applying the thick allowances the equation will be: (3-2) * (119-3) * 10 < 3000

        assertEquals("Not returning plain(type of glass) under the conditions given", OrderInfoHandler.getGlassType(), "plain");
    }

    @Test
    public void testingIfLargeEndpointReturnedWhenToughenedGlassTypeAndTotalAreaMoreThan18000() throws Exception {

        new OrderInfoHandler(5, 121, 1000, "Churchill", "test");

        // Height = 121 to make sure toughened glass type will be returned
        // after applying the thick allowances the equation will be: (5-4) * (121-3) * 1000 > 18000

        assertEquals("Not returning large endpoint under the conditions given", OrderInfoHandler.getOrderURL(), "https://immense-fortress-19979.herokuapp.com/large-order");
    }

    @Test
    public void testingIfNormalEndpointReturnedWhenToughenedGlassTypeAndTotalAreaLowerThan18000() throws Exception {

        new OrderInfoHandler(5, 121, 10, "Churchill", "test");

        // Height = 121 to make sure toughened glass type will be returned
        // after applying the thick allowances the equation will be: (5-4) * (121-3) * 10 < 18000

        assertEquals("Not returning normal endpoint under the conditions given", OrderInfoHandler.getOrderURL(), "https://immense-fortress-19979.herokuapp.com/order");
    }


    @Test // Remark for this test as Total Area larger than 3000 will anyway return toughened glass type so it will basically shift to the second condition: "if ((getWindowType().equals("toughened")) && (getCalculatedTotal()> 18000))"
    public void testingIfNormalEndpointReturnedWhenPlainGlassTypeAndTotalAreaMoreThan20000() throws Exception {

        new OrderInfoHandler(5, 119, 1000, "Churchill", "test");

        // Height = 119 to make sure plain window type will be returned
        // after applying the thick allowances the equation will be: (5-4) * (119-3) * 10 > 20000

        assertEquals("Not returning normal endpoint under the conditions given", OrderInfoHandler.getOrderURL(), "https://immense-fortress-19979.herokuapp.com/large-order");
    }
}