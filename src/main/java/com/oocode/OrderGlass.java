package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OrderGlass {
    int widthOfWindow;  // the width of the window
    int heightOfWindow;  // the height of the window
    int numberOfWindow;  // the number of windows of this size
    String modelName;                 // the model name of these windows
    OkHttpClient client = new OkHttpClient();
    int widthThicknessAllowance;
    int heightThicknessAllowance;


    public OrderGlass(int widthOfWindow, int heightOfWindow, int numberOfWindow, String modelName) throws Exception {
        this.widthOfWindow = widthOfWindow;
        this.heightOfWindow = heightOfWindow;
        this.numberOfWindow = numberOfWindow;
        this.modelName = modelName;
        widthThicknessAllowance = ReturnWidthThicknessAllowance(this.modelName);
        heightThicknessAllowance = ReturnHeightThicknessAllowance(this.modelName);
    }

    public int getWidthOfWindow() {
        return widthOfWindow;
    }
    public int getHeightOfWindow() {
        return heightOfWindow;
    }
    public int getNumberOfWindow() {
        return numberOfWindow;
    }
    public String getModelName() {
        return modelName;
    }

    // the thickness of the frame depends on the model of window
    public void orderDetermination() throws Exception {

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
        throw null; // model name isn't known
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
        throw null; // model name isn't known
    }
}
