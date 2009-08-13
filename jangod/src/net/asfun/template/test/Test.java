package net.asfun.template.test;

import net.asfun.template.bin.FloorBindings;
import net.asfun.template.util.HelperStringTokenizer;

public class Test {

	public static void main(String... args) {
		HelperStringTokenizer tk = new HelperStringTokenizer(
				"ca 'b',\"c,d ','\" edf,hgi");
		tk.splitComma(true);
		while( tk.hasNext() ) {
			System.out.println(tk.next());
		}
		
		FloorBindings fb1 = new FloorBindings(), fb2 = new FloorBindings();
		fb1.put("key1", "value1", 1);
		fb1.put("key2", 2, 3);
		fb2 = fb1.copy();
		fb2.put("key3", "dslfskjf", 2);
		fb1.put("key4", "value4", 5);
		System.out.println(fb1.get("key3", 2));
	}
}
