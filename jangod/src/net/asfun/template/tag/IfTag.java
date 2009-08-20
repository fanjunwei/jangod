package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
//import net.asfun.template.util.HelperStringTokenizer;
import net.asfun.template.util.ObjectTruthValue;

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
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler)
			throws CompilerException {
		//TODO test more helpers
		String var = helpers;
		Object test = compiler.resolveVariable(var);
		StringBuffer sb = new StringBuffer();
		if ( ObjectTruthValue.evaluate(test) ) {
			for(Node node : carries) {
				if ( "[TagNode:else]".equals(node.toString()) ) {
					break;
				}
				sb.append(node.render(compiler));
			}
		} else {
			boolean inElse = false;
			for(Node node : carries) {
				if (inElse) {
					sb.append(node.render(compiler));
				}
				if ( "[TagNode:else]".equals(node.toString()) ) {
					inElse = true;
				}
			}
		}
		return sb.toString();
	}

	@Override
	public String getEndTagName() {
		return "endif";
	}

	@Override
	public String getTagName() {
		return "if";
	}

}
