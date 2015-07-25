package com.randspy.pos;

import java.util.Optional;

public class PointOfSell {
    private Display display;
    private BarcodeRepository barcodeRepository;

    public PointOfSell(BarcodeRepository barcodeRepository, Display display) {
        this.barcodeRepository = barcodeRepository;
        this.display = display;
    }

    public void onBarcode(String barcode) {
        if (isBarCodeEmpty(barcode)) {
            display.send("No input code");
        }
        else{

            Optional<ProductItem> item = barcodeRepository.getProductItem(barcode);

            if (item.isPresent()) {
                display.send(item.get().getPrice());
            }
            else {
                display.send("Invalid code");
            }
        }
    }

    private boolean isBarCodeEmpty(String barcode) {
        return  barcode == null ||
                barcode.isEmpty() ||
                barcode.equals("\n") ||
                barcode.equals("\n\r");
    }
}