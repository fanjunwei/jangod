package net.asfun.template.filter;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import net.asfun.template.compile.CompilerException;

import org.junit.Before;
import org.junit.Test;

public class DefaultFilterTest extends ZzzBase{

	@Before
	public void setUp() throws Exception {
		filter = new DefaultFilter();
		compiler.assignEngineScope("var1", 1);
		compiler.assignEngineScope("var2", "hello");
		compiler.assignEngineScope("var3", new Long[]{1l, 3l, 4l});
		compiler.assignEngineScope("var4", 0);
		compiler.assignEngineScope("var5", null);
		compiler.assignEngineScope("var6", "");
		compiler.assignEngineScope("var7", new ArrayList<String>());
		compiler.assignEngineScope("var8", new HashMap<String,String>());
	}
	
	@Test
	public void test1() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var1");
		Object res = filter.filter(obj, compiler, "'a'");
		assertEquals(1, res);
	}
	
	@Test
	public void test2() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var4");
		Object res = filter.filter(obj, compiler, "'a'");
		assertEquals("a", res);
	}
	
	@Test
	public void test3() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var4");
		Object res = filter.filter(obj, compiler, "a");
		assertEquals("a", res);
	}
	
	@Test
	public void test4() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var5");
		Object res = filter.filter(obj, compiler, "var1");
		assertEquals(1, res);
	}
	
	@Test
	public void test5() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var5");
		Object res = filter.filter(obj, compiler, "'var1'");
		assertEquals("var1", res);
	}
	
	@Test(expected=CompilerException.class)
	public void test6() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var5");
		Object res = filter.filter(obj, compiler);
		assertEquals("var1", res);
	}
	
	@Test
	public void test7() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var4");
		Object res = filter.filter(obj, compiler, "var2");
		assertEquals("hello", res);
	}
	
	@Test
	public void test8() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var6");
		Object res = filter.filter(obj, compiler, "var2");
		assertEquals("hello", res);
	}
	
	@Test
	public void test9() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var7");
		Object res = filter.filter(obj, compiler, "var2");
		assertEquals("hello", res);
	}
	
	@Test
	public void test10() throws CompilerException {
		Object obj = compiler.fetchEngineScope("var8");
		Object res = filter.filter(obj, compiler, "var2");
		assertEquals("hello", res);
	}

}
