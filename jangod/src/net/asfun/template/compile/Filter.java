package net.asfun.template.compile;


public interface Filter extends Importable{

	Object filter(Object object, String... arg) throws CompilerException;
}
