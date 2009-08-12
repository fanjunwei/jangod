package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.parse.EchoToken;
import net.asfun.template.util.JangodLogger;
import net.asfun.template.util.ObjectValue;

public class VariableNode implements Node{
	
	public VariableNode(EchoToken tk) {
		token = tk;
	}

	private EchoToken token;

	@Override
	public String render(JangodCompiler compiler) throws CompilerException {
		Object var = compiler.resolveVariable(token.getVariable());
		//filters
		List<String> filters = token.getFilters();
//		if ( filters.isEmpty() ) {
//			return ObjectValue.printable(var);
//		}
		List<String[]> argss = token.getArgss();
		int i,j;
		String[] args;
		Filter filter;
		for(i=0; i<filters.size(); i++) {
			try {
				filter = FilterLibrary.getFilter(filters.get(i));
			} catch (CompilerException ce) {
				JangodLogger.warning("Using an unregistered filter >>> " + filters.get(i));
				continue;
			}
			args = argss.get(i);
			if ( args == null ) {
				var = filter.filter(var);
			} else {
				for(j=0; j<args.length; j++) {
					var = filter.filter(var, args);
				}
			}
		}
		return ObjectValue.printable(var);
	}

}
