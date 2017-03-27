package com.scg.net.client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import com.scg.domain.TimeCard;

/**
 * 
 * @author parth
 *The client of the InvoiceServer.
 */
public class InvoiceClient {

	/**
	 * Construct an InvoiceClient with a host and port for the server.
	 * @param host - the host for the server.
	 * 	@param port - the port for the server.
	 * @param timeCardList - the list of timeCards to send to the server
	 */
	InvoiceClient(String host, int port, List<TimeCard> timeCardList) {
		
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
