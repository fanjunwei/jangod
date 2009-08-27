package net.asfun.template.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ObjectStringEqual {

	public static boolean evaluate(Object object, Object strObj) {
		if ( object == null ) {
			return strObj == null;
		} else {
			if ( strObj == null ) return false;
			if (String.class.isAssignableFrom(strObj.getClass())) {
				String str = (String)strObj;
				if (String.class.isAssignableFrom(object.getClass())) {
					return str.equals(object);
				}
				
				if (Integer.class.isAssignableFrom(object.getClass())) {
					try {
						return Integer.valueOf(str).equals(object);
					} catch (Exception e) {
						return false;
					}
				}
				
				if (Long.class.isAssignableFrom(object.getClass())) {
					try {
						return Long.valueOf(str).equals(object);
					} catch (Exception e) {
						return false;
					}
				}
				
				if (Boolean.class.isAssignableFrom(object.getClass())) {
					if ( (Boolean)object ) {
						return str.equalsIgnoreCase("True");
					} else {
						return str.equalsIgnoreCase("False");
					}
				}
				
				if (Float.class.isAssignableFrom(object.getClass())) {
					try {
						return Float.valueOf(str).equals(object);
					} catch (Exception e) {
						return false;
					}
				}
				
				if (Short.class.isAssignableFrom(object.getClass())) {
					try {
						return Short.valueOf(str).equals(object);
					} catch (Exception e) {
						return false;
					}
				}
				
				if (Double.class.isAssignableFrom(object.getClass())) {
					try {
						return Double.valueOf(str).equals(object);
					} catch (Exception e) {
						return false;
					}
				}
				
				if (Byte.class.isAssignableFrom(object.getClass())) {
					try {
						return Byte.valueOf(str).equals(object);
					} catch (Exception e) {
						return false;
					}
				}
				
				if (BigInteger.class.isAssignableFrom(object.getClass())) {
					try {
						return Long.valueOf(str).longValue() == ((BigInteger)object).longValue();
					} catch (Exception e) {
						return false;
					}
				}
				
				if (BigDecimal.class.isAssignableFrom(object.getClass())) {
					try {
						return Double.valueOf(str).doubleValue() == ((BigDecimal)object).doubleValue();
					} catch (Exception e) {
						return false;
					}
				}
				
				//TODO suppost more type
				
				return str.equals(object);
			} else {
				return object.equals(strObj);
			}
		}
	}
}
