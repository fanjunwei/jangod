package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.util.HelperStringTokenizer;

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
		StringBuffer sb = new StringBuffer();
		sb.append("<" + getTagName() + ">");
		for(Node node : carries) {
			sb.append(node.render(compiler));
		}
		sb.append("</" + getTagName() + ">");
		return sb.toString();
	}

	@Override
	public String getEndTagName() {
		return "endif";
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		//TODO 
	}

	@Override
	public String getTagName() {
		return "if";
	}

}
