package com.scg.net.cmd;

import com.scg.net.server.CommandProcessor;

/**
 * The superclass of all Command objects, implements the command role in the Command design pattern.
 * @author parth
 *
 * @param <T>
 */

public abstract class AbstractCommand<T>implements Command<T> {
/** The CommandProcessor that will execute this command*/
	private transient CommandProcessor receiver;
	
/** The target of the command*/
	private T target;
	
/**
Construct an AbstractCommand without a target; called from subclasses.
*/
 public AbstractCommand() {}
 
/**
Construct an AbstractCommand with a target; called from subclasses. 
*/
public AbstractCommand(T target){
	
}
/**
 * specified by getReceiver in interface Command<T>
 * @return the receiver for this Command.
 */
@Override
 public final CommandProcessor getReceiver(){
	 return receiver:
 }
 /**
  * specified by setReceiver in interface Command<T>
  * @param receiver - the receiver for this Command.
  */
@Override
 public final void setReceiver(CommandProcessor receiver){
	 this.receiver = receiver;
 }
 /**
  * getTarget in interface Command<T>
  * @return the target.
  */
@Override
 public final T getTarget(){
	 return target;
 }
 /**
  * A string representation of this command. 
  * 
  */
 @Override
 public String toString(){
	 return this.getClass().getSimpleName() + ", target: "+ target;
 }

}
