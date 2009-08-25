package net.asfun.template.compile;

import net.asfun.template.filter.*;

public class FilterLibrary extends SimpleLibrary<Filter>{

	private static FilterLibrary lib;
	
	static {
		lib = new FilterLibrary();
	}
	
	@Override
	protected void initialize() {
		Filter deff = new DefaultFilter();
		register(deff.getName(), deff);
		Filter datf = new DatetimeFilter();
		register(datf.getName(), datf);
		Filter absf = new AbsFilter();
		register(absf.getName(), absf);
		Filter escf = new EscapeFilter();
		register(escf.getName(), escf);
		Filter lenf = new LengthFilter();
		register(lenf.getName(), lenf);
		Filter lowf = new LowerFilter();
		register(lowf.getName(), lowf);
		Filter truf = new TruncateFilter();
		register(truf.getName(), truf);
		Filter upcf = new UpperFilter();
		register(upcf.getName(), upcf);
	}
	
	public static Filter getFilter(String filterName) throws CompilerException {
		return lib.fetch(filterName);
	}

	public static void addFilter(Filter filter) {
		lib.register(filter.getName(), filter);
	}
}
