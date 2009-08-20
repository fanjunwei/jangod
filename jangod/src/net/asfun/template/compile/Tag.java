package net.asfun.template.compile;

import java.util.List;


public interface Tag {

	public String compile(List<Node> carries, String helpers, JangodCompiler compiler) throws CompilerException;

	/**
	 * Get name of end tag
	 * 	lowerCase
	 * 	Null if it's a single tag without content.
	 * @return
	 */
	public String getEndTagName();
	
	public String getTagName();
}
