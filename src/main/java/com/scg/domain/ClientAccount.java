package com.scg.domain;
import com.scg.util.Name;
/**
 * Created by chq-ruchic on 1/18/2017.
 */
public final class ClientAccount implements Account {
   String name;
    Name contactName;
    public void ClientAccount(String name, Name contact){
        this. name = name;
        this.contactName = contact;
    }
    public String getName(){
        return name;
    }
    public Name getContact(){
        return contactName;
    }
    public void setContact(Name contact){
        contactName = contact;
    }
    public boolean isBillable(){
        return true;
    }


}
