package co.scg.persistent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;

/**
 * Responsible for providing a programmatic interface to store and access objects in the database.
 * @author parth
 *
 */
public final class DbServer {
	String dbUrl;
	String username;
	String password;
	/**
	 * Constructor
	 * @param dbUrl - - the database URL
	 * @param username - the database username
	 * @param password -the database password
	 */
	public DbServer(String dbUrl, String username, String password) {
		this.dbUrl = dbUrl;
		this.username = username;
		this.password = password;
		
	}
	/**
	 * Add a client to the database.
	 * @param client - client - the client to add
	 * @throws SQLException - if any database operations fail
	 */
	public void addClient(ClientAccount client)
            throws SQLException{
		
	}
	/**
	 * Get all of the clients in the database.
	 * @return a list of all of the clients
	 * @throws SQLException
	 */
	
	public List<ClientAccount> getClients()
            throws SQLException{
		List <ClientAccount> listClentAccount = new ArrayList<ClientAccount>();
		return listClentAccount;
		
	}
	/**
	 * Add a consultant to the database.
	 * @param consultant - - the consultant to add
	 * @throws SQLException- if any database operations fail
	 */
	public void addConsultant(Consultant consultant)
            throws SQLException{
		
	}
	/**
	 * Get all of the consultant in the database.
	 * @return - a list of all of the consultants
	 * @throws SQLException- if any database operations fail
	 */
	public List<Consultant> getConsultants()
            throws SQLException{
		
		List <Consultant> listConsultant = new ArrayList<Consultant>();
		return listConsultant;
		
	}
	/**
	 * Add a timeCard to the database
	 * @param timeCard - - the timeCard to add
	 * @throws SQLException - if any database operations fail
	 */
	public void addTimeCard(TimeCard timeCard)
            throws SQLException{
		
	}
	/**
	 * Get clients monthly invoice.
	 * @param client - the client to obtain the invoice line items for
	 * @param month - the month of the invoice
	 * @param year - the year of the invoice
	 * @return the clients invoice for the month
	 * @throws SQLException - if any database operations fail
	 */
	public Invoice getInvoice(ClientAccount client,
            java.time.Month month,
            int year)
     throws SQLException{
		Invoice invoice = new Invoice(client, month, year);
		
		return invoice;
	}

}
