package com.scg.domain;
import com.scg.util.Name;
/**
 * Created by chq-ruchic on 1/19/2017.
 */
public class Consultant implements Comparable<Consultant> {
        Name name;
    public Consultant(Name name){
        this.name = name;
    }
    public final Name getName(){
        return name;
    }
    
        
    @Override
    public final String toString(){
        return getName().toString();
    }
	@Override
	public int compareTo(Consultant other) {
		int result=0;
    	result=this.getName().toString().compareTo(other.getName().toString());
    	
    	return result;
	}
	
}
