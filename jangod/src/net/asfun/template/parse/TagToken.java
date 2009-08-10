package net.asfun.template.parse;

public class TagToken extends Token {
	
	private String tagName;
	private String helpers;

	public TagToken(String image) throws ParserException{
		super(image);
	}

	/**
	 * Get tag name
	 */
	@Override
	protected void parse() {
		content = image.substring(2, image.length()-2).trim();
		int postBlank = content.indexOf(' ');
		if ( postBlank > 0 ) {
			tagName = content.substring(0, postBlank);
			helpers = content.substring(postBlank).trim();
		}
		else {
			tagName = content;
			helpers = "";
		}
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public String toString() {
		if ( helpers.length() == 0) {
			return "[TAG]\r\n" + tagName;
		}
		return "[TAG]\r\n" + tagName + "\r\n\t" + helpers;
	}

}
