package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class OrderInfoHandler {
    private static int widthOfWindow = 0;  // the width of the window
    private static int heightOfWindow = 0;  // the height of the window
    private static int numberOfWindow = 0;  // the number of windows of this size
    private static String modelName = "N/A";       // the model name of these windows
    private static String windowType = "N/A";      // window type: plain or toughened
    private static OkHttpClient client = new OkHttpClient(); // TODO - OkhttpCLient
    private static int widthThicknessAllowance = 0;
    private static int heightThicknessAllowance = 0;
    private static int totalArea = 0;
    private static String orderURL = "N/A";
    private static String userName = "Undefined";


    public OrderInfoHandler(int widthOfWindow, int heightOfWindow, int numberOfWindow, String modelName, String userName) throws Exception {
        this.widthOfWindow = widthOfWindow;
        this.heightOfWindow = heightOfWindow;
        this.numberOfWindow = numberOfWindow;
        this.modelName = modelName;
        this.userName = userName;
        widthThicknessAllowance = ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(this.modelName);
        heightThicknessAllowance = ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(this.modelName);
    }

    public static int getWidthOfWindow() { return widthOfWindow; }
    public static int getHeightOfWindow() {
        return heightOfWindow;
    }
    public static int getNumberOfWindow() { return numberOfWindow; }
    public String getModelName() {
        return modelName;
    }
    public static OkHttpClient getClient() { return client; }

    public static int getCalculatedTotal() {
        totalArea = (widthOfWindow - widthThicknessAllowance) * (heightOfWindow - heightThicknessAllowance) * numberOfWindow;
        if (totalArea < 0)
            throw new IllegalArgumentException("Total area of the window could not be in negative!"); // make sure total calculated area will be positive
        return totalArea;
    }

    public static String getWindowType() {
        if((heightOfWindow > 120) || ( getCalculatedTotal() > 3000))
            windowType = "toughened";
        else
            windowType = "plain";

        return windowType;
    }

    public static String getOrderURL() {
        if ((getWindowType().equals("plain") && (getCalculatedTotal() > 20000)) || (getWindowType().equals("toughened") && (getCalculatedTotal()> 18000)))
            orderURL = "https://immense-fortress-19979.herokuapp.com/large-order";
        else
            orderURL = "https://immense-fortress-19979.herokuapp.com/order";

        return orderURL;
    }

    public String getUserName() { return userName; }

    public static RequestBody getRequestBody() throws Exception {
        RequestBody requestBody = RequestBodyBuilder.bodyBuilderForAnyOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance, windowType, userName);
        return requestBody;
    }
}