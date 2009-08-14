package net.asfun.template.compile;

import java.util.List;


public interface Tag extends Cloneable{

	public String compile(List<Node> carries, JangodCompiler compiler) throws CompilerException;

	public void initialize(String helpers) throws CompilerException;

	/**
	 * Get name of end tag
	 * 	lowerCase
	 * 	Null if it's a single tag without content.
	 * @return
	 */
	public String getEndTagName();
	
	public String getTagName();
}
