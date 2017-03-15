package com.scg.beans;

import com.scg.domain.Consultant;

public class TerminationEvent extends java.util.EventObject {

    protected Consultant mConsultant;
    protected boolean mVoluntary;
    /**
     * Constructor
     * @param source
     * @param consultant
     * @param voluntary
     */
    public TerminationEvent(Object source, Consultant consultant, boolean voluntary){
        super(source);
        mConsultant = consultant;
        mVoluntary = voluntary;
    }

    /**
     *Gets the voluntary termination status.
     * @return boolean
     */
    public boolean isVoluntary(){
        
            return true;}
        

    /**
     *Gets the consultant that was terminated.
     * @return the consultant that was terminated
     */
    public Consultant getConsultant(){
            return mConsultant;
    }
}
