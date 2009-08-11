package net.asfun.template.compile;

public interface Node {

	public String render(JangodCompiler compiler) throws CompilerException;
}
