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
        pointOfSell.onBarcode("xyz");
        assertEquals("Invalid code", pointOfSell.displayLastText());
    }


    @Test
    public void whenOnBarcodeNeverCalledDisplayShouldStayEmpty() throws Exception {
        assertEquals("", pointOfSell.displayLastText());
    }

    @Test
    public void validInputCode() throws Exception {
        barcodeRepository.productItem = new ProductItem("$9.99");
        pointOfSell.onBarcode("123456789");
        assertEquals("123456789", barcodeRepository.barcode);
        assertEquals("$9.99", pointOfSell.displayLastText());
    }
}
