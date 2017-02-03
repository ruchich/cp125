package com.scg.util;

/**
 * Created by chq-ruchic on 2/3/2017.
 */
public final class Address {
    String streetNumber, city,  postalCode;
    StateCode state;
    Address(String streetNumber, String city, StateCode state, String postalCode){
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
}
