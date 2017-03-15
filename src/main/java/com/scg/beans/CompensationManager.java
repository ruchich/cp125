package com.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public final class CompensationManager implements PropertyChangeListener, VetoableChangeListener {
    /** This class' logger. */
	private static final Logger log = LoggerFactory.getLogger(CompensationManager.class);
    /**
     * Maximum allowed raise, 5%
     */
    private static final int MAX_NEW_RATE_PERCENT =105;
    /** Percent Multiplier.*/
    private static final int TO_PERCENT = 100;
    /**
     * Constructor
     */

    public CompensationManager(){}

    /**
     *Processes to final pay rate change.
     * @param evt-- a change event for the payRate property
     */
    public void propertyChange(PropertyChangeEvent evt){
    	if( StaffConsultant.PAY_RATE_PROPERTY_NAME.equals(evt.getPropertyName())){
    		final String msg = String.format("Pay Rate changed from %d to %d for %s", evt.getOldValue(),
    				evt.getNewValue(),
    				((StaffConsultant)evt.getSource()).getName());
    		log.info(msg);
    	}
    }

    /**
     * Rejects any raise over 5%.
     * @param evt- a vetoable change event for the payRate property
     * @throws PropertyVetoException- - if the change is vetoed
     */
    public void vetoableChange(PropertyChangeEvent evt)
            throws PropertyVetoException {
    	if( StaffConsultant.PAY_RATE_PROPERTY_NAME.equals(evt.getPropertyName())){
    		final int oldValue = (Integer)evt.getOldValue();
    		final int newValue = (Integer)evt.getNewValue();
    		if(newValue * TO_PERCENT > oldValue * MAX_NEW_RATE_PERCENT){
    			if(log.isInfoEnabled()){
    				final String msg =
    						String.format("Rejected Pay rate change, from %s to %s for %s",
    								evt.getOldValue(), evt.getNewValue(),
    								((StaffConsultant)evt.getSource()).getName());
    				log.info(msg);
    			}
    			throw new PropertyVetoException("Raise denied!", evt);
    		}
    			if(log.isInfoEnabled()){
    				final String msg =
    						String.format("Approved Pay rate change, from %s to %s for %s",
    								evt.getOldValue(), evt.getNewValue(),
    								((StaffConsultant)evt.getSource()).getName());
    				log.info(msg);
    			
    		}
    		}
    		
    		
    	}
        
        }
    

