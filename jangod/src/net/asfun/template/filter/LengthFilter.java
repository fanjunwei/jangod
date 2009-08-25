package net.asfun.template.filter;

import java.lang.reflect.Array;
import java.util.Collection;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;

public class LengthFilter implements Filter{

	@SuppressWarnings("unchecked")
	@Override
	public Object filter(Object object, String... arg) throws CompilerException {
		if ( null == object ) {
			return 0;
		}
		if (object.getClass().isArray()) {
			return Array.getLength(object);
		} 
		
		if (Collection.class.isAssignableFrom(object.getClass())) {
			return ((Collection)object).size();
		} 
		
		if (String.class.isAssignableFrom(object.getClass())) {
			return ((String)object).length();
		}
		return 0;
	}

	@Override
	public String getName() {
		return "length";
	}

}
