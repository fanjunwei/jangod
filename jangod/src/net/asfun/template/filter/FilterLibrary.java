package net.asfun.template.filter;

import net.asfun.template.compile.SimpleLibrary;

public class FilterLibrary extends SimpleLibrary<Filter>{

	@Override
	protected void initialize() {
		register("default", new DefaultFilter());
	}

}
