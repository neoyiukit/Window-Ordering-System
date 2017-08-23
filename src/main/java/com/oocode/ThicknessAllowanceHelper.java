package com.oocode;

public class ThicknessAllowanceHelper {
    public static int ReturnWidthThicknessAllowance(String modelName) {
        if (modelName.equals("Churchill")) {
            return 4;
        }
        if (modelName.equals("Victoria")) {
            return 2;
        }
        if (modelName.equals("Albert")) {
            return 3;
        }
        throw new IllegalArgumentException("Type of Window should be equal to Churchill, Victoria or Albert");
    }

    public static int ReturnHeightThicknessAllowance(String modelName) {
        if (modelName.equals("Churchill")) {
            return 3;
        }
        if (modelName.equals("Victoria")) {
            return 3;
        }
        if (modelName.equals("Albert")) {
            return 4;
        }
        throw new IllegalArgumentException("Type of Window should be equal to Churchill, Victoria or Albert");
    }
}