package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.TagToken;
import net.asfun.template.tag.Tag;
import net.asfun.template.tag.TagLibrary;

public class TagNode implements Node{

	private TagToken master;
	private List<Node> carries;
	protected String endTagName;
	private Tag tag;

	public TagNode(TagToken token, JangodParser parser) throws CompilerException {
		master = token;
		tag = TagLibrary.getTag(master.getTagName(), master.getHelpers());
		endTagName = tag.getEndTagName();
		if ( endTagName != null ) {
			carries = NodeList.makeList(parser, endTagName);
		}
	}

	@Override
	public String render(JangodCompiler compiler) throws CompilerException {
		return tag.compile(carries, compiler);
	}
	
}
