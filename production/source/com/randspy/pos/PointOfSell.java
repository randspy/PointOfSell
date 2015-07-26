package com.randspy.pos;

import java.util.Optional;

public class PointOfSell {
    private Display display;
    private ProductCatalog productCatalog;

    public PointOfSell(ProductCatalog productCatalog, Display display) {
        this.productCatalog = productCatalog;
        this.display = display;
    }

    public void onBarcode(String barcode) {
        if (isBarCodeEmpty(barcode)) {
            display.displayMissingCode();
            return;
        }

        Optional<ProductItem> item = productCatalog.getProductItem(barcode);

        if (item.isPresent()) {
            display.displayPrice(item.get());
        }
        else {
            display.displayInvalidCode();
        }

    }

    private boolean isBarCodeEmpty(String barcode) {
        return  barcode == null ||
                barcode.isEmpty() ||
                barcode.equals("\n") ||
                barcode.equals("\n\r");
    }
}
