package com.scg.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chq-ruchic on 1/23/2017.
 */
public final  class TimeCard implements Comparable<TimeCard>,Serializable {
	  /** Format string for the time card header. */
    private static final String HEADER_FORMAT = "Consultant: %-28s Week Starting: %2$tb %2$td, %2$tY%n";

    /** Format string for the time card string representation. */
    private static final String TO_STRING_FORMAT = "TimeCard for: %s, Week Starting: %2$tb %2$td, %2$tY%n";

    /** Format string for a line header on the time card. */
    private static final String LINE_HEADER_FORMAT = String.format("%-28s %-10s  %5s  %s%n"
        + "---------------------------  ----------  -----  --------------------%n",
        "Account", "Date", "Hours", "Skill");

    /** A border for the time card */
    private static final String CARD_BORDER = "====================================================================%n";

    /** Format string for a line on the time card. */
    private static final String LINE_FORMAT = "%-28s %2$tm/%2$td/%2$tY  %3$5d  %4$s%n";

    /** Format string for a summary line on the time card. */
    private static final String SUMMARY_LINE_FORMAT = "%-39s  %5d%n";

    /** Format string for the billable time section header on the time card. */
    private static final String BILLABLE_TIME_HEADER_FORMAT = "%nBillable Time:%n";

    /** Format string for the non-billable time section header on the time card. */
    private static final String NON_BILLABLE_TIME_HEADER_FORMAT = "%nNon-billable Time:%n";

    /** Format string for the summary section header on the time card. */
    private static final String SUMMARY_HEADER_FORMAT = "%nSummary:%n";
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
public int compareTo(TimeCard o){
	int result=0;
	if(this!=o){ 
		if((result = consultant.compareTo(o.consultant))==0)
			if((result = weekStartingDay.compareTo(o.weekStartingDay))==0)
				if((result =totalHours-o.totalHours) ==0)
					if((result=totalBillableHours-o.totalBillableHours )==0)
						if((result =totalNonBillableHours-o.totalNonBillableHours) ==0);
		
	}
	return result;
	
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
 /**   public List<ConsultantTime> getBillableHoursForClient(String clientName) {
        List<ConsultantTime> billableHoursForClient = new ArrayList<>();

        for (ConsultantTime temp : consultantTimes) {
            if (temp.getAccount().getName().equals(clientName)) {
                billableHoursForClient.add(temp);
            }
        }
        return billableHoursForClient;
    }**/

    public List<ConsultantTime> getBillableHoursForClient(String clientName) {

        Stream<ConsultantTime> s = consultantTimes.stream();
        List<ConsultantTime> billableHoursForClient= s.filter(t -> t.getAccount().getName().equals(clientName)).collect(Collectors.toList());
         return billableHoursForClient;
 		
 		    }
        
        
    public String printBillableHours(){
    	StringBuilder data = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    	for (ConsultantTime temp : consultantTimes) {
            if (temp.getAccount().isBillable()) {
            	String s = temp.getAccount().getName()
            			+ "\t"
                        +temp.getDate().format(formatter)
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (ConsultantTime temp : consultantTimes) {
            if (!temp.getAccount().isBillable()) {
                String s = temp.getAccount().getName()
                        + "\t"
                        +temp.getDate().format(formatter)
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

    /**
     * Add the consulting hours lines to the invoice.
     *
     * @param formatter the formatter to add the lines to
     * @param hours the list of consulting hours
     * @param billable if true billable hours will be added otherwise non-billable
     */
    private void appendTime(final Formatter formatter, final List<ConsultantTime> hours,
                            final boolean billable) {
        for (final ConsultantTime currentTime : hours) {
            if (currentTime.isBillable() == billable) {
                formatter.format(LINE_FORMAT, currentTime.getAccount().getName(),
                                              currentTime.getDate(),
                                              currentTime.getHours(),
                                              currentTime.getSkill());
            }
        }
    }

    /**
     * Create a string representation of this object, consisting of the
     * consultant name and the time card week starting day.
     *
     * @return a string containing the consultant name and the time card week starting day
     */
    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, consultant.getName(), weekStartingDay);
    }

    /**
     * Create a string representation of this object, suitable for printing the
     * entire time card.
     *
     * @return this TimeCard as a formatted String.
     */
    public String toReportString() {
        final StringBuilder sb = new StringBuilder();
        final Formatter formatter = new Formatter(sb, Locale.US);
        // Put on a header.
        formatter.format(CARD_BORDER)
                 .format(HEADER_FORMAT, consultant.getName(), weekStartingDay)
                 .format(BILLABLE_TIME_HEADER_FORMAT)
                 .format(LINE_HEADER_FORMAT);

        appendTime(formatter, consultantTimes, true);

        formatter.format(NON_BILLABLE_TIME_HEADER_FORMAT)
                 .format(LINE_HEADER_FORMAT);

        appendTime(formatter, consultantTimes, false);

        formatter.format(SUMMARY_HEADER_FORMAT)
                 .format(SUMMARY_LINE_FORMAT, "Total Billable:", totalBillableHours)
                 .format(SUMMARY_LINE_FORMAT, "Total Non-Billable:", totalNonBillableHours)
                 .format(SUMMARY_LINE_FORMAT, "Total Hours:", totalBillableHours + totalNonBillableHours)
                 .format(CARD_BORDER);

        final String s = formatter.toString();
        formatter.close();
        return s;
    }
}
