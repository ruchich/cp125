package com.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public final class CompensationManager implements PropertyChangeListener, VetoableChangeListener {
    /** This class' logger. */
    static final Logger log = LoggerFactory.getLogger(CompensationManager.class);
    /**
     * Constructor
     */

    public CompensationManager(){}

    /**
     *Processes to final pay rate change.
     * @param evt-- a change event for the payRate property
     */
    public void propertyChange(PropertyChangeEvent evt){

    }

    /**
     * Rejects any raise over 5%.
     * @param evt- a vetoable change event for the payRate property
     * @throws PropertyVetoException- - if the change is vetoed
     */
    public void vetoableChange(PropertyChangeEvent evt)
            throws PropertyVetoException {
        double changePayRatePercent = (((double) evt.getNewValue() - ((double) evt.getOldValue()))) / (double) evt.getOldValue();
        if (changePayRatePercent <= 5) {
            log.info("Approved pay rate change, from " + evt.getOldValue() + " to" + evt.getNewValue()+ " for" + evt.getPropagationId());
            propertyChange(evt);
        }
        else{
            String msg = "Rejected pay rate change, from " + evt.getOldValue() + " to" + evt.getNewValue()+ " for" + evt.getPropagationId();
              throw new PropertyVetoException(msg, evt);
           // log.info(msg);

        }
    }
}
