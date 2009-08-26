package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

public class EqualFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException {
		if ( arg.length != 1 ) {
			throw new CompilerException("filter equal expects 1 arg >>> " + arg.length);
		}
		if ( object == null ) {
			return ! ( arg[0].startsWith("'") 
					|| arg[0].startsWith("\"")
					|| compiler.retraceVariable(arg[0]) != null );
		} else {
			return object.equals(compiler.resolveObject(arg[0]));
		}
	}

	@Override
	public String getName() {
		return "equal";
	}

}
