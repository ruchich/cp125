

package com.scg.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * @author chq-ruchic
 *
 */
public final class TimeCardListUtil {
	/** Days per week. */
	private static final int DAYS_PER_WEEK = 6; // This is actually days per week - 1
	/**
	 * Get a list of TimeCards for the specified consultant.
	 * @param timeCards - the list of time cards to extract the sub set from
	 * @param consultant - the consultant whose TimeCards will be obtained.
	 * @return a list of TimeCards for the specified consultant.
	 */
	public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant)  {
		Stream<TimeCard> s = timeCards.stream(); 
		List<TimeCard> timeCardsForConsultant;
		timeCardsForConsultant = s.filter(t -> t.getConsultant().getName().equals(consultant.getName())).collect(Collectors.toList());
		return timeCardsForConsultant;
		}
	/**
	 * Get a list of TimeCards that cover dates that fall within a date range.
	 * @param timeCards - the list of time cards to extract the sub set from
	 * @param dateRange - The DateRange within which the dates of the returned TimeCards must fall.
	 * @return a list of TimeCards having dates fall within the date range.
	 */
	public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange)  {
		Stream<TimeCard> s = timeCards.stream(); 
		List<TimeCard> timeCardsForDateRange ;
		timeCardsForDateRange = s.filter(t ->dateRange.isInRange(t.getWeekStartingDay()) ||
				dateRange.isInRange(t.getWeekStartingDay().plusDays(DAYS_PER_WEEK))).collect(Collectors.toList());
		return timeCardsForDateRange;
	}
	/**
	 * Sorts this list into ascending order by consultant name..
	 * @param timeCards - the list of time cards to sort
	 * 
	 */
	
	public static void sortByConsultantName(List<TimeCard> timeCards)  {
			timeCards.sort((t1,t2 )-> t1.getConsultant().compareTo(t2.getConsultant()));
		
		}
	
	/**
	 * Sorts this list into ascending order, by the start date.
	 * @param timeCards - the list of time cards to sort
	 * 
	 */
	public static void sortByStartDate(List<TimeCard> timeCards)  {
		timeCards.sort((t1,t2 )-> t1.getWeekStartingDay().compareTo(t2.getWeekStartingDay()));
	}
}
