package net.asfun.template.compile;

import java.util.List;

import javax.script.ScriptContext;

import net.asfun.template.bin.FloorBindings;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.util.JangodLogger;
import net.asfun.template.util.Variable;

public class JangodCompiler {
	
	private FloorBindings runtime;
	private int level = 1;
	private ScriptContext context;
	
	public JangodCompiler(ScriptContext scontext) {
		context = scontext;
		runtime = new FloorBindings();
		initialize();
	}
	
	private void initialize() {
		
	}
	
	public String render(JangodParser parser) throws CompilerException {
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		StringBuffer buff = new StringBuffer();
		for(Node node : nodes) {
			buff.append(node.render(this));
		}
		return buff.toString();
	}

	public Object resolveVariable(String variable) {
		Variable var = new Variable(variable);
		String varName = var.getName();
		//find from runtime(tree scope) > engine > global
		Object obj = runtime.get(varName, level);
		if ( obj == null ) {
			obj = context.getAttribute(varName);
		}
		if ( obj == null ) {
			obj = context.getAttribute(varName, ScriptContext.GLOBAL_SCOPE);
		}
		if ( obj != null ) {
			obj = var.resolve(obj);
		} else {
			JangodLogger.info("Can't resolve variable >>> " + varName);
		}
		return obj;
	}

	/**
	 * save variable to context engine scope
	 * @param name
	 * @param item
	 */
	public void setVariable(String name, Object item) {
		context.setAttribute(name, item, ScriptContext.ENGINE_SCOPE);
	}
	
	/**
	 * save variable to runtime tree scope space
	 * @param name
	 * @param item
	 */
	public void entrust(String name, Object item) {
		runtime.put(name, item, level);
	}

	public void setLevel(int lvl) {
		level = lvl;
	}

	public String getConfig(String name) {
		Object value = context.getAttribute(name, ScriptContext.ENGINE_SCOPE);
		if ( value == null ) {
			return "";
		}
		return value.toString();
	}
}
