

package com.scg.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * @author chq-ruchic
 *
 */
public final class TimeCardListUtil {
	/**
	 * Get a list of TimeCards for the specified consultant.
	 * @param timeCards - the list of time cards to extract the sub set from
	 * @param consultant - the consultant whose TimeCards will be obtained.
	 * @return a list of TimeCards for the specified consultant.
	 */
	public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant)  {
		List<TimeCard> tc = timeCards;
		Consultant con = consultant;
		List<TimeCard> timeCardsForConsultant = new ArrayList();
		for(TimeCard temp: tc){
			if(temp.equals(con))
				timeCardsForConsultant.add(temp);	
		}
		return timeCardsForConsultant;
	}
	/**
	 * Get a list of TimeCards that cover dates that fall within a date range.
	 * @param timeCards - the list of time cards to extract the sub set from
	 * @param dateRange - The DateRange within which the dates of the returned TimeCards must fall.
	 * @return a list of TimeCards having dates fall within the date range.
	 */
	public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange)  {
		List<TimeCard> tc = timeCards;
		DateRange dateRange1= dateRange;
		List<TimeCard> timeCardsForDateRange = new ArrayList();
		for(TimeCard temp: tc){
			if(dateRange1.isInRange(temp.getWeekStartingDay()))
				timeCardsForDateRange.add(temp);	
		}
		return timeCardsForDateRange;
	}
	/**
	 * Sorts this list into ascending order by consultant name..
	 * @param timeCards - the list of time cards to sort
	 * 
	 */
	
	public static void sortByConsultantName(List<TimeCard> timeCards)  {
		List<TimeCard> tc = timeCards;
		List<TimeCard> timeCardstemp = new ArrayList();
		for(TimeCard temp: tc){
			tc.sort((Comparator<? super TimeCard>) temp.getConsultant());
		}
	}
	/**
	 * Sorts this list into ascending order, by the start date.
	 * @param timeCards - the list of time cards to sort
	 * 
	 */
	public static void sortByStartDate(List<TimeCard> timeCards)  {
		
	}
}
