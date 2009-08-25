package net.asfun.template.compile;

import net.asfun.template.tag.*;

public class TagLibrary extends SimpleLibrary<Tag>{
	
	private static TagLibrary lib;
	
	static {
		lib = new TagLibrary();
	}

	@Override
	protected void initialize() {
		Tag extTag = new ExtendsTag();
		register(extTag.getName(), extTag);
		Tag blkTag = new BlockTag();
		register(blkTag.getName(), blkTag);
		Tag incTag = new IncludeTag();
		register(incTag.getName(), incTag);
		
		Tag forTag = new ForTag();
		register(forTag.getName(), forTag);
		Tag cycleTag = new CycleTag();
		register(cycleTag.getName(), cycleTag);
		Tag ifcTag = new IfchangeTag();
		register(ifcTag.getName(), ifcTag);
		
		Tag ifTag = new IfTag();
		register(ifTag.getName(), ifTag);	
		Tag elseTag = new ElseTag();
		register(elseTag.getName(), elseTag);
		Tag ifnTag = new IfnotTag();
		register(ifnTag.getName(), ifnTag);
	}

	public static Tag getTag(String tagName) throws CompilerException {
		return lib.fetch(tagName);
	}

	public static void addTag(Tag tag) {
		lib.register(tag.getName(), tag);
	}
}
