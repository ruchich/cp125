package com.scg.net.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.TimeCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author parth
 *The client of the InvoiceServer.
 */
public class InvoiceClient {
	/** start month*/
	private static final Month INVOICE_MONTH = Month.MARCH;
	/** test yr*/
	private static final int INVOICE_YEAR = 2006;
	/** This class' logger. */
	private static final Logger log = LoggerFactory.getLogger("InvoiceClient");
	/** List of TimeCards to send to the server*/
	 List<TimeCard> timeCardList = new ArrayList<TimeCard>();
	/** the host for the server.*/
	String host;
	/** the port for the server.*/
	int port;
	/** The socket. */
	private Socket socket;

	/**
	 * Construct an InvoiceClient with a host and port for the server.
	 * @param host - the host for the server.
	 * 	@param port - the port for the server.
	 * @param timeCardList - the list of timeCards to send to the server
	 */
	public InvoiceClient(String host, int port, List<TimeCard> timeCardList)throws IOException {
		this.host = host;
		this.port = port;
		this.timeCardList = timeCardList;
		socket = new Socket(host, port);
	}
	/**
	 * Runs this InvoiceClient, sending clients, consultants, and time cards to the server, then sending the command to create invoices for a specified month.
	 */
	public void run(){
		
	}
	/**
	 * Send the clients to the server.
	 * @param out - the output stream connecting this client to the server.
	 */
	public void sendClients(ObjectOutputStream out){

	}
	/**
	 * Send the consultants to the server.
	 * @param out - the output stream connecting this client to the server.
	 */
	public void sendConsultants(ObjectOutputStream out){
		
	}
	/**
	 * Send the time cards to the server.
	 * @param out - the output stream connecting this client to the server.
	 */
	
	public void sendTimeCards(ObjectOutputStream out){
		
	}
	/**
	 * 
	 * @param out
	 * @param server - the connection to be closed after sending disconnect
	 * 
	 */
	public void sendDisconnect(ObjectOutputStream out,
            Socket server){
		
	}
	/**
	 * Send the command to the server to create invoices for the specified month.
	 * @param out
	 * @param - the month to create invoice for
	 * @param year - the year to create invoice for
	 */
	public void createInvoices(ObjectOutputStream out,
            java.time.Month month,
            int year){
		
	}
	/**
	 * Send the quit command to the server.
	 * @param host - the host for the server.
	 * @param port - the port for the server.
	 */
	public static void sendShutdown(String host,
            int port){
		
	}
	

}
