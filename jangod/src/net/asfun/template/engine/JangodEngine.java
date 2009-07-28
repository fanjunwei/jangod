package net.asfun.template.engine;

import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class JangodEngine extends AbstractScriptEngine {
	
	private static final Logger logger = Logger.getLogger("asfun.jandog");
	private String defaultBindings = "javax.script.SimpleBindings";
	private JangodEngineFactory factory;
	
	public JangodEngine() {
		factory = new JangodEngineFactory();
	}
	
	public JangodEngine(JangodEngineFactory fac) {
		factory = fac;
	}

	@Override
	public Bindings createBindings() {
		try {
			return (Bindings) Class.forName(defaultBindings).newInstance();
		} catch (InstantiationException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		} catch (IllegalAccessException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		}
		return new SimpleBindings();
	}

	@Override
	public Object eval(String arg0, ScriptContext arg1) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object eval(Reader arg0, ScriptContext arg1) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return factory;
	}

}
