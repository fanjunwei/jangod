package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;

public class IfchangeTag implements Tag{
	
	private String var;

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		Object test = compiler.resolveVariable(var);
		//compare with what? TODO save last value
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
		var = helpers;
	}

	@Override
	public String getTagName() {
		return "ifchange";
	}

}
