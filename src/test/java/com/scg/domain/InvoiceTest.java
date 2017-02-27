package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by chq-ruchic on 2/15/2017.
 */
public class InvoiceTest {


    java.time.Month invoiceMonth = Month.MARCH;
    int invoiceYear = 2006;
    Name contact = new Name("Singh","Robin", "");
    Address address = new Address("1616 Index Ct.","Redmond", StateCode.WA ,"98055");
    ClientAccount client = new ClientAccount("XYZ Consultant",  contact, address);
    Invoice invoice = new Invoice( client,invoiceMonth,invoiceYear) ;
    Consultant consultant = new Consultant(contact);
    LocalDate date = LocalDate.of(2017,03,20);
    Skill skill = Skill.SOFTWARE_ENGINEER;
    int hours = 8;
    InvoiceLineItem lineItem=new InvoiceLineItem( date, consultant, skill,hours);
    InvoiceLineItem lineItem1=new InvoiceLineItem( date, consultant, skill,hours);
    List<InvoiceLineItem> actualLineItems = new ArrayList();
    List<InvoiceLineItem> expectedLineItems = new ArrayList();
    ConsultantTime cTime = new ConsultantTime (date, client,Skill.SOFTWARE_ENGINEER, hours);
    TimeCard tC = new TimeCard(consultant,date);


    @Test
    public void getStartDate() throws Exception {
        LocalDate actualDate = LocalDate.of(invoiceYear, invoiceMonth, 1);
        LocalDate expectedDate = invoice.getStartDate();
        Assert.assertEquals(expectedDate,actualDate);
    }

    @Test
    public void getInvoiceMonth() throws Exception {
        java.time.Month actualInvoiceMonth = invoice.getInvoiceMonth();
        Assert.assertEquals(invoiceMonth,actualInvoiceMonth);
    }

    @Test
    public void getClientAccount() throws Exception {
        ClientAccount actualClient = invoice.getClientAccount();
        Assert.assertEquals(client,actualClient);
    }



    @Test
    public void getTotalHours() throws Exception {
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem1);
        int actualHours = invoice.getTotalHours();
        assertEquals(16,actualHours );
    }

    @Test
    public void getTotalCharges() throws Exception {
        invoice.addLineItem(lineItem);
        invoice.addLineItem(lineItem1);
        int actualCharge = invoice.getTotalCharges();
        assertEquals(2400,actualCharge );
    }

   
    }


