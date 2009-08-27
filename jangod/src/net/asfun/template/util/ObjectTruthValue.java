package net.asfun.template.util;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Collection;

public class ObjectTruthValue {
	
	@SuppressWarnings("unchecked")
	public static boolean evaluate(Object object) {
		if (object == null) { 
			return false;
		}
		
		if (Boolean.class.isAssignableFrom(object.getClass())) {
			Boolean b = (Boolean)object;
			return b.booleanValue();
		}
		
		if (Integer.class.isAssignableFrom(object.getClass())) {
			return (Integer)object != 0;
		}
		
		if (String.class.isAssignableFrom(object.getClass())) {
			return !object.toString().equals("");
		}
		
		if (Long.class.isAssignableFrom(object.getClass())) {
			return (Long)object != 0l;
		}
		
		if ( object.getClass().isArray() ) {
			return Array.getLength(object) != 0;
		}
		
		if (Collection.class.isAssignableFrom(object.getClass()) ) {
			return ((Collection)object).size() != 0;
		}
		
		if (BigInteger.class.isAssignableFrom(object.getClass())) {
			return ((BigInteger)object).longValue() != 0l;
		}
		
		//TODO float
		
		return true;
	}
	
}
