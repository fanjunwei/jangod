package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.util.ObjectTruthValue;

public class DefaultFilter implements Filter {

	@Override
	public Object filter(Object object, String... arg) throws CompilerException {
		if ( arg.length != 1) {
			throw new CompilerException("argument miss match");
		}
		return ObjectTruthValue.evaluate(object) ? object : arg;
	}

}
