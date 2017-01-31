package com.scg.domain;

/**
 * Created by chq-ruchic on 1/23/2017.
 */
public enum NonBillableAccount  implements Account {

   BUSINESS_DEVELOPMENT,
    SICK_LEAVE ,
    VACATION ;


    public String getName(){
        String s = "";
        for (NonBillableAccount d: NonBillableAccount.values()) {
            if(d.equals(BUSINESS_DEVELOPMENT)){
                s= "Business Development";
            }
            if (d.equals(SICK_LEAVE)){
                s= "Sick Leave";
            }
           if (d.equals(VACATION)){
                s= "Vacation";
            }
        }
        return s;
    }
    public boolean isBillable(){
        return false;
    }
}
