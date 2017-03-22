package co.scg.persistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;

import com.scg.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.util.Address;
import com.scg.util.Name;
import com.scg.util.StateCode;

/**
 * Responsible for providing a programmatic interface to store and access objects in the database.
 * @author parth
 *
 */
public final class DbServer {
	/* SQL for Inserting a client */
	private static final String CLIENT_INSERT_SQL = "INSERT INTO clients (name, street, city, state, postal_code, "
	                    +"contact_last_name, contact_first_name, contact_middle_name)"
	       + "VALUES (?,?,?,?,?,?,?)";
	/* SQL for selecting all clients.*/
	
	private static final String CLIENT_ALL_SELECT_SQL= "SELECT name, street, city, state, postal_code,"
									+ "contact_last_name, contact_first_name, contact_middle_name"
									+ "FROM clients";
	/* SQL for Inserting a CONSULTANT */
	private static final String CONSULTANT_INSERT_SQL = " INSERT INTO consultants (last_name, first_name, middle_name)"
													+ "VALUES (?, ?,?)";
	/* SQL for selecting Consultant Id.*/
	private static final String CONSULTANT_ID_SELECT_SQL = "SELECT id FROM consultants WHERE last_name = ?"
												+ "AND first_name = ?  AND middle_name = ?";
	/* SQL for selecting all clients.*/
	
	private static final String CONSULTANT_ALL_SELECT_SQL = "SELECT last_name, first_name, middle_name FROM consultants";
	
	/* SQL for Inserting a NON BILLABLE HOURS RECORD.*/
	
	private static final String NON_BILLABLE_HOURS_INSERT_SQL = 
			"INSERT INTO non_billable_hours (account_name, timecard_id, date, hours)"
      +" VALUES (?, ?, ?,?)";
	/* SQL for Inserting a  BILLABLE HOURS RECORD.*/
	
	private static final String BILLABLE_HOURS_INSERT_SQL = 
			"INSERT INTO billable_hours (client_id, timecard_id, date, skill,  hours)"
      +" VALUES ((SELECT DISTINCT id FROM clients WHERE name = ?),?, ?, ?,?)";

	private static final String TIMECARD_INSERT_SQL = "INSERT INTO timecards (consultant_id, start_date) VALUES (?, ?)";
	
	/* SQL for SELECTING ALL THE INVOICE ITEMS FOR A CLIENT AND MONTH.*/
	private static final String INVOICE_ITEMS_SELECT_SQL = "SELECT b.date, c.last_name, c.first_name, c.middle_name,"
		      + "b.skill, s.rate, b.hours"
		      +  "FROM billable_hours b, consultants c, skills s, timecards t"
		     +  "WHERE b.client_id = (SELECT DISTINCT id"
		      +                      " FROM clients"
		        +                    " WHERE name = ?)"
		   +     "AND b.skill = s.name"
		      + "AND b.timecard_id = t.id"
		       +" AND c.id = t.consultant_id"
		        + "AND b.date >= ?"
		        + "AND b.date <= ?";
	
/** This class's logger.*/
	private static final Logger log = LoggerFactory.getLogger(DbServer.class);

	/** the Database URL.*/
	private  final String dbUrl;
	/** username for accessing the database.*/
	private  final String username;
	/** Password for the usernamme.*/
	private  final String password;
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
	 * @param c - client - the client to add
	 * @throws SQLException - if any database operations fail
	 */
	public void addClient(ClientAccount c)
            throws SQLException{
		try(Connection conn = DriverManager.getConnection( dbUrl, username, password )){ 
		
		PreparedStatement ps = conn.prepareStatement( CLIENT_INSERT_SQL);
    	
    		ps.setString(1,c.getName());
    		ps.setString(2, c.getAddress().getStreetNumber());
    		ps.setString(3, c.getAddress().getCity());
    		ps.setString(4, c.getAddress().getState().toString());
    		ps.setString(5, c.getAddress().getPostalCode());
    		ps.setString(6, c.getContact().getLastName());
    		ps.setString(7, c.getContact().getFirstName());
    		ps.setString(8,c.getContact().getMiddleName());
    		ps.executeUpdate();
            
    	}
		
	}
	/**
	 * Get all of the clients in the database.
	 * @return a list of all of the clients
	 * @throws SQLException
	 */
	
