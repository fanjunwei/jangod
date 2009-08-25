package net.asfun.template.compile;

import java.util.ArrayList;
import java.util.List;

import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.TagToken;

public class TagNode implements Node{

	private int level;
	private TagToken master;
	private List<Node> carries;
	protected String endTagName;
	private Tag tag;

	public TagNode(TagToken token, JangodParser parser, int lvl) throws CompilerException {
		master = token;
		level = lvl;
		tag = TagLibrary.getTag(master.getTagName());
		endTagName = tag.getEndTagName();
		if ( endTagName != null ) {
			carries = NodeList.makeList(parser, endTagName, level + 1);
		} else {
			carries = new ArrayList<Node>();
		}
	}

	@Override
	public String render(JangodCompiler compiler) throws CompilerException {
		compiler.setLevel(level);
		return tag.compile(carries, master.getHelpers(), compiler);
	}
	
	public String toString() {
//		return "[TagNode:" + tag.getName() + "]";
		return tag.getName();
	}
}
