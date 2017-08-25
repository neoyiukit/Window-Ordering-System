package com.oocode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
