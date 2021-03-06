package com.scg.util;

import java.io.Serializable;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public final class Address implements Serializable{
    String streetNumber, city,  postalCode;
    StateCode state;
    public Address(String streetNumber, String city, StateCode state, String postalCode){
        this.streetNumber = streetNumber;
        this.city = city;
        this.state= state;
        this.postalCode = postalCode;

            }

   public String  getCity(){
       return city;
   }
   public String getPostalCode(){
        return postalCode;
    }
    public  String getStreetNumber(){
        return streetNumber;
    }
    public StateCode getState(){
        return state;
    }
    public String toString() {
        String format = "%s%n%s, %s %s";
        String s=String.format(format,this.getStreetNumber(),this.getCity(),this.getState(), this.getPostalCode());
        return s;
    }
}
