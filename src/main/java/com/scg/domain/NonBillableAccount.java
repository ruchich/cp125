package com.scg.domain;

/**
 * Created by chq-ruchic on 1/23/2017.
 */
public enum NonBillableAccount  implements Account {

   BUSINESS_DEVELOPMENT,
    SICK_LEAVE ,
    VACATION ;

    String accountName;
    public String getName(){
        return accountName;
    }
    public boolean isBillable(){
        return false;
    }
}
