package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.util.HelperStringTokenizer;

public class CycleTag implements Tag{
	
	private String[] values;
	private String current;
	

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
		return null;
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		HelperStringTokenizer tk = new HelperStringTokenizer(helpers);
		String[] helper = tk.allTokens();
		
	}

	@Override
	public String getTagName() {
		return "cycle";
	}

}
