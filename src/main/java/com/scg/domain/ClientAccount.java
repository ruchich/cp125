package com.scg.domain;
import com.scg.util.Address;
import com.scg.util.Name;
/**
 * Created by chq-ruchic on 1/18/2017.
 */
public final class ClientAccount implements Account {
   String name;
    Name contactName;
    Address address;
    public  ClientAccount(String name, Name contact,  Address address){
        this. name = name;
        this.contactName =contact;
        this.address = address;
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
    public Address getAddress(){
        return address;
    }
    public void setAddress(Address address){
        this.address = address;
    }
}
