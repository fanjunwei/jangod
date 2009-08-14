package net.asfun.template.engine;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.SimpleBindings;


public class JangodContext implements ScriptContext{
	
	private Writer errw;
	private Writer wtr;
	private Reader rd;
	
	private static ThreadLocal<Bindings> engineScope = new ThreadLocal<Bindings>();
	private Bindings globalScope = new SimpleBindings();
	
	@Override
	public Object getAttribute(String name) {
		Bindings bindings = engineScope.get();
		if ( bindings != null && bindings.containsKey(name)) {
			return bindings.get(name);
		} else if ( globalScope.containsKey(name) ) {
			return globalScope.get(name);
		} else {
			return null;
		}
	}

	@Override
	public Object getAttribute(String name, int scope) {
		Bindings bn = getBindings(scope);//TODO
		return getBindings(scope).get(name);
	}

	@Override
	public int getAttributesScope(String name) {
		if ( engineScope.get().containsKey(name) ) {
			return ENGINE_SCOPE;
		}
		if ( globalScope.containsKey(name) ) {
			return GLOBAL_SCOPE;
		}
		return -1;
	}

	@Override
	public Bindings getBindings(int scope) {
		switch (scope) {
			case ENGINE_SCOPE :
				return engineScope.get();
			case GLOBAL_SCOPE :
				return globalScope;
			default :
				throw new IllegalArgumentException("Illegal scope value.");
		}
	}

	@Override
	public Writer getErrorWriter() {
		return errw;
	}

	@Override
	public Reader getReader() {
		return rd;
	}

	@Override
	public List<Integer> getScopes() {
		List<Integer> scopes = new ArrayList<Integer>();
		scopes.add(ScriptContext.ENGINE_SCOPE);
		scopes.add(ScriptContext.GLOBAL_SCOPE);
		return scopes;
	}

	@Override
	public Writer getWriter() {
		return wtr;
	}

	@Override
	public Object removeAttribute(String name, int scope) {
		Bindings bindings = getBindings(scope);
		if ( bindings != null ) {
			return bindings.remove(name);
		}
		return null;
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		getBindings(scope).put(name, value);
	}

	@Override
	public void setBindings(Bindings bindings, int scope) {
		if ( bindings == null ) {
			throw new NullPointerException("Bindings cannot be null.");
		}
		switch (scope) {
			case ENGINE_SCOPE :
				engineScope.set(bindings);
				break;
			case GLOBAL_SCOPE :
				globalScope = bindings;
				break;
			default :
				throw new IllegalArgumentException("Illegal scope value.");
		}
	}

	@Override
	public void setErrorWriter(Writer writer) {
		errw = writer;
	}

	@Override
	public void setReader(Reader reader) {
		rd = reader;
	}

	@Override
	public void setWriter(Writer writer) {
		wtr = writer;
	}

}
