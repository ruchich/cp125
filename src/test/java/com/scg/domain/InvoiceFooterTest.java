package com.scg.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/15/2017.
 */
public class InvoiceFooterTest {
    String businessName = "XYZ Consultant";
      InvoiceFooter footer = new InvoiceFooter( businessName);

    @Test
    public void printFooter() throws Exception {

        String actual = footer.printFooter();
        String expected = "XYZ Consultant" +"\t\t\t\t\t\t\t\tPage:  1";
        assertEquals(expected, actual);
    }

}