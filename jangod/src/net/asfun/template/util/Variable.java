package net.asfun.template.util;

import java.util.Arrays;
import java.util.List;

public class Variable {

	private String name;
	private List<String> chainList;
	
	public Variable(String variable) {
		split(variable);
	}

	private void split(String variable) {
		if (!variable.contains(".")) {
			name = variable;
			chainList = null;
			return;
		}
		
		String[] parts = variable.split("\\.");
		name = parts[0];
		chainList = Arrays.asList(parts);
		chainList = chainList.subList(1, chainList.size());
		
	}

	public String getName() {
		return name;
	}

	public Object resolve(Object value) {
		if ( chainList != null ) {
			return new VariableChain(chainList, value).resolve();
		} else {
			return value;
		}
	}
	
	@Override
	public String toString() {
		return "<Variable: " + name + ">";
	}
}
