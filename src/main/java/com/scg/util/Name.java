package com.scg.util;

import java.io.Serializable;

/**
 * Created by chq-ruchic on 1/18/2017.
 */
public final class Name implements Serializable{
    String lastName;
    String firstName;
    String middleName;

    public Name() {

    }

    ;

    public Name(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    ;

    public Name(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = "";
    }

    ;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        String s;
        if(middleName==""){
            s="NMN";
        }
        else{
            s= middleName;
        }

        return s;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
   @Override
    public String toString() {
        String format = "%s, %s %s";
        String s=String.format(format,this.lastName,this.firstName,this.getMiddleName());
        return s;
    }
}
