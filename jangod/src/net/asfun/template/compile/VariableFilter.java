package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.parse.FilterParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.JangodLogger;

public class VariableFilter {
	
	public static Object compute(String varString, JangodCompiler compiler) throws CompilerException {
		FilterParser fp = new FilterParser(varString);
		try {
			fp.parse();
		} catch (ParserException e) {
			throw new CompilerException(e.getMessage());
		}
		Object var = compiler.resolveVariable(fp.getVariable());
		List<String> filters = fp.getFilters();
//		if ( filters.isEmpty() ) {
//			return var;
//		}
		List<String[]> argss = fp.getArgss();
		String[] args;
		Filter filter;
		for(int i=0; i<filters.size(); i++) {
			try {
				filter = FilterLibrary.getFilter(filters.get(i));
			} catch (CompilerException ce) {
				JangodLogger.warning("Skipping an unregistered filter >>> " + filters.get(i));
				continue;
			}
			args = argss.get(i);
			if ( args == null ) {
				var = filter.filter(var);
			} else {
				var = filter.filter(var, args);
			}
		}
		return var;
	}
}
