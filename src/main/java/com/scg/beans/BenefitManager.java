package com.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public final class BenefitManager implements PropertyChangeListener {

    /** This class' logger. */
     static final Logger log = LoggerFactory.getLogger(BenefitManager.class);
    /**
     * Default Constructor
     */
    public BenefitManager(){}

    /**
     *Logs the change.
     * @param evt - a property change event for the sickLeaveHours or vacationHours, or payRate property
     */
    public void propertyChange(PropertyChangeEvent evt){
        log.info(evt.getPropertyName()+ "changed from "+ evt.getOldValue()+ " to " + evt.getNewValue() + " for " + ((StaffConsultant)evt.getSource()).getName());
    }
}
