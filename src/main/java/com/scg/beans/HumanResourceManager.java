package com.scg.beans;

import com.scg.domain.Consultant;

import java.beans.PropertyVetoException;

import javax.swing.event.EventListenerList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author parth
 *Responsible for modifying the pay rate and sick leave and vacation hours of staff consultants.
 */
public final class HumanResourceManager {
	/** This class' logger. */
    static final Logger log = LoggerFactory.getLogger(HumanResourceManager.class);
	/** The termination event listeners. */
	private EventListenerList mListenerList = new EventListenerList();

	public HumanResourceManager() {};
	
	/**
	 * Sets the pay rate for a staff consultant.
	 * @param c- the consultant
	 * @param newPayRate - the new pay rate for the consultant
	 */
	public void adjustPayRate(StaffConsultant c,int newPayRate){
		try{
			if(log.isInfoEnabled()){
		
				final String msg = String.format("%% change = (%d -%2$d)/%2$d)", newPayRate, c.getPayRate(),((newPayRate - c.getPayRate())/(double)c.getPayRate()));
			
			log.info(msg);
		}
		c.setPayRate(newPayRate);
		log.info("Approved pay adjustment for " + c.getName() );
		}
		catch (final PropertyVetoException pve){
			log.info(" Denied pay adjustment for " + c.getName());
		}
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
		fireTerminationEvent(new TerminationEvent(this,c,true) );
}

	/**
	 *Fires an involuntary termination event for the staff consultant.
	 * @param c - the consultant being terminated
	 */

	public void terminate(Consultant c){
		fireTerminationEvent(new TerminationEvent(this,c,false) );
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
	
	private void fireTerminationEvent(final TerminationEvent evnt) {
		TerminationListener[] listeners = mListenerList.getListeners(TerminationListener.class);
		for (final TerminationListener listener : listeners) {
			if(evnt.isVoluntary()){
				listener.voluntaryTermination(evnt);
				}else{
			listener.forcedTermination(evnt);
		}
	}
	}
}
