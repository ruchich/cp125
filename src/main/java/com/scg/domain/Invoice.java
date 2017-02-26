package com.scg.domain;

import java.io.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import java.util.Properties;

import com.scg.util.Address;

import com.scg.util.StateCode;



/**
 * Created by chq-ruchic on 2/3/2017.
 */
public final class Invoice {
    private ClientAccount client;
    private java.time.Month invoiceMonth = Month.MARCH;
    private int invoiceYear = 2006;
    private static String bizName;
    private static  Address bizAddress;



    public Invoice(ClientAccount client,
                   java.time.Month invoiceMonth,
                   int invoiceYear) {
        this.client = client;
        this.invoiceMonth = invoiceMonth;
        this.invoiceYear = invoiceYear;

    }
    private static String BUSINESS_NAME_PROP = "business.name";
    private static String BUSINESS_STREET_PROP = "business.street";
     private static String BUSINESS_CITY_PROP = "business.city";
    private static String BUSINESS_State_PROP = "business.state";
    private static String BUSINESS_POSTALCODE_PROP = "business.zip";
    private static String NA = "";

   InvoiceHeader invoiceheader = new InvoiceHeader(bizName, bizAddress, this.getClientAccount(), this.getStartDate(), java.time.LocalDate.now());
    InvoiceFooter invoiceFooter =  new InvoiceFooter(bizName);
   
    int month = invoiceMonth.getValue();
    List<InvoiceLineItem> lineItems = new ArrayList();

    public java.time.LocalDate getStartDate() {

        LocalDate startDate;
        int year = invoiceYear;
        int month = invoiceMonth.getValue();
        
        startDate = LocalDate.of(invoiceYear, month, 1);

        return startDate;

    }

    public java.time.Month getInvoiceMonth() {

        return invoiceMonth;
    }


    public ClientAccount getClientAccount() {
        return client;
    }

    public void addLineItem(InvoiceLineItem lineItem) {

        lineItems.add(lineItem);
    }

    public void extractLineItems(TimeCard timeCard) {
        List<ConsultantTime> consultantTimes = timeCard.getConsultingHours();

        for (ConsultantTime temp : consultantTimes) {
            String acc = temp.getAccount().getName();
            if (acc.equals(this.getClientAccount().getName())) {
                java.time.LocalDate ldate = temp.getDate();
                Consultant lconsultant = timeCard.getConsultant();
                Skill lskill = temp.getSkill();
                int lhours = temp.getHours();
                InvoiceLineItem lineItem = new InvoiceLineItem(ldate, lconsultant, lskill, lhours);
                addLineItem(lineItem);
            }
        }
    }

    public int getTotalHours() {
        int totalHours = 0;
        for (InvoiceLineItem item : lineItems) {
        	if (item.date.getMonth().equals(this.getInvoiceMonth()))
        	{
        		totalHours += item.getHours();
        	}
        }
        return totalHours;
    }

    public int getTotalCharges() {
        int totalCharges = 0;
        for (InvoiceLineItem item : lineItems) {
        	if (item.date.getMonth().equals(this.getInvoiceMonth()))
        	{
        		totalCharges += item.getCharge();
        	}
        }
        return totalCharges;
    }
    public String printHeader(){
        String format = "%s %n"
                +"%s %n%n"
                +"Invoice for:\n"
                +this.getClientAccount().toString()
                +"\n" + "\n"
                + "Invoice For Month of: " +this.getInvoiceMonth()+" " + this.invoiceYear
                + "\nInvoice Date: " + this.getStartDate() + "\n"+ "\n"
                + "Date\t\t\tConsultant\t\t\t\t\t\tSkill\t\t\tHours\t\tCharge%n"
                + "----------  ---------------------------  ------------------   -----  ----------%n";
        String s = String.format(format,invoiceheader.businessName.toString(),invoiceheader.businessAddress.toString());
    return s;
    }



    static {
       final Properties invoiceProps = new Properties();

            try (InputStream in = Invoice.class.getResourceAsStream("/invoice.properties")) {
                invoiceProps.load(in);
            }

         catch (IOException e) {
            e.printStackTrace();
        }
            
        bizName = invoiceProps.getProperty(BUSINESS_NAME_PROP,NA);
        final String  bizStreet = invoiceProps.getProperty(BUSINESS_STREET_PROP,NA );
        final String bizCity = invoiceProps.getProperty(BUSINESS_CITY_PROP, NA);
        final String bizState = invoiceProps.getProperty(BUSINESS_State_PROP,NA);
        final String bizZip = invoiceProps.getProperty(BUSINESS_POSTALCODE_PROP,NA);
          bizAddress = new Address(bizStreet,bizCity,StateCode.valueOf(bizState),bizZip);
    }
    public String toReportString(){

        String format ="%s"+
                 "%s"
                +"\nTotal: \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t%s\t\t%s %n%n"
                + Invoice.bizName + "\t\t\t\t\t\t\t\tPage: %d"
                +"\n================================================================================\n\n";
        String formatContinuation ="%s"+
                "%s"
               +"%n%n"
               + Invoice.bizName + "\t\t\t\t\t\t\t\tPage: %d"
               +"\n================================================================================\n\n";
        int pageNumer = 1;
        StringBuilder pageData= new StringBuilder();
        StringBuilder reportData= new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        int validLineItemNo = 0;
        for(int i=0 ;i < lineItems.size();i++){
        	if (validLineItemNo > 0 && validLineItemNo % 5 == 0)
        	{
        		reportData.append(String.format(formatContinuation,printHeader(), pageData.toString(), pageNumer));   
        		pageData.setLength(0);
        		validLineItemNo = 0;
        		pageNumer++;
        	}
        	if(lineItems.get(i).date.getMonth().equals(this.getInvoiceMonth()))
        	{
        		validLineItemNo++;
	        	String s = lineItems.get(i).date.format(formatter)
	                    + "\t"
	                    + lineItems.get(i).getConsultant()
	                    + "\t\t\t\t"
	                    + lineItems.get(i).getSkill()
	                    + "\t\t"
	                    + lineItems.get(i).getHours()
	                    + "\t\t"
	                    + lineItems.get(i).getCharge()
	                    + "\n";
	        	pageData.append(s);
        	}
        }
        
        reportData.append(String.format(format,printHeader(), pageData.toString(), this.getTotalHours(),this.getTotalCharges(), pageNumer ));
        
        return reportData.toString();
    }
}
