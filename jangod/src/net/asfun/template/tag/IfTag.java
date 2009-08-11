package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.util.QuotedStringTokenizer;

/**
 * {% if a %}
 * {% if a and b and c %}
 * {% if c or d or a %}
 * {% if not a and b and not c and d %}
 * @author fangchq
 *
 */
public class IfTag implements Tag {

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndTagName() {
		return "endif";
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		String[] helper = new QuotedStringTokenizer(helpers).allTokens();
		//TODO 
	}

}
