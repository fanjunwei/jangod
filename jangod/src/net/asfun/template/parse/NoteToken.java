package net.asfun.template.parse;

public class NoteToken extends Token {

	public NoteToken(String image) throws ParserException{
		super(image);
	}
	
	@Override
	public int getType() {
		return TOKEN_NOTE;
	}

	/**
	 * remove all content, we don't need it.
	 */
	@Override
	protected void parse() {
		content = "";
	}
	
	public String toString() {
		return "[NOTE]";
	}

}
