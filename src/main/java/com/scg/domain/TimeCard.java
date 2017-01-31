package com.scg.domain;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by chq-ruchic on 1/23/2017.
 */
public final  class TimeCard {
    Consultant consultant;
    LocalDate weekStartingDay;
    int totalBillableHours;
    int totalNonBillableHours;
    int totalHours;
    List<ConsultantTime> consultantTimes = new ArrayList<>();
    public TimeCard(Consultant consultant,java.time.LocalDate weekStartingDay){
        this.consultant = consultant;
        this.weekStartingDay = weekStartingDay;

    }
    public Consultant getConsultant(){
        return consultant;
    }
    public int getTotalBillableHours() {
    	int totalBillableHours = 0;
        for (ConsultantTime temp : consultantTimes) {
            if (temp.getAccount().isBillable()) {
                totalBillableHours += temp.getHours();
            }
        }
        return totalBillableHours;
    }
    
    public int getTotalNonBillableHours() {
    	int totalNonBillableHours = 0;
        for (ConsultantTime temp : consultantTimes) {
            if (!temp.getAccount().isBillable()) {
                totalNonBillableHours += temp.getHours();
            }
        }
        return totalNonBillableHours;
    }
    
    public List<ConsultantTime> getConsultingHours(){

        return consultantTimes;
    }
    
    public void addConsultantTime(ConsultantTime consultantTime){

        consultantTimes.add(consultantTime);

        }
    public int getTotalHours(){
        int totalHours = this.getTotalBillableHours()+ this.getTotalNonBillableHours();
        return totalHours;
    }
    public java.time.LocalDate getWeekStartingDay(){
        return weekStartingDay;
    }
    public List<ConsultantTime> getBillableHoursForClient(String clientName) {
        List<ConsultantTime> billableHoursForClient = new ArrayList<>();

        for (ConsultantTime temp : consultantTimes) {
            if (temp.getAccount().getName().equals(clientName)) {
                billableHoursForClient.add(temp);
            }
        }
        return billableHoursForClient;
    }
    public String toString(){
        StringBuilder bldr = new StringBuilder();
        bldr.append('"');
        bldr.append(getConsultant());
        bldr.append(",");
        bldr.append(getWeekStartingDay() );
        return bldr.toString();
    }
    public String printBillableHours(){
    	StringBuilder data = new StringBuilder();  
    	for (ConsultantTime temp : consultantTimes) {
            if (temp.getAccount().isBillable()) {
            	String s = temp.getAccount().getName()
            			+ "\t"
            			+temp.getDate()
            			+"\t"
            			+temp.getHours()
            			+"\t"
            			+ temp.getSkill()
            			+"\n";
            		     
            	data.append(s);
            }
        }
    	return data.toString();
    }

    public String printNonBillableHours(){
        StringBuilder data = new StringBuilder();
        for (ConsultantTime temp : consultantTimes) {
            if (!temp.getAccount().isBillable()) {
                String s = temp.getAccount().getName()
                        + "\t"
                        +temp.getDate()
                        +"\t"
                        +temp.getHours()
                        +"\t"
                        + temp.getSkill()
                        +"\n";

                data.append(s);
            }
        }
        return data.toString();
    }

    public String toReportString(){
    	String consultantName = this.getConsultant().getName().getLastName()+ " , " + this.getConsultant().getName().getFirstName() +" " + this.getConsultant().getName().getMiddleName();

        String format = "%n====================================================================%n"
         		+ "Consultant: %s\t\t Week Starting: %s"
         		+ "\nBillable Time:\n"
         		+ "Account\t\t\tDate\t\tHours\tSkill%n"
         		+ "----------------------  --------------  -----   --------------------%n"
         		+ "%s"
                + "\nNon-Billable Time:\n"
 		+ "Account\t\t\tDate\t\tHours\tSkill%n"
 		+ "----------------------  --------------  -----   --------------------%n" + "%s"
 		+"Summary:\n"
 		+"Total Billable Hours:\t\t\t %s %n"
 		+"Total Non-Billable Hours: \t\t %s %n"
 		+"Total Hours:  \t\t\t\t%s %n"
         +"%n====================================================================%n";
         
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
         String formattedString = this.getWeekStartingDay().format(formatter);
         String s = String.format(format, consultantName, formattedString,printBillableHours(),printNonBillableHours(),this.getTotalBillableHours(), this.getTotalNonBillableHours(),this.getTotalHours());
         return s;
    }
               

}
