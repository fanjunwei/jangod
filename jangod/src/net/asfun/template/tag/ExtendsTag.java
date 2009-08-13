package net.asfun.template.tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.HelperStringTokenizer;

public class ExtendsTag implements Tag{
	
	private String templateFile;

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler)
			throws CompilerException {
		String templateRoot = compiler.fetchGlobalScope("TPL_ROOT_DIR").toString();
		try {
			Reader reader = new FileReader(templateRoot + File.separator + templateFile);
			JangodParser parser = new JangodParser(reader);
			JangodCompiler parent = compiler.copy();
			compiler.assignRuntimeScope("'IS\"CHILD", true, 1);
			parent.assignRuntimeScope("'IS\"PARENT", true, 1);
			String semi = parent.render(parser);
			compiler.assignRuntimeScope("'SEMI\"FORMAL", semi, 1);
			return "";
		} catch (FileNotFoundException e) {
			throw new CompilerException(e.getMessage());
		} catch (ParserException e) {
			throw new CompilerException(e.getMessage());
		}
		
//		StringBuffer sb = new StringBuffer();
//		sb.append("<" + getTagName() + ">");
//		for(Node node : carries) {
//			sb.append(node.render(compiler));
//		}
//		sb.append("</" + getTagName() + ">");
//		return sb.toString();
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
