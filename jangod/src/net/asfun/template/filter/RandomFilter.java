package net.asfun.template.filter;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

public class RandomFilter implements Filter{

	@SuppressWarnings("unchecked")
	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( object == null ) {
			return null;
		}
		//collection
		if ( Collection.class.isAssignableFrom(object.getClass()) ) {
			Collection clt = (Collection)object;
			Iterator it = clt.iterator();
			int size = clt.size();
			if ( size == 0 ) {
				return null;
			}
			int index = Double.valueOf(Math.random() * size).intValue();
			if (index == size) index = 0;
			while( index-- > 0) {
				it.next();
			}
			return it.next();
		}
		//array
		if (object.getClass().isArray()) {
			int size = Array.getLength(object);
			if ( size == 0 ) {
				return null;
			}
			int index = Double.valueOf(Math.random() * size).intValue();
			if ( index == size ) index = 0;
			return Array.get(object, index);
		}
		//number
		if ( Number.class.isAssignableFrom(object.getClass()) ) {
			return BigDecimal.valueOf(((Number)object).doubleValue() * Math.random()).longValue();
		}
		return object;
	}

	@Override
	public String getName() {
		return "filter";
	}

}
