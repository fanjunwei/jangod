package net.asfun.template.filter;

import java.lang.reflect.Array;
import java.util.Collection;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

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
		//collection
		if ( Collection.class.isAssignableFrom(object.getClass()) ) {
			return ((Collection)object).contains(compiler.resolveObject(arg[0]));
		}
		//array
		if ( object.getClass().isArray() ) {
			int length = Array.getLength(object);
			Object item;
			for(int i=0; i<length; i++) {
				item = Array.get(object, i);
				if ( item == null ) {
					return ! ( arg[0].startsWith("'") 
							|| arg[0].startsWith("\"")
							|| compiler.retraceVariable(arg[0]) != null );
				} else {
					return item.equals(compiler.resolveObject(arg[0]));
				}
			}
		}
		//string
		if ( String.class.isAssignableFrom(object.getClass()) ) {
			return object.toString().contains(compiler.resolveString(arg[0]));
		}
		return false;
	}

	@Override
	public String getName() {
		return "contain";
	}

}
