package com.scg.net.cmd;

/**
 * Created by chq-ruchic on 3/28/2017.
 */
public abstract class AbstarctCommand<T>implements Command {

/** The CommandProccessor that will excecute this command*/
    private  transient CommandProcessor receiver;
    /** The target of the Command*/
    private T target;

    /**
     * Construct an AbstractCommand without a target; called from subclasses.
     */
    public AbstractCommand(){}

    /**
     * Construct an AbstractCommand with a target; called from subclasses.
     * @param target
     */
    public AbstractCommand(T target){}
}
