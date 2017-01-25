package com.scg.domain;


import java.time.LocalDate;
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
    public TimeCard(Consultant consultant,java.time.LocalDate weekStartingDay){
        this.consultant = consultant;
        this.weekStartingDay = weekStartingDay;

    }
    public Consultant getConsultant(){
        return consultant;
    }
    public int getTotalBillableHours(){
        return totalBillableHours;
    }
    public int getTotalNonBillableHours(){
        return totalNonBillableHours;
    }
    public List<ConsultantTime> getConsultingHours(){

    }
    public void addConsultantTime(ConsultantTime consultantTime){

    }
    public int getTotalHours(){
        return totalHours;
    }
    public java.time.LocalDate getWeekStartingDay(){
        return weekStartingDay;
    }
    public List<ConsultantTime> getBillableHoursForClient(String clientName){

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
        String str = TimeCard.class.toString();
        return str;
    }

}
