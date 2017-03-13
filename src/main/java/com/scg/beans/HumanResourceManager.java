package com.scg.beans;
/**
 *  @author parth
 *Responsible for modifying the pay rate and sick leave and vacation hours of staff consultants.
 */
public final class HumanResourceManager {
	public HumanResourceManager() {};
	
	/**
	 * Sets the pay rate for a staff consultant.
	 * @param c- the consultant
	 * @param newPayRate - the new pay rate for the consultant
	 */
	public void adjustPayRate(StaffConsultant c,
            int newPayRate){
		
	}
	/**
	 * Sets the sick leave hours for a staff consultant
	 * @param c - the consultant
	 * @param newSickLeaveHours - the new sick leave hours for the consultant
	 */
	public void adjustSickLeaveHours(StaffConsultant c,
            int newSickLeaveHours){
		
	}
	/**
	 * Sets the vacation hours for a staff consultant.
	 * @param c - the consultant
	 * @param newVacationHours - the new vacation hours for the consultant
	 */
	public void adjustVacationHours(StaffConsultant c,
            int newVacationHours){
		
	}
	/**
	 * Fires a voluntary termination event for the staff consultant.
	 * @param c - the consultant being terminated
	 */
	public void acceptResignation(Consultant c){
}
	/**
	 * Adds a termination listener.
	 * @param l - the listener to add
	 */
	public void addTerminationListener(TerminationListener l){
		
	}
	/**
	 * Removes a termination listener.
	 * @param l the listener to remove
	 */
	public void removeTerminationListener(TerminationListener l){
		
	}
}
