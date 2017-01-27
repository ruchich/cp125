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
        for (ConsultantTime temp : consultantTimes) {
            if (temp.getAccount().isBillable()) {
                totalBillableHours += temp.getHours();
            }
                    }
        return totalBillableHours;
    }
    public int getTotalNonBillableHours() {
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
    public String toReportString(){
     /*  StringBuilder bldr = new StringBuilder();
        bldr.append("====================================================================\n");
        bldr.append("Consultant: ");
        bldr.append(this.getConsultant().getName().getLastName()+ " , " + this.getConsultant().getName().getFirstName() +" " + this.getConsultant().getName().getMiddleName())
        .append;*/
        //String format = "%-40s%s%n";
        String consultantName = this.getConsultant().getName().getLastName()+ " , " + this.getConsultant().getName().getFirstName() +" " + this.getConsultant().getName().getMiddleName();

        //String date = getWeekStartingDay().format(DateTimeFormatter.ofPattern("MMM dd, uuuu"));
        String format = "====================================================================%nConsultant: %1$%100Week Starting: %2$tb %2$te,%2$tY%n Billable Time:Account%40%20Hours%10%Skill%n---------------------------  ----------  -----  --------------------%nBillable Time:Account%40Date%20Hours%10%skill%n---------------------------  ----------  -----  --------------------%nSummary:Total Billable:%30%3$%nTotal Non-Billable:%30%4$%nTotal Hours:%30%5$%n====================================================================\n";
        String s = String.format(format, consultantName, this.getWeekStartingDay(),this.getTotalBillableHours(), this.getTotalNonBillableHours(),this.getTotalHours());


        return s;
    }

}
