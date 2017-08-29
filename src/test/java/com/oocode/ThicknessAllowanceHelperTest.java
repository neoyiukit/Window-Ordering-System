package com.oocode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by neo.yiu on 25/08/2017.
 */
public class ThicknessAllowanceHelperTest {

    @Test
    public void testingIfModelOfChurchillReturnsWidthThicknessAllowanceOf4() throws Exception {
        assertEquals("Width Thickness Allowance of Churchill Doesn't Return 4", ThicknessAllowanceHelper.ReturnWidthThicknessAllowance("Churchill"), 4);
    }

    @Test
    public void testingIfModelOfVictoriaReturnsWidthThicknessAllowanceOf2() throws Exception {
        assertEquals("Width Thickness Allowance of Victoria Doesn't Return 2", ThicknessAllowanceHelper.ReturnWidthThicknessAllowance("Victoria"), 2);
    }

    @Test
    public void testingIfModelOfAlbertReturnsWidthThicknessAllowanceOf3() throws Exception {
        assertEquals("Width Thickness Allowance of Albert Doesn't Return 3", ThicknessAllowanceHelper.ReturnWidthThicknessAllowance("Albert"), 3);
    }

    @Test
    public void testingIfModelOfChurchillReturnsHeightThicknessAllowanceOf3() throws Exception {
        assertEquals("Height Thickness Allowance of Churchill Doesn't Return 3", ThicknessAllowanceHelper.ReturnHeightThicknessAllowance("Churchill"), 3);
    }

    @Test
    public void testingIfModelOfVictoriaReturnsHeightThicknessAllowanceOf3() throws Exception {
        assertEquals("Height Thickness Allowance of Victoria Doesn't Return 3", ThicknessAllowanceHelper.ReturnHeightThicknessAllowance("Victoria"), 3);
    }

    @Test
    public void testingIfModelOfAlbertReturnsHeightThicknessAllowanceOf4() throws Exception {
        assertEquals("Height Thickness Allowance of Albert Doesn't Return 4", ThicknessAllowanceHelper.ReturnHeightThicknessAllowance("Albert"), 4);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testingIfBothWidthThicknessAndHeightThicknessCalled() throws Exception {
        // GIVEN
        ThicknessAllowanceHelper mockThicknessAllowanceHelper = mock(ThicknessAllowanceHelper.class);
        OrderInfoHandler orderInfoHandler = new OrderInfoHandler(19, 20, 21, "Victoria", "test");
        OrderRequestsWrapper orderRequestsWrapper = new OrderRequestsWrapper(orderInfoHandler);

        // WHEN
        when(mockThicknessAllowanceHelper.ReturnWidthThicknessAllowance(anyString())).thenReturn((anyInt()));
        when(mockThicknessAllowanceHelper.ReturnHeightThicknessAllowance(anyString())).thenReturn((anyInt()));
        OrderPlacementExecuter.orderRequestGenerator(orderInfoHandler, orderRequestsWrapper);

        // THEN
        verify(mockThicknessAllowanceHelper, times(1)).ReturnWidthThicknessAllowance("Victoria");
        verify(mockThicknessAllowanceHelper, times(1)).ReturnHeightThicknessAllowance("Victoria");
    }
}
