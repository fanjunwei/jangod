package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;

public class BlockTag implements Tag{

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
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
		return "endblock";
	}

	@Override
	public String getTagName() {
		return "block";
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		// TODO Auto-generated method stub
		
	}

}
