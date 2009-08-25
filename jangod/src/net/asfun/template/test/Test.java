package net.asfun.template.test;

import net.asfun.template.util.HelperStringTokenizer;

public class Test {

	public static void main(String... args) {
	
//		String a = "大后方拉菲a1.";
//		System.out.println(a.length());
		
		String str = "ca 'b'\"c,d ','\" edf hgi for a in post.comments|filter1:a|filter2:\"abc \",c|filter3 ";
		HelperStringTokenizer ht = new HelperStringTokenizer(str);
		ht.splitComma(true);
		while(ht.hasNext()) {
			System.out.println(ht.next());
		}
	}

}
