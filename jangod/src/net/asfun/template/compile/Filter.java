package net.asfun.template.compile;


public interface Filter {

	Object filter(Object object, String... arg) throws CompilerException;
	
	public String getFilterName();
}
