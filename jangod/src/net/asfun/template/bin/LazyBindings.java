package net.asfun.template.bin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.script.Bindings;
import javax.script.ScriptException;

public class LazyBindings implements Bindings{
	
	private Map<String, Object> bins;
	
	public LazyBindings() {
		bins = new HashMap<String, Object>();
	}
	
	public void config(Map<String, String> props) throws ScriptException {
		//TODO set agent
	}

	@Override
	public boolean containsKey(Object key) {
		return true;
	}

	@Override
	public Object get(Object key) {
		Object value = bins.get(key);
		if ( value == null ) {
			value = getFromDataSource(key);
			if ( value != null ) {
				bins.put(key.toString(), value);
			}
		}
		return value;
	}

	private Object getFromDataSource(Object key) {
		// TODO Auto-generated method stub
		// 设置代理类，和key对应的代理方法，调用代理获取数据
		return null;
	}

	@Override
	public Object put(String name, Object value) {
		return bins.put(name, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> toMerge) {
		bins.putAll(toMerge);
	}

	@Override
	public Object remove(Object key) {
		return bins.remove(key);
	}

	@Override
	public void clear() {
		bins.clear();
	}

	@Override
	public boolean containsValue(Object value) {
		return bins.containsValue(value);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return bins.entrySet();
	}

	@Override
	public boolean isEmpty() {
		return bins.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return bins.keySet();
	}

	@Override
	public int size() {
		return bins.size();
	}

	@Override
	public Collection<Object> values() {
		return bins.values();
	}

}
