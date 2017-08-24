package com.oocode;

public class WindowsOrderingSystem {

    public static void OrderGenerator(String[] args) throws Exception {
        int widthOfWindow = Integer.parseUnsignedInt(args[0]); // using parseUnsignedInt to prompt the input value to be non-zero - the width of the window
        int heightOfWindow = Integer.parseUnsignedInt(args[1]);  // the height of the window
        int numberOfWindow = Integer.parseUnsignedInt(args[2]);  // the number of windows of this size
        String modelName = args[3]; // the model name of these windows
        String userName = args[4]; // username of clients

        new OrderInfoHandler(widthOfWindow, heightOfWindow, numberOfWindow, modelName, userName);

        // TODO User Inputs starting from here - including username (tester says tester in the content)

    }
}