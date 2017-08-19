package com.oocode;

import okhttp3.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int widthOfWindow = Integer.parseInt(args[0]);  // the width of the window
        int heightOfWindow = Integer.parseInt(args[1]);  // the height of the window
        int numberOfWindow = Integer.parseInt(args[2]);  // the number of windows of this size
        String modelName = args[3];                 // the model name of these windows
        OkHttpClient client = new OkHttpClient();

        // the thickness of the frame depends on the model of window
        int widthThicknessAllowance = ReturnWidthThicknessAllowance(modelName, true);
        int heightThicknessAllowance = ReturnWidthThicknessAllowance(modelName, false);

        RequestBody requestBody = BodyBuilder.bodyBuilderForSmallOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance);
        if (heightOfWindow > 120){
            requestBody = BodyBuilder.bodyBuilderForLargeOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance);
        }

        // the glass pane is the size of the window minus allowance for
        // the thickness of the frame
        if ((widthOfWindow - widthThicknessAllowance) * (heightOfWindow - heightThicknessAllowance) * numberOfWindow > 20000) {
            Request request = new Request.Builder()
                    .url("https://immense-fortress-19979.herokuapp.com/large-order")
                    .method("POST", RequestBody.create(null, new byte[0]))
                    .post(requestBody)
                    .build();

        try (Response response = client.newCall(request).execute()) {
            try (ResponseBody body = response.body()) {
                assert body != null;
                System.out.println(body.string());
            }
        }
        return;
        }

        Request request = new Request.Builder()
                .url("https://immense-fortress-19979.herokuapp.com/order")
                .method("POST", RequestBody.create(null, new byte[0]))
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            try (ResponseBody body = response.body()) {
                assert body != null; System.out.println(body.string());
            }
        }
    }

    public static int ReturnWidthThicknessAllowance(String modelName, boolean isWidth) {
        if (!isWidth) return ReturnHeightThicknessAllowance(modelName, isWidth);
        if (modelName.equals("Churchill")) {
            return 4;
        }
        if (modelName.equals("Victoria")) {
            return 2;
        }
        if (modelName.equals("Albert")) {
            return 3;
        }
        throw null; // model name isn't known
    }

    public static int ReturnHeightThicknessAllowance(String modelName, boolean isWidth) {
        if (modelName.equals("Churchill")) {
            return 3;
        }
        if (modelName.equals("Victoria")) {
            return 3;
        }
        if (modelName.equals("Albert")) {
            return 4;
        }
        throw null; // model name isn't known
    }
}
