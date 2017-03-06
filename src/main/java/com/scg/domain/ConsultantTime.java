package com.scg.domain;


import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by chq-ruchic on 1/19/2017.
 */
public final  class ConsultantTime implements Serializable{

    LocalDate date;
    Account account;
    int hours;
    Skill skillType;
    public  ConsultantTime (java.time.LocalDate date, Account account, Skill skillType, int hours)throws IllegalArgumentException {
        this.date = date;
        this.account = account;
        this.skillType = skillType;
        if (hours>0) {
            this.hours = hours;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public java.time.LocalDate getDate(){
        return date;
    }

    public void setDate(java.time.LocalDate date){
        this.date = date;

    }
    public Account getAccount(){
        return account;
    }
    public void setAccount(Account account){

        this.account = account;
    }
    public boolean isBillable(){
        if((this.getAccount().isBillable())==true)
        {return true;}
        else
        {return false;}

    }
    public int getHours(){
        return hours;
    }
    public void setHours(int hours)throws IllegalArgumentException {
        if (hours>0){
            this.hours = hours;
        }
        else{
                throw new IllegalArgumentException();
        }
    }
    public Skill getSkill(){
        return skillType;
    }


}
