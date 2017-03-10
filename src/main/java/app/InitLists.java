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
	// constructor for InitLists class
	private InitLists(){};
	//Create lists to be populated by factory
	private static final List<ClientAccount> accounts = new ArrayList<ClientAccount>();
	private static final List<Consultant> consultants = new ArrayList<Consultant>();
	private static final List<TimeCard> timeCards = new ArrayList<TimeCard>();

	public static void main(String[] args){
         //populate Client, consultant and Timecard lists
		ListFactory.populateLists(accounts, consultants, timeCards);
        // Serialize Client and Timecard lists
        serializeLists();

		  	}

    /**
     * Serialize Client and timecard list to ClientList.ser and TimeCardList.ser respectively
     */
    private static void serializeLists(){
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
