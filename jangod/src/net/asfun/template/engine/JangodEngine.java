package net.asfun.template.engine;

import java.io.Reader;
import java.util.Date;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.JangodLogger;

public class JangodEngine implements ScriptEngine {
	
	private String defaultBindings = "javax.script.SimpleBindings";
	private JangodEngineFactory factory;
	private ScriptContext context = new JangodContext();
	
	public JangodEngine() {
		factory = new JangodEngineFactory();
		context.setBindings(createBindings(), ScriptContext.GLOBAL_SCOPE);
		initGlobalData();
	}
	
	public JangodEngine(JangodEngineFactory fac) {
		factory = fac;
		context.setBindings(createBindings(), ScriptContext.GLOBAL_SCOPE);
		initGlobalData();
	}
	
	private void initGlobalData() {
		context.setAttribute("now", new Date(), ScriptContext.GLOBAL_SCOPE);
		//TODO set default var like now, version, etc.
	}

	@Override
	public Bindings createBindings() {
		try {
			return (Bindings) Class.forName(defaultBindings).newInstance();
		} catch (InstantiationException e) {
			JangodLogger.severe(e.getMessage(), e.getCause());
		} catch (IllegalAccessException e) {
			JangodLogger.severe(e.getMessage(), e.getCause());
		} catch (ClassNotFoundException e) {
			JangodLogger.severe(e.getMessage(), e.getCause());
		} catch (Exception e) {
			JangodLogger.severe(e.getMessage(), e.getCause());
		}
		return new SimpleBindings();
	}

	@Override
	public Object eval(String script, ScriptContext ctx) throws ScriptException {
		JangodParser parser = new JangodParser(script);
		JangodCompiler compiler = new JangodCompiler(ctx);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object eval(Reader reader, ScriptContext ctx) throws ScriptException {
		JangodParser parser;
		try {
			parser = new JangodParser(reader);
		} catch (ParserException e) {
			throw new ScriptException(e.getMessage());
		}
		JangodCompiler compiler = new JangodCompiler(ctx);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public ScriptEngineFactory getFactory() {
		return factory;
	}

	@Override
	public Object eval(String script) throws ScriptException {
		JangodParser parser = new JangodParser(script);
		JangodCompiler compiler = new JangodCompiler(context);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object eval(Reader reader) throws ScriptException {
		JangodParser parser;
		try {
			parser = new JangodParser(reader);
		} catch (ParserException e) {
			throw new ScriptException(e.getMessage());
		}
		JangodCompiler compiler = new JangodCompiler(context);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object eval(String script, Bindings n) throws ScriptException {
		JangodParser parser = new JangodParser(script);
		ScriptContext ctx = new JangodContext();
		ctx.setBindings(context.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
		ctx.setBindings(n, ScriptContext.ENGINE_SCOPE);
		JangodCompiler compiler = new JangodCompiler(ctx);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object eval(Reader reader, Bindings n) throws ScriptException {
		JangodParser parser;
		try {
			parser = new JangodParser(reader);
		} catch (ParserException e) {
			throw new ScriptException(e.getMessage());
		}
		
		ScriptContext ctx = new JangodContext();
		ctx.setBindings(context.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
		ctx.setBindings(n, ScriptContext.ENGINE_SCOPE);
		JangodCompiler compiler = new JangodCompiler(ctx);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object get(String key) {
		return getBindings(ScriptContext.ENGINE_SCOPE).get(key);
	}

	@Override
	public Bindings getBindings(int scope) {
		return context.getBindings(scope);
	}

	@Override
	public ScriptContext getContext() {
		return context;
	}

	@Override
	public void put(String key, Object value) {
		getBindings(ScriptContext.ENGINE_SCOPE).put(key, value);
	}

	@Override
	public void setBindings(Bindings bindings, int scope) {
		context.setBindings(bindings, scope);
	}

	@Override
	public void setContext(ScriptContext scontext) {
		context = scontext;
	}

}
