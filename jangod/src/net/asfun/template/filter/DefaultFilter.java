package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.ObjectTruthValue;

public class DefaultFilter implements Filter {

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException {
		if ( ObjectTruthValue.evaluate(object) ) {
			return object;
		} else {
			if ( arg.length != 1) {
				throw new CompilerException("filter default expects 1 arg >>> " + arg.length);
			}
			return compiler.resolveObject(arg[0]);
		}
	}

	@Override
	public String getName() {
		return "default";
	}

}
