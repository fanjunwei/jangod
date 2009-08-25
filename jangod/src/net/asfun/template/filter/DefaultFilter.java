package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.util.ObjectTruthValue;

public class DefaultFilter implements Filter {

	@Override
	public Object filter(Object object, String... arg) throws CompilerException {
		if ( arg.length != 1) {
			throw new CompilerException("filter default expects 1 arg >>> " + arg.length);
		}
		return ObjectTruthValue.evaluate(object) ? object : arg;
	}

	@Override
	public String getName() {
		return "default";
	}

}
