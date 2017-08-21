package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OrderGenerator implements GetValueHelper {
    int widthOfWindow;  // the width of the window
    int heightOfWindow;  // the height of the window
    int numberOfWindow;  // the number of windows of this size
    String modelName;       // the model name of these windows
    String windowType;      // window type: plain or toughened
    OkHttpClient client = new OkHttpClient(); // TODO - OkhttpCLient
    int widthThicknessAllowance;
    int heightThicknessAllowance;
    int totalArea;
    String orderURL = "https://immense-fortress-19979.herokuapp.com/order";


    public OrderGenerator(int widthOfWindow, int heightOfWindow, int numberOfWindow, String modelName) throws Exception {
        this.widthOfWindow = widthOfWindow;
        this.heightOfWindow = heightOfWindow;
        this.numberOfWindow = numberOfWindow;
        this.modelName = modelName;
        this.widthThicknessAllowance = ThicknessAllowanceHelper.ReturnWidthThicknessAllowance(this.modelName);
        this.heightThicknessAllowance = ThicknessAllowanceHelper.ReturnHeightThicknessAllowance(this.modelName);
        this.totalArea = (widthOfWindow - widthThicknessAllowance) * (heightOfWindow - heightThicknessAllowance) * numberOfWindow;

        if((heightOfWindow > 120) || ( totalArea > 3000))
             this.windowType = "toughened";
        else
             this.windowType = "plain";

        if ( (windowType.equals("plain") && (totalArea > 20000)) || (windowType.equals("toughened") && (totalArea> 18000)))
            orderURL = "https://immense-fortress-19979.herokuapp.com/large-order";
    }

    @Override
    public int getWidthOfWindow() {
        return widthOfWindow;
    }
    public int getHeightOfWindow() {
        return heightOfWindow;
    }
    public int getNumberOfWindow() { return numberOfWindow; }
    public String getModelName() {
        return modelName;
    }
    public int getCalculatedTotal() { return totalArea; }
    public String getWindowType(){
        return windowType;
    }

    public void orderPlacementHelper() throws Exception {

        RequestBody requestBody = BodyBuilder.bodyBuilderForAnyOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance, windowType);
        Request request = RequestBuilder.placeAnyOrder(requestBody, orderURL);
        ResponseBuilder.responseBuilder(request, client);

    }
}