package com.randspy.point_of_sell;

import java.util.Optional;

public class PointOfSell {
    private Display display;
    private BarcodeRepository barcodeRepository;

    public PointOfSell(BarcodeRepository barcodeRepository, Display display) {
        this.barcodeRepository = barcodeRepository;
        this.display = display;
    }

    public void onBarcode(String barcode) {
        if (barcode.isEmpty()) {
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
}
