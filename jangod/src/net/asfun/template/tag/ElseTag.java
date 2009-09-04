package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;

/**
 * {% else %}
 * @author anysome
 *
 */
public class ElseTag implements Tag{

	@Override
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler)
			throws CompilerException {
		return "";
	}

	@Override
	public String getEndTagName() {
		return null;
	}

	@Override
	public String getName() {
		return "else";
	}

}
