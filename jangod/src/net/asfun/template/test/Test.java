package net.asfun.template.test;

import java.math.BigDecimal;

import net.asfun.template.parse.FilterParser;
import net.asfun.template.parse.ParserException;
import net.asfun.template.util.HelperStringTokenizer;

public class Test {

	public static void main(String... args) {
	
//		String a = "大后方拉菲a1.";
//		System.out.println(a.length());
		
		String str = "post.comments|filter1:a|filter2:\"abc'|, \", 		 c 	| 	filter3:  'a,\"}|c',bde.abc";
		HelperStringTokenizer ht = new HelperStringTokenizer("'a,b\"c' ,def,	\"a'b\"");
//		ht.splitComma(true);
		while(ht.hasNext()) {
			System.out.println(ht.next());
		}
		FilterParser fp = new FilterParser(str);
		try {
			fp.parse();
			System.out.println(fp.getVariable());
			for(String fl : fp.getFilters()) {
				System.out.println(fl);
			}
			for(String ar[] : fp.getArgss() ) {
				if ( ar == null ) continue;
				for(String arg : ar) {
					System.out.println(arg);
				}
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal db = new BigDecimal("-2.62");
		System.out.println(db.doubleValue());
	}

}
