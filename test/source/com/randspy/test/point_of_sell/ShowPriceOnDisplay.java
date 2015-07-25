package com.randspy.test.point_of_sell;
import com.randspy.point_of_sell.PointOfSell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShowPriceOnDisplay {

    private PointOfSell pointOfSell;

    @Before
    public void setUp() throws Exception {
        pointOfSell = new PointOfSell();
    }

    @Test
    public void noInput() throws Exception {
        pointOfSell.onBarcode("");
        assertEquals("No input code", pointOfSell.displayLastText());
    }

    @Test
    public void invalidInput() throws Exception {
        pointOfSell.onBarcode("xyz");
        assertEquals("Invalid code", pointOfSell.displayLastText());
    }

    @Test
    public void whenOnBarcodeNeverCalledDisplayShouldStayEmpty() throws Exception {
        assertEquals("", pointOfSell.displayLastText());
    }
}
