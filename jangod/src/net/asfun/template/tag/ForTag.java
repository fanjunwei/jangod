package net.asfun.template.tag;

import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.compile.Tag;
import net.asfun.template.compile.VariableFilter;
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
		List<Object> it = ObjectIterator.toList(collection, false);
//		if ( it.size() == 0 ) {
//			return "";
//		}
		int level = compiler.getLevel() + 1;
		ForLoop loop = (ForLoop) compiler.fetchRuntimeScope("loop", level);
		if ( loop == null ) {
			loop = new ForLoop(it.size());
			compiler.assignRuntimeScope("loop", loop, level);
		}
		StringBuffer buff = new StringBuffer();
		for(Object obj : it) {
			//set item variable
			compiler.assignRuntimeScope(item, obj, level);
			for(Node node : carries) {
				buff.append(node.render(compiler));
			}
			//change loop variable
			loop.next();
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
