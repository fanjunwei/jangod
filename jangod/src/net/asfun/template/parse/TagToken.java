package net.asfun.template.parse;

public class TagToken extends Token {
	
	private String tagName;

	public TagToken(String image) {
		super(image);
	}

	/**
	 * Get tag name
	 */
	@Override
	protected void parse() {
		content = image.substring(2, image.length()-2).trim();
		tagName = content.substring(0, content.indexOf(0));
	}
	
	public String getTagName() {
		return tagName;
	}

}
