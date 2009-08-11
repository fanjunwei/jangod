package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;

public interface Tag {

	public String compile(List<Node> carries, JangodCompiler compiler) throws CompilerException;

	public void initialize(String helpers) throws CompilerException;

	/**
	 * Get name of end tag
	 * 	lowerCase
	 * 	Null if it's a single tag without content.
	 * @return
	 */
	public String getEndTagName();
}
