package com.oocode;

public class Main {
    public static void main(String[] args) throws Exception {
        int widthOfWindow = Integer.parseInt(args[0]);  // the width of the window
        int heightOfWindow = Integer.parseInt(args[1]);  // the height of the window
        int numberOfWindow = Integer.parseInt(args[2]);  // the number of windows of this size
        String modelName = args[3];                 // the model name of these windows

        new OrderGlass(widthOfWindow, heightOfWindow, numberOfWindow, modelName);

        // TODO User Inputs starting from here - including username (tester says tester in the content)

    }
}
