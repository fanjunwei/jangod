package net.asfun.template.engine;

import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

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

public class JangodEngine implements ScriptEngine {
	
	private static final Logger logger = Logger.getLogger("asfun.jandog");
	private String defaultBindings = "javax.script.SimpleBindings";
	private JangodEngineFactory factory;
	private ScriptContext context = new JangodContext();
	JangodCompiler compiler = new JangodCompiler();
	
	public JangodEngine() {
		factory = new JangodEngineFactory();
		//TODO use CacheBindings for global scope
		//TODO set default var like now, version, etc.
		context.setBindings(createBindings(), ScriptContext.GLOBAL_SCOPE);
	}
	
	public JangodEngine(JangodEngineFactory fac) {
		factory = fac;
		//TODO use CacheBindings for global scope
		//TODO set default var like now, version, etc.
		context.setBindings(createBindings(), ScriptContext.GLOBAL_SCOPE);
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
	public Object eval(String script, ScriptContext ctx) throws ScriptException {
		JangodParser parser = new JangodParser(script);
		JangodCompiler tempCompiler = new JangodCompiler();
		tempCompiler.setContext(ctx);
		try {
			return tempCompiler.render(parser);
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
		JangodCompiler tempCompiler = new JangodCompiler();
		tempCompiler.setContext(ctx);
		try {
			return tempCompiler.render(parser);
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
		compiler.setContext(context);
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
		compiler.setContext(context);
		try {
			return compiler.render(parser);
		} catch (CompilerException e) {
			throw new ScriptException(e.getMessage());
		}
	}

	@Override
	public Object eval(String script, Bindings n) throws ScriptException {
		JangodParser parser = new JangodParser(script);
		JangodCompiler tempCompiler = new JangodCompiler();
		ScriptContext ctx = new JangodContext();
		ctx.setBindings(context.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
		ctx.setBindings(n, ScriptContext.ENGINE_SCOPE);
		tempCompiler.setContext(ctx);
		try {
			return tempCompiler.render(parser);
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
		JangodCompiler tempCompiler = new JangodCompiler();
		ScriptContext ctx = new JangodContext();
		ctx.setBindings(context.getBindings(ScriptContext.GLOBAL_SCOPE), ScriptContext.GLOBAL_SCOPE);
		ctx.setBindings(n, ScriptContext.ENGINE_SCOPE);
		tempCompiler.setContext(ctx);
		try {
			return tempCompiler.render(parser);
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
