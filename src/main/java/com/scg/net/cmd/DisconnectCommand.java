package com.scg.net.cmd;
/**
 * The command to disconnect, this command has no target.
 * @author parth
 *
 */
public class DisconnectCommand {
/**
 * Construct an DisconnectCommand.
 */
	public DisconnectCommand(){
		
	}
	
	/**
	 * Execute this Command by calling receiver.execute(this).
	 */
	public void execute(){
		 receiver.execute(this)
	}
}
