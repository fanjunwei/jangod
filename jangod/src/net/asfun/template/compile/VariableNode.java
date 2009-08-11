package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.filter.FilterLibrary;
import net.asfun.template.parse.EchoToken;
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
		for(i=0; i<filters.size(); i++) {
			args = argss.get(i);
			if ( args == null ) {
				var = FilterLibrary.getFilter(filters.get(i)).filter(var);
			} else {
				for(j=0; j<args.length; j++) {
					var = FilterLibrary.getFilter(filters.get(i)).filter(var, args);
				}
			}
		}
		return ObjectValue.printable(var);
	}


}
