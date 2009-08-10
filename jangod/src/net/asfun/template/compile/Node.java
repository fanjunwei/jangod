package net.asfun.template.compile;

public interface Node {

	public String compile(JangodCompiler compiler) throws CompilerException;
}
