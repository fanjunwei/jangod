package net.asfun.template.parse;

public abstract class Token implements ParserConstants{

	protected String image;
	protected String content;
	
	public Token(String image2) throws ParserException{
		image = image2;
		parse();
	}

	public String getImage() {
		return image;
	}
	
	public String toString() {
		return image;
	}
	
	protected abstract void parse() throws ParserException;
	
	public abstract int getType();

	public static Token newToken(int tokenKind, char[] image2) throws ParserException {
		String image = String.valueOf(image2).trim();
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
