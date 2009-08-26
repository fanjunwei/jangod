package net.asfun.template.compile;

import net.asfun.template.filter.*;

public class FilterLibrary extends SimpleLibrary<Filter>{

	private static FilterLibrary lib;
	
	static {
		lib = new FilterLibrary();
	}
	
	@Override
	protected void initialize() {
		//common
		Filter deff = new DefaultFilter();
		register(deff.getName(), deff);
		
		//collection
		Filter ctnf = new ContainFilter();
		register(ctnf.getName(), ctnf);
		Filter lenf = new LengthFilter();
		register(lenf.getName(), lenf);
		Filter revf = new ReverseFilter();
		register(revf.getName(), revf);
		Filter ranf = new RandomFilter();
		register(ranf.getName(), ranf);
		
		//logic
		Filter equf = new EqualFilter();
		register(equf.getName(), equf);
		Filter notf = new NotFilter();
		register(notf.getName(), notf);
		Filter andf = new AndFilter();
		register(andf.getName(), andf);
		Filter orf = new OrFilter();
		register(orf.getName(), orf);
		
		//format
		Filter datf = new DatetimeFilter();
		register(datf.getName(), datf);
		
		//number
		Filter absf = new AbsFilter();
		register(absf.getName(), absf);
		Filter diaf = new DivisibleFilter();
		register(diaf.getName(), diaf);
		Filter addf = new AddFilter();
		register(addf.getName(), addf);
		Filter mulf = new MultiplyFilter();
		register(mulf.getName(), mulf);
		Filter divf = new DivideFilter();
		register(divf.getName(), divf);
		
		//string
		Filter escf = new EscapeFilter();
		register(escf.getName(), escf);
		Filter lowf = new LowerFilter();
		register(lowf.getName(), lowf);
		Filter truf = new TruncateFilter();
		register(truf.getName(), truf);
		Filter upcf = new UpperFilter();
		register(upcf.getName(), upcf);
		Filter cutf = new CutFilter();
		register(cutf.getName(), cutf);
	}
	
	public static Filter getFilter(String filterName) throws CompilerException {
		return lib.fetch(filterName);
	}

	public static void addFilter(Filter filter) {
		lib.register(filter.getName(), filter);
	}
}
