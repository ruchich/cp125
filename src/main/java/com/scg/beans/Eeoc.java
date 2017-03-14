package com.scg.beans;

/**
 * The EEOC monitors SCG's terminations.
 */
public final class Eeoc implements TerminationListener{
    int forcedTc = 0;
    int voluntaryTc = 0;

/**
 * Constructor
 */
public Eeoc(){};

    /**
     * Simply prints a message indicating that the consultant quit.
      * @param evt
     */
    public void voluntaryTermination(TerminationEvent evt){
  System.out.println("The consultant quit");
        voluntaryTc++;
    }

    /**
     * Simply prints a message indicating that the consultant was fired.
     * @param evt
     */
    public void forcedTermination(TerminationEvent evt){
        System.out.println("The consultant fired");
        forcedTc++;
    }

    /**
     * Gets the forced termination count.
     * @return
     */
    public int forcedTerminationCount(){
   return forcedTc;
    }
    /**
     * Gets the voluntary  termination count.
     * @return
     */
    public int voluntaryTerminationCount(){
       return voluntaryTc;
    }


}