	public List<ClientAccount> getClients()
            throws SQLException{
		try(Connection conn = DriverManager.getConnection( dbUrl, username, password )){ 
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(CLIENT_ALL_SELECT_SQL);
		List <ClientAccount> clients = new ArrayList<ClientAccount>();
		while (rs.next()){
			final String name = rs.getString(1);
			final String street = rs.getString(2);
			final String city = rs.getString(3);
			final StateCode state = StateCode.valueOf(rs.getString(4));
			final String postalCode = rs.getString(5);
			final String contactLastName = rs.getString(6);
			final String contactFirstName = rs.getString(7);
			final String contactMiddleName = rs.getString(8);
			final ClientAccount client = new ClientAccount(name,
					new Name(contactLastName, contactLastName, contactMiddleName),
					new Address(street,city,state,postalCode));
			clients.add(client);
					
		}
		return clients;
		}
		
	}
	/**
	 * Add a consultant to the database.
	 * @param  c - the consultant to add
	 * @throws SQLException- if any database operations fail
	 */
	public void addConsultant(Consultant c)
            throws SQLException{
		try(Connection conn = DriverManager.getConnection( dbUrl, username, password )){ 
			
			PreparedStatement ps = conn.prepareStatement( CONSULTANT_INSERT_SQL);
	    	 
	    		ps.setString(1,c.getName().getLastName());
	    		ps.setString(2, c.getName().getFirstName());
	    		ps.setString(3, c.getName().getMiddleName());
	    		ps.executeUpdate();
	            
	    	}
			
		
	}
	/**
	 * Get all of the consultant in the database.
	 * @return - a list of all of the consultants
	 * @throws SQLException- if any database operations fail
	 */
	public List<Consultant> getConsultants()
            throws SQLException{
		try(Connection conn = DriverManager.getConnection( dbUrl, username, password )){
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(CLIENT_ALL_SELECT_SQL);
			List <Consultant> listConsultant = new ArrayList<Consultant>();
			while (rs.next()) {
				final String lastName = rs.getString(1);
				final String firstName = rs.getString(2);
				final String middlename = rs.getString(3);
				final Consultant consultant = new Consultant(new Name(lastName, firstName, middlename));
				listConsultant.add(consultant);
			}
				return listConsultant;
			}
		
	}
	/**
	 * Add a timeCard to the database
	 * @param timeCard - - the timeCard to add
	 * @throws SQLException - if any database operations fail
	 */
	public void addTimeCard(TimeCard timeCard)
            throws SQLException{
		try(Connection conn = DriverManager.getConnection( dbUrl, username, password )){
			// get the consultant id
			PreparedStatement ps =conn.prepareStatement(CONSULTANT_ID_SELECT_SQL);
			ps.setString(1, timeCard.getConsultant().getName().getLastName());
			ps.setString(2, timeCard.getConsultant().getName().getFirstName());
			ps.setString(3, timeCard.getConsultant().getName().getMiddleName());
			ResultSet rs =	 ps.executeQuery();
			if(rs.next()){
				final int consultantId = rs.getInt(1);
				rs.close();
				ps.close();
				//Insert the timecard record
				ps = conn.prepareStatement(TIMECARD_INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1,consultantId);
				long dateMillis = timeCard.getWeekStartingDay().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
				Date date = new Date(dateMillis);
				ps.setDate(2, date);
				ps.executeUpdate();
				//get id of the generated row
				rs = ps.getGeneratedKeys();
				if(rs.next()){
					final int timeCardId = rs.getInt(1);
					final List<ConsultantTime> entries = timeCard.getConsultingHours();
					PreparedStatement billableStmnt = conn.prepareStatement(BILLABLE_HOURS_INSERT_SQL);
					PreparedStatement nonBillableStmnt  = conn.prepareStatement(NON_BILLABLE_HOURS_INSERT_SQL);
					 for( ConsultantTime entry: entries){
						 ps = (entry.isBillable())? billableStmnt : nonBillableStmnt;
						 int indx = 1;
						 ps.setString(indx++, entry.isBillable() ? entry.getAccount().getName() : ((NonBillableAccount)entry.getAccount()).name());
						 ps.setInt(indx++, timeCardId);
						 dateMillis = entry.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
						 ps.setDate(indx++, new Date(dateMillis));
						 if(entry.isBillable()){
							 ps.setString(indx++,entry.getSkill().name());
						 }
						 ps.setInt(indx++, entry.getHours());
						 ps.addBatch();
					 }
					billableStmnt.executeBatch();
					nonBillableStmnt.executeBatch();
				}else{
					log.error("Unable to locate inserted Timecard");

					}
			}else{
				log.error("Unknown Client");
			}
		}

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
		final Invoice invoice = new Invoice(client, month, year);
		try(Connection conn = DriverManager.getConnection( dbUrl, username, password )) {
			// get tinvoice line item
			PreparedStatement ps = conn.prepareStatement(INVOICE_ITEMS_SELECT_SQL);
			ps.setString(1, client.getName());
			LocalDate iStartDate = invoice.getStartDate();
			LocalDate iEndDate = iStartDate.plusMonths(1);
			long datemillis = iStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond();
			ps.setDate(2, new Date(datemillis));
			datemillis = iEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond();
			ps.setDate(3, new Date(datemillis));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				final Date date = rs.getDate(1);
				final String cLastName = rs.getString(2);
				final String cFirstName = rs.getString(3);
				final String cMiddleName = rs.getString(4);
				final String skill = rs.getString(5);
				final int hours = rs.getInt(7);
				final Consultant consultant = new Consultant(new Name(cLastName, cFirstName, cMiddleName));
				final Skill s = Skill.valueOf(skill);
				LocalDate lDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
				final InvoiceLineItem item = new InvoiceLineItem(lDate, consultant, s, hours);
				invoice.addLineItem(item);

			}
		}
		return invoice;
	}

}
