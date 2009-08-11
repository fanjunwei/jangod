package net.asfun.template.compile;

import java.util.HashMap;
import java.util.Map;


public abstract class SimpleLibrary<T> {

	private Map<String, T> lib = new HashMap<String, T>();
	
	protected SimpleLibrary() {
		initialize();
	}
	
	protected abstract void initialize();
	
	public T fetch(String item) throws CompilerException {
		if ( lib.containsKey(item) ) {
			return lib.get(item);
		}
		throw new CompilerException("Library doesn't contain " + item);
	}
	
	public void register(String item, T obj) {
		lib.put(item, obj);
	}
	
	public boolean isRegistered(String name) {
		return lib.containsKey(name);
	}
}
