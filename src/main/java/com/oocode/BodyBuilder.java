package com.oocode;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BodyBuilder {
    public static RequestBody bodyBuilderForAnyOrders(int widthOfWindow, int heightOfWindow, int numberOfWindow, int widthThicknessAllowance, int heightThicknessAllowance, String windowType, String userName) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", userName)
                .addFormDataPart("quantity", Integer.toString(numberOfWindow))
                .addFormDataPart("width", Integer.toString(widthOfWindow - widthThicknessAllowance))
                .addFormDataPart("height", Integer.toString(heightOfWindow - heightThicknessAllowance))
                .addFormDataPart("type", windowType)
                .build();

        return requestBody;
    }
}
