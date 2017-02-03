package com.scg.domain;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public final class Invoice {
    ClientAccount client;
    java.time.Month invoiceMonth;
    int invoiceYear;
    public Invoice(ClientAccount client,
                   java.time.Month invoiceMonth,
                   int invoiceYear){
        this.client = client;
        this.invoiceMonth = invoiceMonth;
        this.invoiceYear = invoiceYear;

    }

    public java.time.LocalDate getStartDate(){
        LocalDate startDate;
        return startDate;

    }
    public java.time.Month getInvoiceMonth(){

        return invoiceMonth;
    }

    public ClientAccount getClientAccount(){
        return client;
    }
}
