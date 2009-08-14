package net.asfun.template.compile;

import net.asfun.template.tag.*;
import net.asfun.template.util.JangodLogger;

public class TagLibrary extends SimpleLibrary<Tag>{
	
	private static TagLibrary lib;
	
	static {
		lib = new TagLibrary();
	}

	@Override
	protected void initialize() {
		Tag forTag = new ForTag();
		register(forTag.getTagName(), forTag);
		Tag cycleTag = new CycleTag();
		register(cycleTag.getTagName(), cycleTag);
		Tag ifTag = new IfTag();
		register(ifTag.getTagName(), ifTag);
		Tag ifcTag = new IfchangeTag();
		register(ifcTag.getTagName(), ifcTag);
		Tag extTag = new ExtendsTag();
		register(extTag.getTagName(), extTag);
		Tag incTag = new IncludeTag();
		register(incTag.getTagName(), incTag);
		Tag blockTag = new BlockTag();
		register(blockTag.getTagName(), blockTag);
		Tag elseTag = new ElseTag();
		register(elseTag.getTagName(), elseTag);
	}

	public static Tag getTag(String tagName, String helpers) throws CompilerException {
		try {
			Tag tag = lib.fetch(tagName).getClass().newInstance();
			tag.initialize(helpers);
			return tag;
		} catch (InstantiationException e) {
			JangodLogger.severe("Can't create handler tag >>> " + tagName);
			throw new CompilerException(e.getMessage());
		} catch (IllegalAccessException e) {
			JangodLogger.severe("Can't create handler tag >>> " + tagName);
			throw new CompilerException(e.getMessage());
		}
	}
}
