package net.asfun.template.parse;

import java.util.ArrayList;
import java.util.List;

public class EchoToken extends Token {
	
	public EchoToken(String image) {
		super(image);
		filters = new ArrayList<Object>();
	}

	private String var;
	
	private List<Object> filters;

	/**
	 * Get var and filters
	 * Like that if
	 *     image = {{obj.attr.attr|filter1:"ar|g1",arg2|filter2:'a:",b"c' }}
	 * then
	 *     var = obj.attr.attr
	 *     filter = [
	 *         [filter1,ar|g1,arg2],
	 *         [filter2,a:",b"c]   ]
	 */
	@Override
	protected void parse() {
		content = image.substring(2, image.length()-2).trim();
		//image = obj.attr.attr|filter1:"ar|g1",arg2|filter2:'a:",b"c'
		int pointer = content.indexOf('|');
		if ( pointer < 0 ) {
			var = content;
		} else {
			var = content.substring(0, pointer).trim();
			content = content.substring(pointer);
			pointer = content.indexOf(':');
			if ( pointer < 0 ) {
				filters.add(content);
			} else {
				filters.add(content.substring(0, pointer).trim());
				content = content.substring(pointer);
				//"ar|g1",arg2|filter2:'a:",b"c'
			}
		}
		//var = obj.attr.attr
		//filter = [
		//	[filter1,ar|g1,arg2],
		//  [filter2,a:",b"c]   ]
		//TODO 
	}

}
