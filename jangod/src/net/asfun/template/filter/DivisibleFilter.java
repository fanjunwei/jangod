package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;

public class DivisibleFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( object == null ) {
			return false;
		}
		if ( Number.class.isAssignableFrom(object.getClass()) ) {
			if ( arg.length != 1 ) {
				throw new CompilerException("filter divisible expects 1 arg >>> " + arg.length);
			}
			long factor = Long.valueOf(compiler.resolveString(arg[0]));
			long value = ((Number)object).longValue();
			if ( value % factor == 0 ) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getName() {
		return "divisible";
	}

}
