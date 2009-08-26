package net.asfun.template.tag;

import java.io.IOException;
import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.HelperStringTokenizer;
import net.asfun.template.util.TemplateLoader;

public class IncludeTag implements Tag{
	
	@Override
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler)
			throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if( helper.length != 1) {
			throw new CompilerException("include tag expects 1 helper >>> " + helper.length);
		}
		String templateFile = compiler.resolveString(helper[0]);
		try {
			TemplateLoader loader = new TemplateLoader();
			loader.setBase(compiler.getConfig().getTemplateRoot());
			JangodParser parser = new JangodParser(loader.getReader(templateFile));
			JangodCompiler child = compiler.copy();
			child.assignRuntimeScope(JangodCompiler.INSERT_FLAG, true, 1);
			return child.render(parser);
		} catch (IOException e) {
			throw new CompilerException(e.getMessage());
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
