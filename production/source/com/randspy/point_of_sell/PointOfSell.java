package com.randspy.point_of_sell;

import java.util.Optional;

public class PointOfSell {
    private String message = "";
    private BarcodeRepository barcodeRepository;

    public PointOfSell(BarcodeRepository barcodeRepository) {

        this.barcodeRepository = barcodeRepository;
    }

    public String displayLastText() {
        return message;
    }

    public void onBarcode(String barcode) {
        if (barcode.isEmpty()) {
            message = "No input code";
        }
        else{

            Optional<ProductItem> item = barcodeRepository.getProductItem(barcode);

            if (item.isPresent()) {
                message = item.get().getPrice();
            }
            else {
                message = "Invalid code";
            }
        }
    }
}
