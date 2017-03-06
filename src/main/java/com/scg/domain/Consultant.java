package com.scg.domain;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

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
        return new SerializationProxy(this);
    }
    private void readObject(ObjectInputStream ois)
            throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
    private static class SerializationProxy implements Serializable {
        private Name proxyName;
        SerializationProxy(final Consultant consultant) {
            proxyName = consultant.getName();
        }
        private Object readResolve() { return new Consultant(proxyName); }
    }
}

