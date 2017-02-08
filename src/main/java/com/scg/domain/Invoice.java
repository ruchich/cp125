package com.scg.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.scg.util.ListFactory;

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
        // ConsultantTime cTime = new ConsultantTime();
         LocalDate startDate ;
        return startDate;

    }
    public java.time.Month getInvoiceMonth(){

        return invoiceMonth;
    }

    public ClientAccount getClientAccount(){
        return client;
    }
    public void addLineItem(InvoiceLineItem lineItem){
    List<InvoiceLineItem>lineItems = new ArrayList();
    lineItems.add(lineItem);
        }
    public void extractLineItems(TimeCard timeCard){
    	List<InvoiceLineItem>extractLineItems = new ArrayList();
    	if(timeCard.)
    }
}
