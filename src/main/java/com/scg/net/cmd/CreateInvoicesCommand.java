package com.scg.net.cmd;

import java.time.LocalDate;

import com.scg.domain.Consultant;
/**
 * The command to create invoices for a specified month.
 * @author parth
 *
 */
public class CreateInvoicesCommand {
	/**
	 * Construct an CreateInvoicesCommand with a target.
	 * @param target - The target of this Command.
	 */
	
	public CreateInvoicesCommand(LocalDate  target){
		super();
	}
	/**
	 * Execute this Command by calling receiver.execute(this).
	 */
	
	public void execute(){
		receiver.execute(this);
	}
}
