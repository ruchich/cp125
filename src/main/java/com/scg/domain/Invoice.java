package com.scg.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.scg.util.ListFactory;

import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

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
    private static Calendar cacheCalendar;
    int month = invoiceMonth.getValue();
    List<InvoiceLineItem>lineItems = new ArrayList();
    public java.time.LocalDate getStartDate(){

        LocalDate startDate;
            int year = invoiceYear;
            int month = invoiceMonth.getValue();
            cacheCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cacheCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
            cacheCalendar.set(Calendar.MONTH, month);
            cacheCalendar.set(Calendar.YEAR, invoiceYear);
          int weekStartdate= cacheCalendar.get(Calendar.DATE);
             startDate = LocalDate.of(invoiceYear, month, weekStartdate);

        return startDate;

    }
    public java.time.Month getInvoiceMonth(){

        return invoiceMonth;
    }

    public ClientAccount getClientAccount(){
        return client;
    }
    public void addLineItem(InvoiceLineItem lineItem){

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
    public int getTotalHours(){
        int totalHours = 0;
        for(InvoiceLineItem item: lineItems ){
            totalHours+= item.getHours();
        }
        return totalHours;
    }
    public int getTotalCharges(){
        int totalCharges = 0;
        for(InvoiceLineItem item: lineItems ){
            totalCharges+= item.getHours();
        }
        return totalCharges;
    }
    public String printLineItems(){
        StringBuilder data = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (InvoiceLineItem temp : lineItems) {

                String s = temp.date.format(formatter)
                        +"\t"
                        +temp.getConsultant()
                        +"\t"
                        + temp.getSkill()
                        +"\t"
                        +temp.getHours()
                        +"\t"
                        +temp.getCharge()
                        +"\n";

                data.append(s);

        }
        return data.toString();
    }
    public String toReportString(){
        InvoiceFooter footer= new InvoiceFooter(business.name);
        String format = "Invoice for:\n"
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
        + footer.businessName.toString()+ footer.
        String s = String.format(format,printLineItems(),this.getTotalHours(),this.getTotalCharges() );
        System.out.println(s);
        return s;
    }
}
