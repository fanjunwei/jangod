package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.HelperStringTokenizer;

public class IncludeTag implements Tag{
	
	@Override
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler)
			throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if( helper.length != 1) {
			throw new CompilerException("include tag expects 1 helper >>> " + helper.length);
		}
		String templateFile = (String) compiler.resolveVariable(helper[0]);
		try {
			JangodParser parser = new JangodParser(compiler.getLoader().getReader(templateFile));
			JangodCompiler child = compiler.copy();
			child.assignRuntimeScope(JangodCompiler.INSERT_FLAG, true, 1);
			return child.render(parser);
		} catch (ParserException e) {
			throw new CompilerException(e.getMessage());
		}
	}

	@Override
	public String getEndTagName() {
		return null;
	}

	@Override
	public String getName() {
		return "include";
	}

}
