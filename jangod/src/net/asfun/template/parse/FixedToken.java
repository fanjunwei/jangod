package net.asfun.template.parse;

public class FixedToken extends Token{

	public FixedToken(String image) {
		super(image);
	}
	
	/**
	 * change "\{" and "\}" to "{" or "}"
	 */
	@Override
	protected void parse() {
		content = image.replaceAll("\\{", "{").replaceAll("\\}", "}");
	}

	public boolean isBlank() {
		return content.trim().length()==0;
	}
	
	public String trim() {
		return content.trim();
	}
}
