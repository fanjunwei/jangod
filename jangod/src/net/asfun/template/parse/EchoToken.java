package net.asfun.template.parse;

import java.util.List;

public class EchoToken extends Token {
	
	private FilterParser fp;
	
	public EchoToken(String image) throws ParserException{
		super(image);
	}
	
	public String getVariable() {
		return fp.getVariable();
	}
	
	/**
	 * get the filters
	 * @return filters
	 */
	public List<String> getFilters() {
		return fp.getFilters();
	}
	
	/**
	 * get filters' args
	 * @return
	 */
	public List<String[]> getArgss() {
		return fp.getArgss();
	}

	/**
	 * Get var and filters
	 * Like that if
	 *     image = {{obj.attr.attr|filter1:"ar|g1",arg2|filter2:'a:",b"c' }}
	 * then
	 *     var = obj.attr.attr
	 *     filter = [
	 *         [filter1,ar|g1,arg2],
	 *         [filter2,a:",b"c]   ]
	 * @throws ParserException 
	 */
	@Override
	protected void parse() throws ParserException {
		fp = new FilterParser(image.substring(2, image.length()-2).trim());
		fp.parse();
	}

	public String toString() {
		String s = "[VAR]\r\n" + fp.getVariable();
		int i,j;
		for (i=0; i<fp.getFilters().size(); i++) {
			s += "\r\n\t" + fp.getFilters().get(i);
			String[] args = fp.getArgss().get(i);
			if ( args != null) {
				s += "\r\n\t";
				for(j=0; j<args.length; j++) {
					s += "\t" + args[j];
				}
			}
		}
		return s;
	}

	@Override
	public int getType() {
		return TOKEN_ECHO;
	}
}
