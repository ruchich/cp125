package com.scg.domain;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public class InvoiceFooter {
    String businessName;
    int currentPageNumber ;
    InvoiceFooter(String businessName){
        this.businessName = businessName;

    }

   public void incrementPageNumber(){

      currentPageNumber++;


   }
}
