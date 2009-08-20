package net.asfun.template.compile;

public interface Instruction {

	public String getInstName();

	public void act(int level, String helpers, JangodCompiler compiler) throws CompilerException;
}
