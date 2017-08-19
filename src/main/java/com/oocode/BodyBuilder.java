package com.oocode;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BodyBuilder {
    public static RequestBody bodyBuilderForSmallOrders(int w, int h, int n, int width, int height) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", "Test Account")
                .addFormDataPart("quantity", Integer.toString(n))
                .addFormDataPart("width", Integer.toString(w - width))
                .addFormDataPart("height", Integer.toString(h - height))
                .addFormDataPart("type", "plain")
                .build();
    }

    public static RequestBody bodyBuilderForLargeOrders(int w, int h, int n, int width, int height) {
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("account", "Test Account")
                .addFormDataPart("quantity", Integer.toString(n))
                .addFormDataPart("width", Integer.toString(w - width))
                .addFormDataPart("height", Integer.toString(h - height))
                .addFormDataPart("type", "toughened")
                .build();
    }
}
