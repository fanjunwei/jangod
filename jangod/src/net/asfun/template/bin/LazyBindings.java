package net.asfun.template.bin;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.Bindings;
import javax.script.ScriptException;

public class LazyBindings implements Bindings{
	
	private Map<String, Object> bins;
	private Set<String> keys;
	private Map<String, String> lazies;
	
	private static final Logger logger = Logger.getLogger("asfun.jandog");
	
	public LazyBindings() {
		bins = new HashMap<String, Object>();
	}
	
	/**
	 * set up lazy load bindings
	 * @param props
	 *       like  posts => net.asfun.jvalog.domain.Post.getPosts
	 * @throws ScriptException
	 */
	public void config(Map<String, String> props) throws ScriptException {
		//TODO check the settings all right
		lazies = props;
		keys = props.keySet();
		bins.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return keys.contains(key);
	}

	@Override
	public Object get(Object key) {
		checkKey(key);
		if ( keys.contains(key) ) {
			Object value = bins.get(key);
			if ( value == null ) {
				value = getFromDataSource(key);
				bins.put(key.toString(), value);
			}
			return value;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Object getFromDataSource(Object key) {
		try {
			Class c = Class.forName("");
			Method method = c.getDeclaredMethod("", new Class[]{});
			Object value = method.invoke(null, new Object[]{});
			return value;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e.getCause());
		} 
		return null;
	}

	@Override
	public Object put(String name, Object value) {
		checkKey(name);
		keys.add(name);
		return bins.put(name, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> toMerge) {
		if (toMerge == null) {
            throw new NullPointerException("toMerge map is null");
        }
		for (Map.Entry<? extends String, ? extends Object> entry : toMerge.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public Object remove(Object key) {
		keys.remove(key);
		if ( bins.containsKey(key) ) {
			lazies.remove(key);
			return bins.remove(key);
		} else {
			Object value = getFromDataSource(key);
			lazies.remove(key);
			return value;
		}
	}

	@Override
	public void clear() {
		keys.clear();
		bins.clear();
		lazies.clear();
	}

	@Override
	public boolean containsValue(Object value) {
		return values().contains(value);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return bins.entrySet();
	}

	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return keys;
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public Collection<Object> values() {
		for(String key : lazies.keySet() ) {
			bins.put(key, getFromDataSource(key));
		}
		return bins.values();
	}
	
	private void checkKey(Object key) {
        if (key == null) {
            throw new NullPointerException("key can not be null");
        }
        if (!(key instanceof String)) {
            throw new ClassCastException("key should be a String");
        }
        if (key.equals("")) {
            throw new IllegalArgumentException("key can not be empty");
        }
    }

}
