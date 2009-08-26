package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

public class LowerFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException {
		if ( object instanceof String ) {
			String value = object.toString();
			return value.toLowerCase();
		}
		return object;
	}

	@Override
	public String getName() {
		return "lower";
	}

}
