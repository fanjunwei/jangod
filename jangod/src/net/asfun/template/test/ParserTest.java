package net.asfun.template.test;

import net.asfun.template.parse.Token;
import net.asfun.template.parse.TokenManager;

public class ParserTest {

	public static void main(String[] args) {
		String tpl = "{{aa}}bbb{#ddd#}ccdfe{%dfff%}{{{#{%";
		TokenManager.init(tpl);
		Token tk = TokenManager.getNextToken();
		while ( tk != null ) {
			System.out.println(tk);
			System.out.println("-------------------------");
			tk = TokenManager.getNextToken();
		}
	}
}
