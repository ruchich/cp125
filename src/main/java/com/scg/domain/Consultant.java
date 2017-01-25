package com.scg.domain;
import com.scg.util.Name;
/**
 * Created by chq-ruchic on 1/19/2017.
 */
public class Consultant {
        Name name;
    public Consultant(Name name){
        this.name = name;
    }
    public final Name getName(){
        return name;
    }
    public final String toString(){
        return getName().toString();
    }

}
