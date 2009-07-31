package net.asfun.template.parse;

public abstract class Token implements ParserConstants{

	protected String image;
	protected String content;
	protected Token next;
	
	public String toString() {
//		return "" + (char)kind + "-:-" + image;
		return image;
	}
	
	public Token(String image2) {
		image = image2;
		parse();
	}

	public String getImage() {
		return image;
	}

	public Token getNext() {
		return next;
	}

	public void setNext(Token next) {
		this.next = next;
	}
	
	protected abstract void parse();

	public static Token newToken(int tokenKind, char[] image2) throws ParserException {
		String image = new String(image2);
		switch( tokenKind ) {
		case TOKEN_FIXED : 
			return new FixedToken(image);
		case TOKEN_NOTE :
			return new NoteToken(image);
		case TOKEN_ECHO :
			return new EchoToken(image);
		case TOKEN_TAG :
			return new TagToken(image);
		case TOKEN_INST :
			return new InstToken(image);
		default :
			throw new ParserException("Creating a token with unknown type" + (char)tokenKind);	
		}
	}
	
}
