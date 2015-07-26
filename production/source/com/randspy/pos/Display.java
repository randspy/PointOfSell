package com.randspy.pos;

public class Display {
    public void send(String message) {
    }

    void displayPrice(ProductItem item) {
        send(item.getPrice());
    }

    void displayInvalidCode() {
        send("Invalid code");
    }

    void displayMissingCode() {
        send("No input code");
    }
}
