package net.asfun.template.parse;

import java.util.ArrayList;
import java.util.List;

public class EchoToken extends Token {
	
	public EchoToken(String image) throws ParserException{
		super(image);
	}

	private String var;
	
	private List<Object> filters;
	
	public String getVariable() {
		return var;
	}
	
	/**
	 * get the filters with their args
	 * List item Object :
	 * 		filter without arg is instanceof String
	 * 		filter with args is instanceof List<String> 
	 * @return filters
	 */
	public List<Object> getFilters() {
		return filters;
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
		content = image.substring(2, image.length()-2).trim();
		filters = new ArrayList<Object>();
		//image = obj.attr.attr|filter1:"ar|g1",arg2|filter2:'a:",b"c'
		int pointer = content.indexOf('|');
		if ( pointer < 0 ) {
			var = content;
		} else {
			var = content.substring(0, pointer).trim();
			content = content.substring(pointer+1).trim();
			while ( content.length() > 0 ) {
				parseFilter(content);
			}
		}
	}
	
	private void parseFilter(String filterString) throws ParserException {
		//filterString = filter1:"ar|g1",arg2|filter2:'a:",b"c'
		int postColon = filterString.indexOf(':');
		int postPipe = filterString.indexOf('|');
		//filterString = filter1
		if ( postColon == postPipe ) {
			filters.add(filterString);
			content = "";
		}
		//filterString = filter1:argString|filter2
		if ( postColon > 0 && ( postColon < postPipe || postPipe < 0 )) {
			List<String> filter = new ArrayList<String>();
			filter.add(filterString.substring(0, postColon).trim());
			String argString = filterString.substring(postColon+1).trim();
			do {
				argString = parseArg(argString, filter);
			} while(argString != null);
			filters.add(filter.toArray());
		} 
		if ( postPipe > 0 && ( postPipe < postColon || postColon < 0 )) {
			filters.add(filterString.substring(0, postPipe).trim());
			content = filterString.substring(postPipe+1).trim();
		}
	}
	
	private String parseArg(String argString, List<String> filter) throws ParserException {
		//"ar|g1:",arg2   or 'a:"b"|c'|filter2  or arg3
		if( argString.charAt(0) == '"' ) {
			argString = argString.substring(1);
			int post = argString.indexOf('"');
			if ( post < 0 ) {
				throw new ParserException("filter argument doesn't match quotes");
			} else {
				filter.add(argString.substring(0,post));
				if( post < argString.length() - 2) {
					argString = argString.substring(post+1).trim();
					if ( argString.charAt(0) == '|' ) {
						content = argString.substring(1).trim();
						return null;
					} 
					else if ( argString.charAt(0) == ',' ) {
						return argString.substring(1).trim();
					}
					else {
						throw new ParserException("filter argument(s) is illegal");
					}
				}
			}
		}
		else if( argString.charAt(0) == '\'' ) {
			argString = argString.substring(1);
			int post = argString.indexOf('\'');
			if ( post < 0 ) {
				throw new ParserException("filter argument doesn't match quotes");
			} else {
				filter.add(argString.substring(0,post));
				if( post < argString.length() - 2) {
					argString = argString.substring(post+1).trim();
					if ( argString.charAt(0) == '|' ) {
						content = argString.substring(1).trim();
						return null;
					} 
					else if ( argString.charAt(0) == ',' ) {
						return argString.substring(1).trim();
					}
					else {
						throw new ParserException("filter argument(s) is illegal");
					}
				}
			}
		}
		else {
			int postComma = argString.indexOf(',');
			int postPipe = argString.indexOf('|');
			if ( postComma > 0 && ( postPipe > postComma || postPipe < 0)) {
				filter.add(argString.substring(0,postComma).trim());
				if( postComma < argString.length() - 1) {
					return argString.substring(postComma+1).trim();
				}
			}
			if ( postPipe > 0 && ( postPipe < postComma || postComma < 0)) {
				filter.add(argString.substring(0,postPipe).trim());
				if( postPipe < argString.length() - 1) {
					//RETURN NULL start a new filter parse
					content = argString.substring(postPipe+1).trim();
					return null;
				}
			}
			if ( postComma == postPipe ) {
				filter.add(argString);
			}
		}
		content = "";
		return null;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		String s = "[VAR]\r\n" +var;
		for(Object fi : filters) {
			if ( fi instanceof String) {
				s += "\r\n\t" + fi;
			} else {
				String[] fig = (String[])fi;
				s += "\r\n";
				for(String str : fig) {
					s += "\t" + str;
				}
			}
		}
		return s;
	}
}
