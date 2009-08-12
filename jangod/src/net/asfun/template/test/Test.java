package net.asfun.template.test;

import net.asfun.template.util.HelperStringTokenizer;

public class Test {

	public static void main(String... args) {
		HelperStringTokenizer tk = new HelperStringTokenizer(
				"ca 'b',\"c,d ','\" edf,hgi");
		tk.splitComma(true);
		while( tk.hasNext() ) {
			System.out.println(tk.next());
		}
	}
}
