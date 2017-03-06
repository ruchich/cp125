package com.scg.util;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author chq-ruchic
 * Encapsulates a range of two dates, inclusive of the start date and end date.
 */
public final class DateRange implements Serializable {
	/**
	 * the start date for a DateRange
	 */
	private java.time.LocalDate startDate;
	/**
	 * the end date for a DateRange
	 */
	private java.time.LocalDate endDate;
	/**
	 * the month for which the DateRange should be constructed.
	 */
	private java.time.Month month;
	/**
	 * the calendar year
	 */
	private int year;
	/**
	 * String representing the start date, of the form yyyy-MM-dd.
	 */
	private String start;
	/**
	 * String representing the end date, of the form yyyy-MM-dd.
	 */
	private String end;
	/**
	 * Construct a DateRange given two dates.
	 * @param startDate - the start date for this DateRange.
	 * @param  endDate the end date for this DateRange.
	 */
	public DateRange(java.time.LocalDate startDate, java.time.LocalDate endDate) {
		this.startDate = startDate;
		this.endDate= endDate;
		
	}
	/**
	 * Construct a DateRange given month.
	 * @param  month- the month for which this DateRange should be constructed.
	 * @param  year the calendar year
	 */
	public DateRange(java.time.Month month, int year) {
		this.month = month;
		this.year = year;
	//	int day = 31;
		
		this.startDate = LocalDate.of(this.year, this.month, 1);
		if (this.year % 4 == 0){
		this.endDate = LocalDate.of(this.year, this.month, this.month.maxLength());
		}
		else
		{
			this.endDate = LocalDate.of(this.year, this.month, this.month.minLength());
		}
	}
	
	/**
	 * Construct a DateRange given two date strings in the correct format.
	 * @param  start - String representing the start date, of the form yyyy-MM-dd.
	 * @param  end - String representing the end date, of the form yyyy-MM-dd.
	 */
	
	public DateRange(String start, String end) {
		this.start = start;
		this.end = end;
	}
	/**
	 * Returns the start date for this DateRange.
	 * @return the start date for this DateRange.
	 */
	public java.time.LocalDate getStartDate(){
		return this.startDate;
	}
	/**
	 * Returns the end date for this DateRange.
	 * @return the end date for this DateRange.
	 */
	public java.time.LocalDate getEndDate(){
		return this.endDate;
	}
	/**
	 * Returns true if the specified date is within the range start date <= date <= end date.
	 * @param date - the date to check for being within this DateRange.
	 * @return true if the specified date is within this DateRange.
	 */
	public boolean isInRange(java.time.LocalDate date){
		boolean result = false;
		if((this.getStartDate().equals(date) || this.getStartDate().isBefore(date)) && (this.getEndDate().equals(date)||this.getEndDate().isAfter(date)))
				{result = true;}
		return result;
}
}
 