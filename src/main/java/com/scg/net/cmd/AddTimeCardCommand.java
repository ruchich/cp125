package com.scg.net.cmd;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * Command that adds a TimeCard to the server's list of TimeCards.
 * @author parth
 *
 */
public class AddTimeCardCommand extends AbstractCommand<TimeCard>{
	/**
	 * Construct an AddConsultantCommand with a target.
	 * @param target - The target of this Command.
	 */
	
	public AddTimeCardCommand(TimeCard target){
		super();
	}
	/**
	 * Execute this Command by calling receiver.execute(this).
	 */
	
	public void execute(){
		receiver.execute(this);
	}

	
}
