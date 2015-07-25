package com.randspy.test.point_of_sell;
import com.randspy.point_of_sell.PointOfSell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShowPriceOnDisplay {

    @Test
    public void noInput() throws Exception {
        PointOfSell pointOfSell = new PointOfSell();
        pointOfSell.onBarcode("");
        assertEquals("No input code", pointOfSell.displayLastText());
    }

    @Test
    public void invalidInput() throws Exception {
        PointOfSell pointOfSell = new PointOfSell();
        pointOfSell.onBarcode("xyz");
        assertEquals("Invalid code", pointOfSell.displayLastText());
    }
}
