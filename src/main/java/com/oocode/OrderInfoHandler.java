package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OrderInfoHandler implements GetValueHelper {
    private int widthOfWindow = 0;  // the width of the window
    private int heightOfWindow = 0;  // the height of the window
    private int numberOfWindow = 0;  // the number of windows of this size
    private String modelName = "N/A";       // the model name of these windows
    private String windowType = "N/A";      // window type: plain or toughened
    OkHttpClient client = new OkHttpClient(); // TODO - OkhttpCLient
    private int widthThicknessAllowance = 0;
    private int heightThicknessAllowance = 0;
    private int totalArea = 0;
    private String orderURL = "N/A";
    private String userName = "Tester";


    public OrderInfoHandler(int widthOfWindow, int heightOfWindow, int numberOfWindow, String modelName) throws Exception {
        this.widthOfWindow = widthOfWindow;
        this.heightOfWindow = heightOfWindow;
        this.numberOfWindow = numberOfWindow;
        this.modelName = modelName;
        widthThicknessAllowance = ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(this.modelName);
        heightThicknessAllowance = ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(this.modelName);
    }

    @Override
    public int getWidthOfWindow() { return widthOfWindow; }
    public int getHeightOfWindow() {
        return heightOfWindow;
    }
    public int getNumberOfWindow() { return numberOfWindow; }
    public String getModelName() {
        return modelName;
    }

    public int getCalculatedTotal() {
        totalArea = (widthOfWindow - widthThicknessAllowance) * (heightOfWindow - heightThicknessAllowance) * numberOfWindow;
        if (totalArea < 0)
            throw new IllegalArgumentException("Total area of the window could not be in negative!");
        return totalArea;
    }

    public String getWindowType(){

        if((heightOfWindow > 120) || ( totalArea > 3000))
            windowType = "toughened";
        else
            windowType = "plain";

        return windowType;
    }

    public String getOrderURL() {

        if ( (windowType.equals("plain") && (totalArea > 20000)) || (windowType.equals("toughened") && (totalArea> 18000)))
            orderURL = "https://immense-fortress-19979.herokuapp.com/large-order";
        else
            orderURL = "https://immense-fortress-19979.herokuapp.com/order";

        return orderURL;
    }

    public String getUserName() { return userName; }

    public void orderPlacementHelper() throws Exception {

        RequestBody requestBody = BodyBuilder.bodyBuilderForAnyOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance, windowType, userName);
        Request request = RequestBuilder.placeAnyOrder(requestBody, orderURL);
        ResponseBuilder.responseBuilder(request, client);

    }
}