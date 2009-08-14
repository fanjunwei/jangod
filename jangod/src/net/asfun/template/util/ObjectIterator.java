package net.asfun.template.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectIterator {

	@SuppressWarnings("unchecked")
	public static List<Object> toList(Object obj, boolean isReverse) {
		ArrayList<Object> res = new ArrayList<Object>();
		if ( obj == null ) {
			return res;
		}
		if ( obj.getClass().isArray() ) {
			int length = Array.getLength(obj);
			if ( isReverse ) {
				for(int i=length; i>0; ) {
					res.add(Array.get(obj, --i));
				}
			} else {
				for(int i=0; i<length; i++) {
					res.add(Array.get(obj, i));
				}
			}
		} else {
			Iterator it = null;
			if (obj instanceof Iterable) {
				it = ((Iterable) obj).iterator();
			}
			if (obj instanceof Iterator) {
				it = (Iterator)obj;
			}
			if ( it != null) {
				//TODO reverse 
				while(it.hasNext()) {
					res.add(it.next());
				}
			}
		}
		return res;
	}
}
