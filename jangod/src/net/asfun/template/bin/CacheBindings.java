package net.asfun.template.bin;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.script.Bindings;
import javax.script.ScriptException;

public class CacheBindings implements Bindings{
	
	private Cache cache;
	
	public void config(Map<String, String> props) throws ScriptException {
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(props);
		} catch (CacheException e) {
			throw new ScriptException(e);
		}
	}

	@Override
	public boolean containsKey(Object key) {
		return cache.containsKey(key);
	}

	@Override
	public Object get(Object key) {
		return cache.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object put(String name, Object value) {
		return cache.put(name, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putAll(Map<? extends String, ? extends Object> toMerge) {
		cache.putAll(toMerge);
	}

	@Override
	public Object remove(Object key) {
		return cache.remove(key);
	}

	@Override
	public void clear() {
		cache.clear();
	}

	@Override
	public boolean containsValue(Object value) {
		return cache.containsValue(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return cache.entrySet();
	}

	@Override
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> keySet() {
		return cache.keySet();
	}

	@Override
	public int size() {
		return cache.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Object> values() {
		return cache.values();
	}

}
