package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.ConsultantTime;
import com.scg.domain.TimeCard;
import com.scg.util.ListFactory;

/**
 * The initialize/populate the database.
 * @author parth
 *
 */
public final class InitDb {
	public static void main(String[] args)
            throws Exception{
		 // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<ClientAccount>();
        final List<Consultant> consultants = new ArrayList<Consultant>();
        final List<TimeCard> timeCards = new ArrayList<TimeCard>();
        ListFactory.populateLists(accounts, consultants, timeCards);


        Connection conn = null;
        String db = "jdbc:derby://localhost:1527/memory:scgDb"; 
        String user = "student";
        String pass = "student";    

        	/* Insert client */ 
   // String query =	("INSERT INTO clients (name, street, city, state, postal_code,contact_last_name, contact_first_name, contact_middle_name)VALUES (?, ?, ?, ?, ?,?, ?, ?)");

        	/* Insert consultant */
			ps = conn.prepareStatement("INSERT INTO consultants (last_name, first_name, middle_name) VALUES (?, ?,?)");
        	for(Consultant c:consultants){
        		ps.setString(1,c.getName().getLastName());
        		ps.setString(2,c.getName().getFirstName());
        		ps.setString(3,c.getName().getMiddleName());
        		ps.executeUpdate();

        		
        	}
        	/* Insert time cards */
        	for(TimeCard t: timeCards) {
				
			/* Select consultant id */

				ps = conn.prepareStatement("SELECT id FROM consultants  WHERE last_name = ?  AND first_name = ?  AND middle_name = ?");
				ps.setString(1, t.getConsultant().getName().getLastName());
				ps.setString(2,t.getConsultant().getName().getFirstName() );
				ps.setString(3,t.getConsultant().getName().getMiddleName() );
				ps.execute();
				rs = ps.getGeneratedKeys();
				int consultant_id = rs.getInt("consultant_id");
				String startDate = t.getWeekStartingDay().toString();

			/* Insert time card */

				//INSERT INTO timecards (consultant_id, start_date)        VALUES (1, '2005/03/01');
				ps = conn.prepareStatement("INSERT INTO timecards (consultant_id, start_date) VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, consultant_id);
				ps.setString(2, startDate);
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				rs.next();  // assume we successfully inserted
				int timeCardID = rs.getInt(1);
				/* Insert non-billable hours */
				ps = conn.prepareStatement("INSERT INTO non_billable_hours (account_name, timecard_id, date, hours) VALUES (?, ?, ?, ?)");
				for (final ConsultantTime currentTime : t.getConsultingHours()) {
					if (!currentTime.isBillable()) {
						ps.setString(1, currentTime.getAccount().getName());
						ps.setInt(2, timeCardID);
						ps.setString(3, currentTime.getDate().toString());
						ps.setInt(4, currentTime.getHours());
						ps.executeUpdate();
					}
					/* Insert billable hours */
				}
			}

        	}  
        catch( SQLException ex )   {
        	System.out.println("Connection Failed");
        }
        
	}

}
