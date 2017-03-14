package com.scg.beans;

import com.scg.domain.Consultant;

import javax.swing.event.EventListenerList;

/**
 *  @author parth
 *Responsible for modifying the pay rate and sick leave and vacation hours of staff consultants.
 */
public final class HumanResourceManager {
	/** The termination event listeners. */
	private EventListenerList mListenerList = new EventListenerList();

	public HumanResourceManager() {};
	
	/**
	 * Sets the pay rate for a staff consultant.
	 * @param c- the consultant
	 * @param newPayRate - the new pay rate for the consultant
	 */
	public void adjustPayRate(StaffConsultant c,int newPayRate){
		//c.setPayRate(newPayRate);
	}
	/**
	 * Sets the sick leave hours for a staff consultant
	 * @param c - the consultant
	 * @param newSickLeaveHours - the new sick leave hours for the consultant
	 */
	public void adjustSickLeaveHours(StaffConsultant c,int newSickLeaveHours){
		c.setSickLeave(newSickLeaveHours);
	}
	/**
	 * Sets the vacation hours for a staff consultant.
	 * @param c - the consultant
	 * @param newVacationHours - the new vacation hours for the consultant
	 */
	public void adjustVacationHours(StaffConsultant c,int newVacationHours){
		c.setVacation(newVacationHours);
	}
	/**
	 * Fires a voluntary termination event for the staff consultant.
	 * @param c - the consultant resigning
	 */
	public void acceptResignation(Consultant c){
		fireVoluntaryTerminationEvent(new TerminationEvent(this,c,true) );
}

	/**
	 *Fires an involuntary termination event for the staff consultant.
	 * @param c - the consultant being terminated
	 */

	public void terminate(Consultant c){
		fireForcedTerminationEvent(new TerminationEvent(this,c,false) );
	}
	/**
	 * Adds a termination listener.
	 * @param l - the listener to add
	 */
	public void addTerminationListener(TerminationListener l){
		mListenerList.add(TerminationListener.class,l);
	}
	/**
	 * Removes a termination listener.
	 * @param l the listener to remove
	 */
	public void removeTerminationListener(TerminationListener l){
		mListenerList.remove(TerminationListener.class,l);
	}
	protected void fireForcedTerminationEvent(final TerminationEvent evnt) {
		TerminationListener[] listeners = mListenerList.getListeners(TerminationListener.class);
		for (TerminationListener tL : listeners) {
			tL.forcedTermination(evnt);
		}
	}
	protected void fireVoluntaryTerminationEvent(final TerminationEvent evnt) {
		TerminationListener[] listeners = mListenerList.getListeners(TerminationListener.class);
		for (TerminationListener tL : listeners) {
			tL.forcedTermination(evnt);
		}
	}
}
