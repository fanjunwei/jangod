package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.parse.EchoToken;
import static net.asfun.template.util.logger.JangodLogger;
import net.asfun.template.util.ObjectValue;

public class VariableNode implements Node{
	
	public VariableNode(EchoToken tk, int lvl) {
		token = tk;
		level = lvl;
	}

	private int level;
	private EchoToken token;

	@Override
	public String render(JangodCompiler compiler) throws CompilerException {
		compiler.setLevel(level);
		Object var = compiler.retraceVariable(token.getVariable());
		//filters
		List<String> filters = token.getFilters();
//		if ( filters.isEmpty() ) {
//			return ObjectValue.printable(var);
//		}
		List<String[]> argss = token.getArgss();
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
				var = filter.filter(var, compiler);
			} else {
				var = filter.filter(var, compiler, args);
			}
		}
		return ObjectValue.printable(var);
	}

}
