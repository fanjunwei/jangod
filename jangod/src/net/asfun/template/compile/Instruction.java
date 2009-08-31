package net.asfun.template.compile;

public interface Instruction extends Importable{

	public void command(String helpers, JangodCompiler compiler) throws CompilerException;
	
	public String getEndInstName();
	
}
