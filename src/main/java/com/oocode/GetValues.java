package com.oocode;

/**
 * Created by neo.yiu on 20/08/2017.
 */
public interface GetValues {
    int getWidthOfWindow();
    int getHeightOfWindow();
    int getNumberOfWindow();
    String getModelName();
    int getCalculatedTotal(int widthOfWindow, int heightOfWindow, int numberOfWindow, int widthThicknessAllowance, int heightThicknessAllowance);
}

