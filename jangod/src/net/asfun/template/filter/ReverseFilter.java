package net.asfun.template.filter;

import java.lang.reflect.Array;
import java.util.Collection;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

public class ReverseFilter implements Filter{

	@SuppressWarnings("unchecked")
	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( object == null ) {
			return null;
		}
		//collection
		if ( Collection.class.isAssignableFrom(object.getClass()) ) {
			Object[] origin = ((Collection)object).toArray();
			int length = origin.length;
			Object[] res = new Object[length];
			length--;
			for(int i=0; i<length; i++) {
				res[i] = origin[length-i];
			}
			return res;
		}
		//array
		if ( object.getClass().isArray() ) {
			int length = Array.getLength(object);
			Object[] res = new Object[length];
			length--;
			for(int i=0; i<length; i++) {
				res[i] = Array.get(object, length-i);
			}
			return res;
		}
		//string
		if ( String.class.isAssignableFrom(object.getClass()) ) {
			String origin = (String)object;
			int length = origin.length();
			char[] res = new char[length];
			length--;
			for(int i=0; i<length; i++) {
				res[i] = origin.charAt(length-i);
			}
			return String.valueOf(res);
		}
		return object;
	}

	@Override
	public String getName() {
		return "reverse";
	}

}
