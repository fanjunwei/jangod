package net.asfun.template.filter;

import javax.script.ScriptContext;
import javax.script.SimpleBindings;

import org.junit.BeforeClass;

import net.asfun.template.compile.Filter;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.engine.JangodContext;

public class ZzzBase {

	protected static JangodCompiler compiler;
	protected Filter filter;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		SimpleBindings bindings = new SimpleBindings();
		ScriptContext context = new JangodContext(bindings);
		compiler = new JangodCompiler(context);
	}
}
