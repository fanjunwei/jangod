package net.asfun.template.compile;

import java.util.List;

import javax.script.ScriptContext;

import net.asfun.template.bin.FloorBindings;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.util.JangodLogger;
import net.asfun.template.util.Variable;

public class JangodCompiler {
	
	private int level = 1;
	private FloorBindings runtime;
	private ScriptContext context;
	
	public JangodCompiler(ScriptContext scontext) {
		context = scontext;
		runtime = new FloorBindings();
		initialize();
	}
	
	private JangodCompiler() {}
	
	private void initialize() {
		
	}
	
	public JangodCompiler copy() {
		JangodCompiler compiler = new JangodCompiler();
		compiler.context = context;
		compiler.runtime = runtime.copy();
		return compiler;
	}
	
	public String render(JangodParser parser) throws CompilerException {
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		StringBuffer buff = new StringBuffer();
		for(Node node : nodes) {
			buff.append(node.render(this));
		}
		if ( runtime.get("'IS\"CHILD", 1) != null ) {
			JangodLogger.finest(buff.toString());
			String semi = context.getAttribute("'SEMI\"FORMAL").toString();
			//使用 engine scope的block 内容，代替标识符
			return semi;
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
	public void assignRuntimeScope(String name, Object item) {
		runtime.put(name, item, level);
	}
	
	public void assignRuntimeScope(String name, Object item, int level) {
		runtime.put(name, item, level);
	}
	
	public Object fetchRuntimeScope(String name, int level) {
		return runtime.get(name, level);
	}
	
	public Object fetchRuntimeScope(String name) {
		return runtime.get(name, level);
	}

	public void setLevel(int lvl) {
		level = lvl;
	}

	public Object fetchGlobalScope(String name) {
		return context.getAttribute(name, ScriptContext.GLOBAL_SCOPE);
	}

	public Object fetchEngineScope(String name) {
		return context.getAttribute(name, ScriptContext.ENGINE_SCOPE);
	}
	
	public void assignEngineScope(String name, Object value) {
		context.setAttribute(name, value, ScriptContext.ENGINE_SCOPE);
	}
}
