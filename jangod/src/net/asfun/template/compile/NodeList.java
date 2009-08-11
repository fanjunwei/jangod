package net.asfun.template.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.asfun.template.parse.EchoToken;
import net.asfun.template.parse.JangodParser;
import net.asfun.template.parse.TagToken;
import net.asfun.template.parse.Token;

public class NodeList {

	private static final Logger logger = Logger.getLogger("asfun.jandog");
	
	public static List<Node> makeList(JangodParser parser, String endTagName) {
		List<Node> nodes = new ArrayList<Node>();
		Token token;
		TagToken tag;
		while( parser.hasNext() ) {
			token = parser.next();
			switch(token.getType()) {
				case Token.TOKEN_FIXED :
				case Token.TOKEN_NOTE :		
					TextNode xn = new TextNode(token);
					nodes.add(xn);
					break;
				case Token.TOKEN_INST :
					//TODO not right now
					break;
				case Token.TOKEN_ECHO :
					VariableNode vn = new VariableNode((EchoToken) token);
					nodes.add(vn);
					break;
				case Token.TOKEN_TAG :
					tag = (TagToken) token;
					if ( tag.getTagName().equals(endTagName) ) {
						return nodes;
					}
					try {
						TagNode tn = new TagNode((TagToken) token, parser);
						nodes.add(tn);
					} catch (CompilerException e) {
						logger.warning("Can't create node with token >>> " + token);
					}
					break;
				default :
					logger.warning("Unknown type token >>> " + token);
			}
		}
		return nodes;
	}
}
