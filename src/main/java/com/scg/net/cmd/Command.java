package com.scg.net.cmd;

import com.scg.net.server.CommandProcessor;

import java.io.Serializable;

/**
 * The superclass of all Command objects, implements the command role in the Command design pattern.
 * Created by chq-ruchic on 3/27/2017.
 */
public interface Command <T>extends Serializable{
    /**
     * Gets the CommandProcessor receiver for this Command.
     * @return - the receiver for this Command.
     */
    public CommandProcessor getReceiver();

    /**
     * Set the CommandProcessor that will execute this Command.
     * @param receiver - the receiver for this Command.
     */

    public void setReceiver(CommandProcessor receiver);

    /**
     * Return the target of this Command.
     * @return the target.
     */
    T getTarget();

    /**
     *The method called by the receiver. This method must be implemented by subclasses to send a reference to themselves to the receiver by calling receiver.execute(this).
     */

    public void execute();

}
