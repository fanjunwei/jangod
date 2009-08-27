package net.asfun.template.filter;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.ObjectStringEqual;

public class ContainFilter implements Filter{

	@SuppressWarnings("unchecked")
	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( object == null ) {
			return false;
		}
		if ( arg.length != 1 ) {
			throw new CompilerException("filter contain expects 1 arg >>> " + arg.length);
		}
		Object argObj ;
		boolean isNull = false;
		if ( arg[0].startsWith("'") || arg[0].startsWith("\"") ) {
			argObj = arg[0].substring(1, arg[0].length()-1);
		} else {
			argObj = compiler.retraceVariable(arg[0]);
			if ( isNull = argObj == null ) {
				argObj = arg[0];
			}
		}
		//collection
		if ( Collection.class.isAssignableFrom(object.getClass()) ) {
			Iterator it = ((Collection)object).iterator();
			Object item;
			while(it.hasNext()) {
				item = it.next();
				if ( item == null ) {
					if ( isNull ) {
						return true;
					}
				}
				else if ( ObjectStringEqual.evaluate(item, argObj) ) {
					return true;
				}
			}
			return false;
		}
		//array
		if ( object.getClass().isArray() ) {
			int length = Array.getLength(object);
			Object item;
			for(int i=0; i<length; i++) {
				item = Array.get(object, i);
				if ( item == null ) {
					if ( isNull )
						return true;
				} else if ( ObjectStringEqual.evaluate(item, argObj) ){
					return true;
				}
			}
			return false;
		}
		//map
		if( Map.class.isAssignableFrom(object.getClass()) ) {
			Iterator it = ((Map)object).values().iterator();
			Object item;
			while(it.hasNext()) {
				item = it.next();
				if ( item == null ) {
					if ( isNull ) {
						return true;
					}
				}
				else if ( ObjectStringEqual.evaluate(item, argObj) ) {
					return true;
				}
			}
			return false;
		}
		//string
		if ( String.class.isAssignableFrom(object.getClass()) ) {
			return object.toString().contains(argObj.toString());
		}
		throw new CompilerException("filter contain can't be applied to >>> " + object.getClass().getName());
	}

	@Override
	public String getName() {
		return "contain";
	}

}
