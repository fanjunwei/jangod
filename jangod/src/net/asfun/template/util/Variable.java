package net.asfun.template.util;

import java.util.Arrays;
import java.util.List;

public class Variable {

	private String name;
	private VariableChain chain;
	
	public Variable(String variable) {
		split(variable);
	}

	private void split(String variable) {
		if (!variable.contains(".")) {
			name = variable;
			chain = null;
			return;
		}
		
		String[] parts = variable.split("\\.");
		
		List<String> chainList = Arrays.asList(parts);
		chainList = chainList.subList(1, chainList.size());
		
		name = parts[0];
		chain = new VariableChain(chainList);
	}

	public String getName() {
		return name;
	}

	public Object resolve(Object value) {
		return chain == null ? value : chain.resolve(value);
	}
	
	@Override
	public String toString() {
		return "<Variable: " + name + ">";
	}
}
