package com.randspy.test.point_of_sell;
import com.randspy.point_of_sell.BarcodeRepository;
import com.randspy.point_of_sell.PointOfSell;
import com.randspy.point_of_sell.ProductItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ShowPriceOnDisplay {

    private PointOfSell pointOfSell;
    private BarcodeRepositoryStub barcodeRepository;

    private class BarcodeRepositoryStub extends BarcodeRepository {

        public ProductItem productItem;
        private String barcode;

        public Optional<ProductItem> getProductItem(String barcode) {
            this.barcode = barcode;
            return productItem != null ? Optional.of(productItem) : Optional.ofNullable(null);
        }
    }

    @Before
    public void setUp() throws Exception {
        barcodeRepository = new BarcodeRepositoryStub();
        pointOfSell = new PointOfSell(barcodeRepository);
    }

    @Test
    public void noInput() throws Exception {
        pointOfSell.onBarcode("");
        assertEquals("No input code", pointOfSell.displayLastText());
    }

    @Test
    public void invalidInputCode() throws Exception {
        String barcode = "xyz";
        pointOfSell.onBarcode(barcode);

        assertEquals(barcode, barcodeRepository.barcode);
        assertEquals("Invalid code", pointOfSell.displayLastText());
    }


    @Test
    public void whenOnBarcodeNeverCalledDisplayShouldStayEmpty() throws Exception {
        assertEquals("", pointOfSell.displayLastText());
    }

    @Test
    public void validInputCode() throws Exception {
        String price = "$9.99";
        String barcode = "123456789";

        barcodeRepository.productItem = new ProductItem(price);
        pointOfSell.onBarcode(barcode);

        assertEquals(barcode, barcodeRepository.barcode);
        assertEquals(price, pointOfSell.displayLastText());
    }
}
