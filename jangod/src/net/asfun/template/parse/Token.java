package net.asfun.template.parse;

public class Token {

	protected int kind;
	protected int beginLine, beginColumn, endLine, endColumn;
	protected String image;
	protected Token next;
	
	public String toString() {
		return image + "-:-" + (char)kind;
	}
	
	public Token(int tokenKind, String content) {
		kind = tokenKind;
		image = content;
	}

	public int getKind() {
		return kind;
	}

	public int getBeginLine() {
		return beginLine;
	}

	public void setBeginLine(int beginLine) {
		this.beginLine = beginLine;
	}

	public int getBeginColumn() {
		return beginColumn;
	}

	public void setBeginColumn(int beginColumn) {
		this.beginColumn = beginColumn;
	}

	public int getEndLine() {
		return endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public int getEndColumn() {
		return endColumn;
	}

	public void setEndColumn(int endColumn) {
		this.endColumn = endColumn;
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

	public static Token newToken(int tokenKind, char[] image2) {
		return new Token(tokenKind, new String(image2));
	}
	
}
