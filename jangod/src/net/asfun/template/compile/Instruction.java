package net.asfun.template.compile;

public interface Instruction extends Importable{

	public void act(String helpers, JangodCompiler compiler) throws CompilerException;
}
