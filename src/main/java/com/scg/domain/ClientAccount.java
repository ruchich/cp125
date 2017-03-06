package com.scg.domain;
import java.io.Serializable;
import java.util.function.Function;

import com.scg.util.Address;
import com.scg.util.Name;
/**
 * Created by chq-ruchic on 1/18/2017.
 */
public final class ClientAccount implements Account, Comparable<ClientAccount>,Serializable  {
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

    @Override
    public String toString() {
        String format = "%s%n%s%n%s";
                String s=String.format(format,this.getName(),this.getAddress(),this.getContact());
        return s;
    }
	@Override
	public int compareTo(ClientAccount o) {
		int result = 0;
		if (this!=o){
		result = this.getName().compareTo(o.getName());
				if(result==0){
			result= this.getContact().toString().compareTo(o.getContact().toString());
		}
				if(result==0){
					result= this.getAddress().toString().compareTo(o.getAddress().toString());
				}
				
		
		}
		return result;
}
}
