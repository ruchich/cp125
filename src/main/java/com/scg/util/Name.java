package com.scg.util;

/**
 * Created by chq-ruchic on 1/18/2017.
 */
public final class Name {
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
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
