package net.asfun.template.parse;

public class NoteToken extends Token {

	public NoteToken(String image) {
		super(image);
	}

	/**
	 * remove all content, we don't need it.
	 */
	@Override
	protected void parse() {
		content = "";
	}

}
