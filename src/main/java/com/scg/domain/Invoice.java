package com.scg.domain;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Enumeration;
import java.util.Properties;

import com.scg.util.Address;
import com.scg.util.ListFactory;
import com.scg.util.StateCode;

import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public final class Invoice {
    ClientAccount client;
    java.time.Month invoiceMonth = Month.MARCH;
    int invoiceYear = 2006;
static String bizName;
    static  Address bizAddress;
    static  String bizStreet="";
    static  String bizCity = "";
    static  String bizState = "";
    static  String bizZip = "";


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
            totalHours += item.getHours();
        }
        return totalHours;
    }

    public int getTotalCharges() {
        int totalCharges = 0;
        for (InvoiceLineItem item : lineItems) {
            totalCharges += item.getCharge();
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
    public String printLineItems() {
        StringBuilder data = new StringBuilder();
        String s;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
         {
                for(int i=0, line=1;i<lineItems.size();i++){
                    for (InvoiceLineItem temp : lineItems) {

                        if (line <= 5) {
                                if(temp.date.getMonth())
                            s = temp.date.format(formatter)
                                    + "\t"
                                    + temp.getConsultant()
                                    + "\t\t\t\t"
                                    + temp.getSkill()
                                    + "\t\t"
                                    + temp.getHours()
                                    + "\t\t"
                                    + temp.getCharge()
                                    + "\n";


                        data.append(s);
                    }
                        else{
                            s= invoiceFooter.printFooter();
                            data.append(s);
                            s= printHeader();
                            data.append(s);
                            line =1;
                        }
                    }
                    }

        }
        return data.toString();
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
                + invoiceFooter.printFooter()
                +"%n================================================================================%n";
        String s = String.format(format,printHeader(),printLineItems(),this.getTotalHours(),this.getTotalCharges() );
        System.out.println(s);
        return s;
    }
}
