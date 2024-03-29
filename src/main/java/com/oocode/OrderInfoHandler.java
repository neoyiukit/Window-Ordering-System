package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class OrderInfoHandler {
    private static int widthOfWindow = 0;  // the width of the window
    private static int heightOfWindow = 0;  // the height of the window
    private static int numberOfWindow = 0;  // the number of windows of this size
    private static String modelName = "N/A";       // the model name of these windows
    private static String glassType = "N/A";      // window type: plain or toughened
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
    public static String getModelName() {
        return modelName;
    }
    public static OkHttpClient getClient() { return client; }
    public static String getUserName() { return userName; }
    public static int getWidthThicknessAllowance() { return widthThicknessAllowance;}
    public static int getHeightThicknessAllowance() { return heightThicknessAllowance;}

    public static int getCalculatedTotal() {
        totalArea = (widthOfWindow - widthThicknessAllowance) * (heightOfWindow - heightThicknessAllowance) * numberOfWindow;
        if (totalArea < 0)
            throw new IllegalArgumentException("Total area of the window could not be in negative!"); // make sure total calculated area will be positive
        return totalArea;
    }

    public static String getGlassType() {
        if((heightOfWindow > 120) || ( getCalculatedTotal() > 3000))
            glassType = "toughened";
        else
            glassType = "plain";

        return glassType;
    }

//  -----------Condition before Question 2---------
//  public static String getWindowType() {
//        if((heightOfWindow > 120)
//            windowType = "toughened";
//        else
//            windowType = "plain";
//
//        return windowType;
//    }

    public static String getOrderURL() {
        // should be if ((getWindowType().equals("toughened")) && (getCalculatedTotal()> 18000)) instead after the demonstration of Question 2
        if ((getGlassType().equals("plain") && (getCalculatedTotal() > 20000)) || (getGlassType().equals("toughened") && (getCalculatedTotal()> 18000))) // after the deployment of question 2, the former condition will be ignored as > 3000 will anyway return window type of "toughened"
            orderURL = "https://immense-fortress-19979.herokuapp.com/large-order";
        else
            orderURL = "https://immense-fortress-19979.herokuapp.com/order";

        return orderURL;
    }

//    -------------Condition before Question 1 --------------
//    public static String getOrderURL() {
//        if (getCalculatedTotal() > 20000)
//            orderURL = "https://immense-fortress-19979.herokuapp.com/large-order";
//        else
//            orderURL = "https://immense-fortress-19979.herokuapp.com/order";
//
//        return orderURL;
//    }

    public static RequestBody getRequestBody() throws Exception {
        RequestBody requestBody = RequestBodyBuilder.bodyBuilderForAnyOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance, glassType, userName);
        return requestBody;
    }
}