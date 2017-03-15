package com.scg.beans;

import com.scg.domain.Consultant;
import com.scg.util.Name;

import javax.swing.event.EventListenerList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.EventListener;

/**
 * Created by chq-ruchic on 3/10/2017.
 * A consultant who is kept on staff (receives benefits).
 */
public class StaffConsultant extends Consultant implements Serializable {
    
	/** Pay rate property name. */
    static String PAY_RATE_PROPERTY_NAME =  "payRate";
    /** Sick Leave property name. */
     static String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";
    /**Vacation hours property name.  */
     static String VACATION_HOURS_PROPERTY_NAME = "vacationHours";

    private int payRate;
    private int sickLeaveHours;
    private int vacationHours;
    private EventListenerList mListenerList = new EventListenerList();
	private PropertyChangeSupport pcs;
	private VetoableChangeSupport vcs;

    /**Creates a new instance of StaffConsultant.*/


    public  StaffConsultant(Name name, int rate, int sickLeaveHours, int vacationHours){
    	super(name);
        this.payRate = rate;
        this.sickLeaveHours = sickLeaveHours;
        this.vacationHours = vacationHours;
        pcs = new PropertyChangeSupport(this);
        vcs = new VetoableChangeSupport(this);
    }
    /**
     * Get the current pay rate.
     * @return the pay rate in cents*/

    public int getPayRate() {
        return payRate;
    }
    /**
     * Set the pay rate. Fires the VetoableChange event.
     * @param  payRate - the pay rate in cents
     *  @throws    PropertyVetoException - if a veto occurs
     * */
    public void setPayRate(int payRate)throws PropertyVetoException {
        vcs.fireVetoableChange(PAY_RATE_PROPERTY_NAME, this.payRate, payRate);
        final int oldRate = this.payRate;
        this.payRate = payRate;
        pcs.firePropertyChange(PAY_RATE_PROPERTY_NAME, oldRate, payRate);


    }

    /**
     * Adds a general property change listener.
     * @param l
     */
    public void addPropertyChangeListener(PropertyChangeListener l){
        pcs.addPropertyChangeListener(l);
        }

    /**
     * Remove a general property change listener.
     * @param l
     */
    public void removePropertyChangeListener(PropertyChangeListener l){
        pcs.removePropertyChangeListener(l);
    }

    /**
     * Adds a payRate property change listener.
     * @param l
     */

    public void addPayRateListener(PropertyChangeListener l){
        pcs.addPropertyChangeListener(l);
        }

    /**
     * Removes a payRate property change listener.
     * @param l
     */

    public void removePayRateListener(PropertyChangeListener l){
        pcs.removePropertyChangeListener(l);
    }

    /**
     * Adds a vetoable change listener.
     *
     * @param l
     */
    public void addVetoableChangeListener(VetoableChangeListener l){
        vcs.addVetoableChangeListener(l);
    }

    /**
     * Removes a vetoable change listener.
     * @param l
     */

    public void removeVetoableChangeListener(VetoableChangeListener l){
       vcs.removeVetoableChangeListener(l);
    }

    /**
     * Get the available sick leave.
     * @return the available sick leave hours
     * */
    public int getSickLeave() {
        return sickLeaveHours;
    }
    /**
     * Set the sick leave hours. Fires the ProperrtyChange event.
     * @param  sickLeaveHours - the available sick leave hours
     *
     * */
    public void setSickLeave(int sickLeaveHours) {
        final int oldHours = this.sickLeaveHours;
    	this.sickLeaveHours = sickLeaveHours;
    	pcs.firePropertyChange(SICK_LEAVE_HOURS_PROPERTY_NAME, oldHours, sickLeaveHours);
    }

    /**
     * Adds a sickLeaveHours property change listener.
     * @param l
     */
    public void addSickLeaveHoursListener(PropertyChangeListener l){
         pcs.addPropertyChangeListener(l);
    }

    /**
     * Removes a sickLeaveHours property change listener.
     * @param l
     */

    public void removeSickLeaveHoursListener(PropertyChangeListener l){
        pcs.removePropertyChangeListener(l);
    }
    /**
     * Get the available vacation hours.
     * @return the available vacation hours
     * */
    public int getVacation() {
        return vacationHours;
    }
    /**
     * Set the sick vacation  hours. Fires the PropertyChange event.
     * @param  vacationHours - the vacation hours
     *
     * */
    public void setVacation(int vacationHours) {
    	final int oldHours = this.vacationHours;
    	this.vacationHours = vacationHours;
    	pcs.firePropertyChange(VACATION_HOURS_PROPERTY_NAME, oldHours, vacationHours);
    }

    /**
     * Adds a vacationHours property change listener.
     * @param l
     */
    public void addVacationHoursListener(PropertyChangeListener l){
    pcs.addPropertyChangeListener(l);
    }

    /**
     * Removes a vacationHours property change listener.
     * @param l
     */
    public void removeVacationHoursListener(PropertyChangeListener l){
        pcs.removePropertyChangeListener(l);
    }



}
