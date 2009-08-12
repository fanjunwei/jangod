package net.asfun.template.compile;

import net.asfun.template.filter.*;

public class FilterLibrary extends SimpleLibrary<Filter>{

	private static FilterLibrary lib;
	
	static {
		lib = new FilterLibrary();
	}
	
	@Override
	protected void initialize() {
		Filter defaultFilter = new DefaultFilter();
		register(defaultFilter.getFilterName(), defaultFilter);
	}
	
	public static Filter getFilter(String filterName) throws CompilerException {
		return lib.fetch(filterName);
	}

}
