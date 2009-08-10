package net.asfun.template.util;

public class ObjectValue {

	public static String printable(Object variable) {
		if ( variable == null ) {
			return "";
		}
		//TODO if String , Integer, Float, boolean....
		return variable.toString();
	}
}
