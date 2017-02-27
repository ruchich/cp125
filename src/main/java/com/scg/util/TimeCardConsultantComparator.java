package com.scg.util;

import java.util.Comparator;

import com.scg.domain.TimeCard;

/**
 *  @author chq-ruchic
 * Compares two TimeCard objects by ascending consultant, timecard week, totalHours, totalBillableHours and totalNonBillableHours.
 */

public final class TimeCardConsultantComparator implements Comparator<TimeCard> {
	
 public TimeCardConsultantComparator() {
	 
 }
 /**
  *  Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
  * @param firstTimeCard - the first object to be compared.
  * @param secondTimeCard - the second object to be compared.
  * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
  */
 public int compare(TimeCard firstTimeCard,
         TimeCard secondTimeCard){
	 int result =0;
	 if(firstTimeCard!=secondTimeCard ){
		 result = firstTimeCard.compareTo(secondTimeCard);
		}
	 	 return result;
 }
}
