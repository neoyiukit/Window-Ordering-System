package com.oocode;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OrderGlass implements GetValues {
    int widthOfWindow;  // the width of the window
    int heightOfWindow;  // the height of the window
    int numberOfWindow;  // the number of windows of this size
    String modelName;       // the model name of these windows
    String windowType;      // window type: plain or toughened
    OkHttpClient client = new OkHttpClient(); // TODO - OkhttpCLient
    int widthThicknessAllowance;
    int heightThicknessAllowance;


    public OrderGlass(int widthOfWindow, int heightOfWindow, int numberOfWindow, String modelName) throws Exception {
        this.widthOfWindow = widthOfWindow;
        this.heightOfWindow = heightOfWindow;
        this.numberOfWindow = numberOfWindow;
        this.modelName = modelName;
        this.widthThicknessAllowance = ReturnThicknessAllowance.ReturnWidthThicknessAllowance(this.modelName);
        this.heightThicknessAllowance = ReturnThicknessAllowance.ReturnHeightThicknessAllowance(this.modelName);

        if(heightOfWindow > 120 || (getCalculatedTotal(this.widthOfWindow, this.heightOfWindow, this.numberOfWindow, this.widthThicknessAllowance, this.heightThicknessAllowance) > 3000))
             this.windowType = "toughened";
        else
             this.windowType = "plain";
    }

    @Override
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
    public int getCalculatedTotal(int widthOfWindow, int heightOfWindow, int numberOfWindow, int widthThicknessAllowance, int heightThicknessAllowance) {
        int total = (widthOfWindow - widthThicknessAllowance) * (heightOfWindow - heightThicknessAllowance) * numberOfWindow;
        return total;
    }
    public String getWindowType(){
        return windowType;
    }

    // the thickness of the frame depends on the model of window
    public void orderDetermination() throws Exception {

        RequestBody requestBody = BodyBuilder.bodyBuilderForSmallOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance, windowType);
        if (heightOfWindow > 120) {
            requestBody = BodyBuilder.bodyBuilderForLargeOrders(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance, windowType);
        }

        Request request = RequestBuilder.placeSmallOrder(requestBody);
        ResponseBuilder.responseBuilder(request, client);

        if ((windowType.equals("plain") && getCalculatedTotal(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance) > 20000)
                || (windowType.equals("toughened") && getCalculatedTotal(widthOfWindow, heightOfWindow, numberOfWindow, widthThicknessAllowance, heightThicknessAllowance) > 18000)) {
            request = RequestBuilder.placeLargeOrder(requestBody);
            ResponseBuilder.responseBuilder(request, client);
        }
    }
}