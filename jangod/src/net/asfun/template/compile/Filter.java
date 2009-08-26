package net.asfun.template.compile;


public interface Filter extends Importable{

	Object filter(Object object, JangodCompiler compiler, String... arg) throws CompilerException;
}
