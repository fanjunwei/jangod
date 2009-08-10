package net.asfun.template.compile;

import java.util.List;

import net.asfun.template.parse.TagToken;

public class TagNode implements Node{

	private TagToken master;
	private List<Node> carries;
	
	public String getTagName() {
		return master.getTagName();
	}

	@Override
	public String compile(JangodCompiler compiler) {
		// TODO Auto-generated method stub
		return null;
	}
}
