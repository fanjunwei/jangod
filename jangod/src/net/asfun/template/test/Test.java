package net.asfun.template.test;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.ListOrderedMap;

import net.asfun.template.bin.FloorBindings;
import net.asfun.template.util.HelperStringTokenizer;

public class Test {

	public static void main(String... args) {
//		HelperStringTokenizer tk = new HelperStringTokenizer(
//				"ca 'b',\"c,d ','\" edf,hgi");
//		tk.splitComma(true);
//		while( tk.hasNext() ) {
//			System.out.println(tk.next());
//		}
		
		FloorBindings fb1 = new FloorBindings(), fb2 = new FloorBindings();
		fb1.put("key1", "value1", 1);
		fb1.put("key2", 2, 3);
		fb2 = fb1.copy();
		fb2.put("key3", "dslfskjf", 1);
		fb1.put("key4", "value4", 5);
		System.out.println(fb1.get("key3", 1));
		System.out.println(fb1.get("key0", 3));

		String[] dfkf = new String[10];
		System.out.println(dfkf.getClass().isArray());
		
//		ListOrderedMap lom = new ListOrderedMap();
//		lom.put(1, "abc");
//		lom.put(3, "def");
//		lom.put(2, "ghi");
//		MapIterator mi = lom.mapIterator();
//		while(mi.hasNext()) {
//			mi.next();
//			System.out.println(mi.getKey());
//			System.out.println(mi.getValue());
//		}
//		lom.put(3, "xyz");
//		mi = lom.mapIterator();
//		while(mi.hasNext()) {
//			mi.next();
//			System.out.println(mi.getKey());
//			System.out.println(mi.getValue());
//		}
	}

}
