package com.oocode;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BodyBuilder {
    public static RequestBody bodyBuilderForSmallOrders(int widthOfWindow, int heightOfWindow, int numberOfWindow, int widthThicknessAllowance, int heightThicknessAllowance, String windowType) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", "Test Account")
                .addFormDataPart("quantity", Integer.toString(numberOfWindow))
                .addFormDataPart("width", Integer.toString(widthOfWindow - widthThicknessAllowance))
                .addFormDataPart("height", Integer.toString(heightOfWindow - heightThicknessAllowance))
                .addFormDataPart("type", windowType)
                .build();
    }

    public static RequestBody bodyBuilderForLargeOrders(int widthOfWindow, int heightOfWindow, int numberOfWindow, int widthThicknessAllowance, int heightThicknessAllowance, String windowType) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", "Test Account")
                .addFormDataPart("quantity", Integer.toString(numberOfWindow))
                .addFormDataPart("width", Integer.toString(widthOfWindow - widthThicknessAllowance))
                .addFormDataPart("height", Integer.toString(heightOfWindow - heightThicknessAllowance))
                .addFormDataPart("type", windowType)
                .build();
    }
}
