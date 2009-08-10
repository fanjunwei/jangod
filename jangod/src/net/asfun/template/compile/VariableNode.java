package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.parse.EchoToken;
import net.asfun.template.util.ObjectValue;

public class VariableNode implements Node{
	
	private EchoToken token;

	@Override
	public String compile(JangodCompiler compiler) throws CompilerException {
		Object var = compiler.resolveVariable(token.getVariable());
		//filters
		List<Object> filters = token.getFilters();
		if ( filters.isEmpty() ) {
			return ObjectValue.printable(var);
		}
		for(Object obj : filters) {
			if ( obj instanceof String ) {
				var = compiler.getFilter(obj.toString()).filter(var);
			} else {
				String[] filargs = (String[])obj;
				//TODO filargs - first item
				var = compiler.getFilter(filargs[0]).filter(var, filargs);
			}
		}
		return ObjectValue.printable(var);
	}


}
