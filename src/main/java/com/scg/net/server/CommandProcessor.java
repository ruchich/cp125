package com.scg.net.server;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.net.cmd.AddClientCommand;
import com.scg.net.cmd.AddConsultantCommand;
import com.scg.net.cmd.AddTimeCardCommand;
import com.scg.net.cmd.CreateInvoicesCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CommandProcessor {
    /** socket for the client connection*/
	private final Socket clientSocket;
	/** The Clents accounts*/
	private final List<ClientAccount>clientList;
	/** The Consultants*/
	private final List<Consultant>consultantList;
	/** TimeCards*/
	private final List<TimeCard>timeCardList = new ArrayList<TimeCard>();
	final InvoiceServer server;
	/** This class' logger. */
	private static final Logger logger = LoggerFactory. getLogger(CommandProcessor.class);
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

	/**
	 * Execute an AddTimeCard command
	 *
	 * @param command the command to execute
	 */
	
	public void execute(AddTimeCardCommand command){
		logger.info("Executing add Time Card command: " + command);
		timeCardList.add(command.getTarget());
	}


	public void execute(AddClientCommand command){
		logger.info("Executing add Client command: " + command);
		clientList.add(command.getTarget());
	}
	public void execute(AddConsultantCommand command){
		logger.info("Executing add Client command: " + command);
		consultantList.add(command.getTarget());
	}

	public void execute(CreateInvoicesCommand command) {
        logger.info("Executing invoice command: " + command);
        Invoice invoice = null;
        LocalDate date = command.getTarget();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMyyyy");
        final String monthString = formatter.format(date);
        for (final ClientAccount client : clientList) {
            Invoice invoice = new Invoice(client, date.getMonth(), date.getYear());
            for (final TimeCard currentTimeCard : timeCardList) {
                invoice.extractLineItems(currentTimeCard);
            }
            if (invoice.getTotalHours() > 0) {
                final File serverDir = new File(outputDirectoryName);
                if (!serverDir.exists()) {
                    logger.error("unable to create directory, " + serverDir.getName());
                    return;
                }
            }
            final String outFileName = String.format("%s%sInvoice.txt", client.getName().replaceAll(" ", " "), monthString);
            final File outFile = new File(outputDirectoryName, outFileName);
            try (PrintStream printOut = new PrintStream(new FileOutputStream(outFile))) {
                printOut.println(invoice.toReportString());
            } catch (final FileNotFoundException e) {
                logger.error("can't open file " + outFileName, e);
            }

        }
    }

		/** Executes a shutdown command
		 *
		 */
		public void execute(final ShutdownCommand command){
		logger.info("Executing Shut down command: " + command);
		try{
			clientSocket.close();

		}catch(final IOException e) {
			logger.warn("Shut down unable to close client comnnection");
		}finally{
			server.shutdown();
		}
	}
	}


