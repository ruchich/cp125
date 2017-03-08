package app;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.util.DateRange;
import com.scg.util.ListFactory;

public final class InitLists {
	private InitLists(){};
	public static void main(String[] args){
		//Create lists to be populated by factory
		final List<ClientAccount> accounts = new ArrayList<ClientAccount>();
		final List<Consultant> consultants = new ArrayList<Consultant>();
		final List<TimeCard> timeCards = new ArrayList<TimeCard>();
		ListFactory.populateLists(accounts, consultants, timeCards); 
		  try {           
	         ObjectOutputStream out =  new ObjectOutputStream( new FileOutputStream("ClientList.ser"));
	         out.writeObject(accounts);
	         out.close(); 
	         } 
		  catch(IOException ex) {
			  
			  ex.printStackTrace(); 
			  } 
		  try {           
		         ObjectOutputStream out =  new ObjectOutputStream( new FileOutputStream("TimeCardList.ser"));
		         out.writeObject(timeCards);
		         out.close(); 
		         } 
			  catch(IOException ex) {
				  ex.printStackTrace(); 
				  } 
		  
	}
}
