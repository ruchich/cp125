package com.scg.net.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.net.cmd.AddTimeCardCommand;

public final class CommandProcessor {
	private final Socket clientSocket;
	
	private final List<ClientAccount>clientList;
	
	private final List<Consultant>consultantList;
	
	private final List<TimeCard>timeCardList = new ArrayList<TimeCard>();
	final InvoiceServer server;
	
	/** The name of the directory to be used for files output*/
	private String outputDirectoryName  = ".";
	
	public CommandProcessor ( final Socket connection,
			final List<ClientAccount>clientList,
			final List<Consultant>consultantList,
			final InvoiceServer server){
		this.clientSocket = connection;
		this.clientList = clientList;
		this.consultantList = consultantList;
		this.server = server;
	}
	
	public void execute(AddTimeCardCommand command){
		logger.info("Executing add Time Card command: " + command);
		timeCardList.add(command.getTarget());
	}

}
