package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Tag;
import net.asfun.template.compile.VariableFilter;
import net.asfun.template.util.ForLoop;
import net.asfun.template.util.HelperStringTokenizer;
import net.asfun.template.util.ObjectIterator;
import net.asfun.template.compile.Node;

/**
 * {% for a in b %}	
 * @author fangchq
 *
 */
public class ForTag implements Tag {
	

	@Override
	public String compile(List<Node> carries, String helpers, JangodCompiler compiler) throws CompilerException {
		String[] helper = new HelperStringTokenizer(helpers).allTokens();
		if ( helper.length != 3 ) {
			throw new CompilerException("for tag expects 3 helpers >>> " + helper.length);
		}
		String item = helper[0];
		Object collection = VariableFilter.compute( helper[2], compiler);
		ForLoop loop = ObjectIterator.getLoop(collection);
		
		int level = compiler.getLevel() + 1;
		compiler.assignRuntimeScope("loop", loop, level);
		StringBuffer buff = new StringBuffer();
		while ( loop.hasNext() ) {
			//set item variable
			compiler.assignRuntimeScope(item, loop.next(), level);
			for(Node node : carries) {
				buff.append(node.render(compiler));
			}
		}
		return buff.toString();
	}

	@Override
	public String getEndTagName() {
		return "endfor";
	}

	@Override
	public String getName() {
		return "for";
	}

}
