package net.asfun.template.tag;

import java.util.Iterator;
import java.util.List;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.JangodCompiler;
import net.asfun.template.util.ObjectIterator;
import net.asfun.template.util.QuotedStringTokenizer;
import net.asfun.template.compile.Node;

/**
 * {% for a in b %}
 * @author fangchq
 *
 */
public class ForTag implements Tag {

	private String item;
	private String items;

	@Override
	public String compile(List<Node> carries, JangodCompiler compiler) throws CompilerException {
		Iterator<Object> it = ObjectIterator.toIterator(compiler.resolveVariable(items));
		if (it == null) {
			return "";
		}
		StringBuffer buff = new StringBuffer();
		while ( it.hasNext() ) {
			compiler.setVariable(item, it.next());
			for(Node node : carries) {
				buff.append(node.render(compiler));
			}
		}
		return buff.toString();
	}

	/**
	 * @param helpers 
	 * 		like  for [ cat in cats ]
	 * @throws CompilerException 
	 */
	@Override
	public void initialize(String helpers) throws CompilerException {
		String[] helper = new QuotedStringTokenizer(helpers).allTokens();
		if (helper.length != 3) {
			throw new CompilerException("for tag expects three helpers:" + helpers);
		}
		item = helper[0];
		items = helper[2];
	}

	@Override
	public String getEndTagName() {
		return "endfor";
	}

}
