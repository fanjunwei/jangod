package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.SimpleLibrary;

public class FilterLibrary extends SimpleLibrary<Filter>{

	private static FilterLibrary lib;
	
	static {
		lib = new FilterLibrary();
	}
	
	@Override
	protected void initialize() {
		register("default", new DefaultFilter());
	}
	
	public static Filter getFilter(String filterName) throws CompilerException {
		return lib.fetch(filterName);
	}

}
