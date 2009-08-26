package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.ObjectValue;

public class CutFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( arg.length != 1 ) {
			throw new CompilerException("filter cut expects 1 arg >>> " + arg.length);
		}
		String cutee = compiler.resolveString(arg[0]);
		String origin = ObjectValue.printable(object);
		return origin.replace(cutee, "");
	}

	@Override
	public String getName() {
		return "cut";
	}

}
