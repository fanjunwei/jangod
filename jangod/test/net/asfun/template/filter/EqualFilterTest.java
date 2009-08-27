package net.asfun.template.filter;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import net.asfun.template.compile.CompilerException;

import org.junit.Before;
import org.junit.Test;

public class EqualFilterTest extends ZzzBase{

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		filter = new EqualFilter();
		compiler.assignEngineScope("var1", 
				new String[] {"abc", "def", null, "ghi", "xyz", "123", "aaa"});
		compiler.assignEngineScope("var2", "asdfghjkl");
		compiler.assignEngineScope("var3", null);
		compiler.assignEngineScope("var4", 234);
		compiler.assignEngineScope("var5", compiler);
		HashMap map = new HashMap();
		map.put("a", "aaa");
		map.put("b", "bbb");
		map.put("c", "ccc");
		map.put("d", null);
		compiler.assignEngineScope("var6", map);
		ArrayList al = new ArrayList();
		al.add("ddd");
		al.add("ccc");
		al.add(234);
		al.add(map);
		al.add(al);
		al.add("abc");
		al.add(null);
		al.add(true);
		compiler.assignEngineScope("var7", al);
		compiler.assignEngineScope("var8", "bcd");
	}
	
	@Test
	public void test1() throws CompilerException {
		Object res = filter.filter("bcd", compiler, "var8");
		assertEquals(true, res);
	}
	
	@Test
	public void test2() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var4");
		Object res = filter.filter(obj, compiler, "'234'");
		assertEquals(true, res);
	}
	
	@Test
	public void test3() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var3");
		Object res = filter.filter(obj, compiler, "'234'");
		assertEquals(false, res);
	}
	
	@Test
	public void test4() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var3");
		Object res = filter.filter(obj, compiler, "'234'");
		assertEquals(false, res);
	}
	
	@Test
	public void test5() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var5");
		Object res = filter.filter(obj, compiler, "var5");
		assertEquals(true, res);
	}
	
	@Test
	public void testGetName() {
		assertEquals("equal", filter.getName());
	}

}
