package app;

import co.scg.persistent.DbServer;
import com.scg.domain.Consultant;

import java.time.Month;

/**
 * Created by chq-ruchic on 3/22/2017.
 */
public final class Assignment07 {
    /** start month*/
    private static final Month INVOICE_MONTH = Month.MARCH;
    /** test yr*/
    private static final int INVOICE_YEAR = 2006;
    /** Database  URL*/
    private static final  String DB_URL =
    /** Database  account*/
    private static  final String DB_ACCOUNT = "student";
    /** Database  password */
    private static final String DB_PASSWORD = "student";
    /** prevent Instantiation*/
    private Assignment07(){};
     public static  void main(final String[] args)throws Exception{
         System.out.println(DB_URL);
         final DbServer db = new DbServer(DB_URL,DB_ACCOUNT,DB_PASSWORD );
         //print the consultants
         System.out.println("The consultants of SCG");
         for(final Consultant consultant : db.getConsultants()){
             System.out.println(consultant.getName());
         }
         System.out.println();
         System.out.println();
     }
}
