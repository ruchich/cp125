package com.scg.domain;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public class InvoiceFooter {
    String businessName;
    int currentPageNumber=1 ;
  public  InvoiceFooter(String businessName){
        this.businessName = businessName;

    }
    public String printFooter(){
        String s = this.businessName+"\t\t\t\t\t\t\t\tPage:  " + currentPageNumber;
                this.incrementPageNumber();
        return s;
    }

    public void incrementPageNumber(){

      currentPageNumber++;


   }
}
