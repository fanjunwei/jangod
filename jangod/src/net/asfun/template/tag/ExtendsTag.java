package net.asfun.template.tag;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Node;
import net.asfun.template.compile.Tag;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.HelperStringTokenizer;

public class ExtendsTag implements Tag{


	@Override
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler)
			throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if( helper.length != 1) {
			throw new CompilerException("extends tag expects 1 helper >>> " + helper.length);
		}
		String templateFile = (String) compiler.resolveVariable(helper[0]);
		try {
			JangodParser parser = new JangodParser(compiler.getLoader().getReader(templateFile));
			JangodCompiler parent = compiler.copy();
			compiler.assignRuntimeScope(JangodCompiler.CHILD_FLAG, true, 1);
			parent.assignRuntimeScope(JangodCompiler.PARENT_FLAG, true, 1);
			ListOrderedMap blockList = new ListOrderedMap();
			compiler.assignEngineScope(JangodCompiler.BLOCK_LIST, blockList);
			String semi = parent.render(parser);
			compiler.assignEngineScope(JangodCompiler.SEMI_RENDER, semi);
			return "";
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
		return "extends";
	}

}
