package com.scg.beans;

import com.scg.domain.Consultant;
import com.scg.util.Name;

import javax.swing.event.EventListenerList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.EventListener;

/**
 * Created by chq-ruchic on 3/10/2017.
 * A consultant who is kept on staff (receives benefits).
 */
public class StaffConsultant extends Consultant implements Serializable {
    
	/** Pay rate property name. */
  private static String PAY_RATE_PROPERTY_NAME =  "payrate";
    /** Sick Leave property name. */
    private static String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";
    /**Vacation hours property name.  */
    private static String VACATION_HOURS_PROPERTY_NAME = "vacationHours";

    private int payRate;
    private int sickLeave;
    private int vacation;
    private EventListenerList mListenerList = new EventListenerList();

    /**Creates a new instance of StaffConsultant.*/


    public  StaffConsultant(Name name, int rate, int sickLeave, int vacation){
    	this.name = name;
        this.payRate = rate;
        this.sickLeave = sickLeave;
        this.vacation = vacation;
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
        this.payRate = payRate;


    }

    /**
     * Adds a general property change listener.
     * @param l
     */
    public void addPropertyChangeListener(PropertyChangeListener l){
        mListenerList.add(PropertyChangeListener.class,l);
    }

    /**
     * Remove a general property change listener.
     * @param l
     */
    public void removePropertyChangeListener(PropertyChangeListener l){
        mListenerList.remove(PropertyChangeListener.class,l);
    }

    /**
     * Adds a payRate property change listener.
     * @param l
     */

    public void addPayRateListener(PropertyChangeListener l){
        mListenerList.add(PropertyChangeListener.class,l);
    }

    /**
     * Removes a payRate property change listener.
     * @param l
     */

    public void removePayRateListener(PropertyChangeListener l){
        mListenerList.remove(PropertyChangeListener.class,l);
    }

    /**
     * Adds a vetoable change listener.
     *
     * @param l
     */
    public void addVetoableChangeListener(VetoableChangeListener l){
        mListenerList.add(VetoableChangeListener.class, l);
    }

    /**
     * Removes a vetoable change listener.
     * @param l
     */

    public void removeVetoableChangeListener(VetoableChangeListener l){
        mListenerList.remove(VetoableChangeListener.class,l);
    }

    /**
     * Get the available sick leave.
     * @return the available sick leave hours
     * */
    public int getSickLeave() {
        return sickLeave;
    }
    /**
     * Set the sick leave hours. Fires the ProperrtyChange event.
     * @param  sickLeave - the available sick leave hours
     *
     * */
    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    /**
     * Adds a sickLeaveHours property change listener.
     * @param l
     */
    public void addSickLeaveHoursListener(PropertyChangeListener l){
         mListenerList.add(PropertyChangeListener.class,l);
    }

    /**
     * Removes a sickLeaveHours property change listener.
     * @param l
     */

    public void removeSickLeaveHoursListener(PropertyChangeListener l){
        mListenerList.remove(PropertyChangeListener.class,l);
    }
    /**
     * Get the available vacation hours.
     * @return the available vacation hours
     * */
    public int getVacation() {
        return vacation;
    }
    /**
     * Set the sick vacation  hours. Fires the PropertyChange event.
     * @param  vacation - the vacation hours
     *
     * */
    public void setVacation(int vacation) {
        this.vacation = vacation;
    }

    /**
     * Adds a vacationHours property change listener.
     * @param l
     */
    public void addVacationHoursListener(PropertyChangeListener l){
    mListenerList.add(PropertyChangeListener.class,l);
    }

    /**
     * Removes a vacationHours property change listener.
     * @param l
     */
    public void removeVacationHoursListener(PropertyChangeListener l){
        mListenerList.remove(PropertyChangeListener.class,l);
    }



}
