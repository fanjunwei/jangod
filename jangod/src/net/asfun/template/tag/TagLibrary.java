package net.asfun.template.tag;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.SimpleLibrary;

public class TagLibrary extends SimpleLibrary<Tag>{
	
	private static TagLibrary lib;
	
	static {
		lib = new TagLibrary();
	}

	@Override
	protected void initialize() {
		register("for", new ForTag());
	}

	public static Tag getTag(String tagName, String helpers) throws CompilerException {
		Tag tag = lib.fetch(tagName);
		tag.initialize(helpers);
		return tag;
	}
}
