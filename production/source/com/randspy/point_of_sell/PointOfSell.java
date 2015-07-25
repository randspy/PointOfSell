package com.randspy.point_of_sell;

public class PointOfSell {
    private String message = "";

    public String displayLastText() {
        return message;
    }

    public void onBarcode(String barcode) {
        if (barcode.isEmpty()) {
            message = "No input code";
        }
        else {
            message = "Invalid code";
        }
    }
}
