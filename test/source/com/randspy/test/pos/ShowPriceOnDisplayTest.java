package com.randspy.test.pos;
import com.randspy.pos.BarcodeRepository;
import com.randspy.pos.Display;
import com.randspy.pos.PointOfSell;
import com.randspy.pos.ProductItem;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ShowPriceOnDisplayTest {

    private PointOfSell pointOfSell;
    private BarcodeRepositorySpy barcodeRepository;
    private DisplaySpy display;

    private class BarcodeRepositorySpy extends BarcodeRepository {

        public ProductItem productItem;
        private String barcode;

        public Optional<ProductItem> getProductItem(String barcode) {
            this.barcode = barcode;
            return productItem != null ? Optional.of(productItem) : Optional.ofNullable(null);
        }
    }

    private class DisplaySpy extends Display {
        public String receivedText;

        @Override
        public void send(String message) {
            receivedText = message;
        }
    }

    @Before
    public void setUp() throws Exception {
        barcodeRepository = new BarcodeRepositorySpy();
        display = new DisplaySpy();
        pointOfSell = new PointOfSell(barcodeRepository, display);
    }

    @Test
    public void emptyInput() throws Exception {
        pointOfSell.onBarcode("");
        assertEquals("No input code", display.receivedText);
    }

    @Test
    public void nullInput() throws Exception {
        pointOfSell.onBarcode(null);
        assertEquals("No input code", display.receivedText);
    }

    @Test
    public void newLineInput() throws Exception {
        pointOfSell.onBarcode("\n");
        assertEquals("No input code", display.receivedText);
    }

    @Test
    public void carriageReturnInput() throws Exception {
        pointOfSell.onBarcode("\n\r");
        assertEquals("No input code", display.receivedText);
    }


    @Test
    public void invalidInputCode() throws Exception {
        String barcode = "xyz";
        pointOfSell.onBarcode(barcode);

        assertEquals(barcode, barcodeRepository.barcode);
        assertEquals("Invalid code", display.receivedText);
    }


    @Test
    public void validInputCode() throws Exception {
        String price = "$9.99";
        String barcode = "123456789\n";

        barcodeRepository.productItem = new ProductItem(price);
        pointOfSell.onBarcode(barcode);

        assertEquals(barcode, barcodeRepository.barcode);
        assertEquals(price, display.receivedText);
    }
}
