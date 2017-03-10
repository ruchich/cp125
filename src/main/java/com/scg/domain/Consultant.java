package com.scg.domain;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.util.Name;
/**
 * Created by chq-ruchic on 1/19/2017.
 */
public class Consultant implements Comparable<Consultant>,Serializable {
        Name name;
    public Consultant(Name name){
        this.name = name;
    }
    public final Name getName(){
        return name;
    }
    private static final Logger log = LoggerFactory.getLogger(Consultant.class);

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
    private Object writeReplace() {
    	final String	msg = String.format("Serializing consultant:  %s",  getName());
    	log.info(msg);
        return new SerializationProxy(this);
    }
    private void readObject(ObjectInputStream ois)
            throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
    private static final class SerializationProxy implements Serializable {
     private Name proxyName;
     private final String x;
     private final  String y;
     private final  String z;
        SerializationProxy(final Consultant consultant) {
            proxyName = consultant.getName();
            x = proxyName.getFirstName();
            y = proxyName.getLastName();
            z= proxyName.getMiddleName();
        }
        private Object readResolve() { 
        final String	msg = String.format("De-seralized consultant: %s,%s,%s",  x,y,z);
        	log.info(msg);
        	return new Consultant(new Name(x,y,z)); }
    }
}

