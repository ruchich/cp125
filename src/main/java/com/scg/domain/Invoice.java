package com.scg.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    String businessName;
    String businessAddress;


    public Invoice(ClientAccount client,
                   java.time.Month invoiceMonth,
                   int invoiceYear) {
        this.client = client;
        this.invoiceMonth = invoiceMonth;
        this.invoiceYear = invoiceYear;

    }

    String streetNumber;
    String city;
    StateCode state;
    String postalCode;
    Address invoiceHeaderAddress = new Address(streetNumber, city, state, postalCode);
    InvoiceHeader invoiceheader = new InvoiceHeader(businessName, invoiceHeaderAddress, this.getClientAccount(), this.getStartDate(), java.time.LocalDate.now());
    InvoiceFooter invoiceFooter =  new InvoiceFooter(businessName);
   
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
            if (temp.getAccount().getName().equals(this.getClientAccount())) {
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
            totalCharges += item.getHours();
        }
        return totalCharges;
    }

    public String printLineItems() {
        StringBuilder data = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (InvoiceLineItem temp : lineItems) {

            String s = temp.date.format(formatter)
                    + "\t"
                    + temp.getConsultant()
                    + "\t"
                    + temp.getSkill()
                    + "\t"
                    + temp.getHours()
                    + "\t"
                    + temp.getCharge()
                    + "\n";

            data.append(s);

        }
        return data.toString();
    }

    public void readInvoicePropertiesFile() {

        try {
            File file = new File("invoice.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                if (key.equals("business.name")) {
                    businessName = value;
                } else if (key.equals("business.street")) {
                    streetNumber = value;
                } else if (key.equals("business.city")) {
                    city = value;
                } else if (key.equals("business.state")) {
                    for (StateCode s : StateCode.values()) {
                        if (s.equals(value)) {
                            state = StateCode.valueOf(value);
                        }
                    }
                } else if (key.equals("business.zip")) {
                    postalCode = value;
                }

            }

            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String toReportString(){

        String format = "%s %n"
                +"%s %n"
                +"Invoice for:\n"
                +this.getClientAccount().getName().toString()
                +"\n"+this.getClientAccount().getAddress()
                +"\n" + this.getClientAccount().getContact()
                + "Invoice For Month of: " +this.getInvoiceMonth()
                + "\nInvoice Date: " + this.getStartDate()
                + "Date\t\t\tConsultant\t\tSkill\tHours\tCharge%n"
                + "----------  ---------------------------  ------------------   -----  ----------%n"
                + "%s"
                +"\nTotal:  \t\t\t\t%s\t\t%s %n"
                +"%n====================================================================%n"
        + invoiceFooter.printFooter();
        String s = String.format(format,invoiceheader.businessName,invoiceheader.businessAddress,printLineItems(),this.getTotalHours(),this.getTotalCharges() );
        System.out.println(s);
        return s;
    }
}
