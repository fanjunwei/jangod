package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.ObjectTruthValue;

public class OrFilter implements Filter{

	@Override
	public Object filter(Object object, JangodCompiler compiler, String... arg)
			throws CompilerException {
		if ( ObjectTruthValue.evaluate(object) ) {
			return true;
		} else {
			Object test;
			for(String var : arg) {
				test = compiler.retraceVariable(var);
				if ( ObjectTruthValue.evaluate(test) ) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public String getName() {
		return "or";
	}

}
