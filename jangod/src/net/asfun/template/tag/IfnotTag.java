package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.util.ObjectTruthValue;

public class IfnotTag implements Tag{

	@Override
	public String compile(List<Node> carries, String helpers,
			JangodCompiler compiler) throws CompilerException {
		String var = helpers;
		Object test = compiler.resolveVariable(var);
		StringBuffer sb = new StringBuffer();
		if ( ! ObjectTruthValue.evaluate(test) ) {
			for(Node node : carries) {
//				if ( "[TagNode:else]".equals(node.toString()) ) {
				if ( "else".equals(node.toString()) ) {
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
//				if ( "[TagNode:else]".equals(node.toString()) ) {
				if ( "else".equals(node.toString()) ) {
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
	public String getName() {
		return "ifnot";
	}

}
