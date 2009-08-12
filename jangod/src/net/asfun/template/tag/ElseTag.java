package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;

public class ElseTag implements Tag{

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		//TODO 
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
		return null;
	}

	@Override
	public String getTagName() {
		return "else";
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		// TODO Auto-generated method stub
		
	}

}
