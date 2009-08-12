package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.util.HelperStringTokenizer;

public class ExtendsTag implements Tag{
	
	private String templateFile;

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		// TODO Auto-generated method stub
		String templateRoot = compiler.getConfig("TPL_ROOT_DIR");
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
		return "extends";
	}

	@Override
	public void initialize(String helpers) throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if( helper.length != 1) {
			throw new CompilerException("extends tag expects one helper:" + helper.length);
		}
		templateFile = helper[0];
	}

}
