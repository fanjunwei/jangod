package net.asfun.template.compile;

import java.util.List;
import java.util.logging.Logger;

import javax.script.ScriptContext;

import net.asfun.template.parse.JangodParser;
import net.asfun.template.util.Variable;

public class JangodCompiler {
	
	private static final Logger logger = Logger.getLogger("asfun.jandog");
	
	private ScriptContext context;
	
	public JangodCompiler() {
		initialize();
	}
	
	private void initialize() {
		
	}
	
	public void setContext(ScriptContext scontext) {
		context = scontext;
	}
	
	public String render(JangodParser parser) throws CompilerException {
		List<Node> nodes = NodeList.makeList(parser, null);
		StringBuffer buff = new StringBuffer();
		for(Node node : nodes) {
			buff.append(node.render(this));
		}
		return buff.toString();
	}

	public Object resolveVariable(String variable) {
		Variable var = new Variable(variable);
		String varName = var.getName();
		Object obj = context.getAttribute(varName);
		if ( obj != null ) {
			obj = var.resolve(obj);
		}
		return obj;
	}

	public void setVariable(String name, Object item) {
		context.setAttribute(name, item, ScriptContext.ENGINE_SCOPE);
	}
}
