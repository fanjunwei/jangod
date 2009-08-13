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
		compiler.setLevel(1);
		Object isChild = compiler.fetchRuntimeScope("'IS\"CHILD", 1);
		if ( isChild != null ) {
			//替换engine scope 里面的对应的block内容
			StringBuffer sb = new StringBuffer();
			for(Node node : carries) {
				sb.append(node.render(compiler));
			}
			compiler.setVariable("唯一标识", sb.toString());
		}
		Object isParent = compiler.fetchRuntimeScope("'IS\"PARENT", 1);
		if ( isParent != null) {
			//TODO BLOCK
			//保持block内容到engine scope ，返回唯一标识为结果
			StringBuffer sb = new StringBuffer();
			for(Node node : carries) {
				sb.append(node.render(compiler));
			}
			compiler.setVariable("唯一标识", sb.toString());
		}
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
