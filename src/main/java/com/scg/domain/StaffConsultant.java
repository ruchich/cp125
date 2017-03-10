package com.scg.domain;

import com.scg.util.Name;

import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.io.Serializable;

/**
 * Created by chq-ruchic on 3/10/2017.
 * A consultant who is kept on staff (receives benefits).
 */
public class StaffConsultant extends Consultant implements Serializable {
    /** Pay rate property name. */
  private static String PAY_RATE_PROPERTY_NAME;
    /** Sick Leave property name. */
    private static String SICK_LEAVE_HOURS_PROPERTY_NAME;
    /**Vacation hours property name.  */
    private static String VACATION_HOURS_PROPERTY_NAME;

    private int payRate;
    private int sickLeave;
    private int vacation;

    /**Creates a new instance of StaffConsultant.*/
    public void StaffConsultant(Name name, int rate, int sickLeave, int vacation){
        this.name = name;
        this.payRate = rate;
        this.sickLeave = sickLeave;
        this.vacation = vacation;
    }
    /**
     * Get the current pay rate.
     * @return the pay rate in cents*/

    public int getRate() {
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
    public void addPropertyChangeListener(PropertyChangeListener l){

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
}
