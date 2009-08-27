package net.asfun.template.filter;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import net.asfun.template.compile.CompilerException;

import org.junit.Before;
import org.junit.Test;

public class OrFilterTest extends ZzzBase{

	@Before
	public void setUp() throws Exception {
		filter = new OrFilter();
		compiler.assignEngineScope("var1", "hello");
		compiler.assignEngineScope("var2", "");
		compiler.assignEngineScope("var3", null);
		compiler.assignEngineScope("var4", new Object[]{});
		compiler.assignEngineScope("var5", new ArrayList<Object>());
		compiler.assignEngineScope("var6", 0);
		compiler.assignEngineScope("var7", 0.0f);
		compiler.assignEngineScope("var8", 21);
		compiler.assignEngineScope("var9", compiler);
	}

	@Test
	public void test1() throws CompilerException {
		Boolean res = (Boolean) filter.filter(1, compiler, new String[]{});
		assertEquals(true, res);
	}
	
	@Test
	public void test2() throws CompilerException {
		Boolean res = (Boolean) filter.filter("", compiler, new String[]{"var1", "var3"});
		assertEquals(true, res);
	}
	
	@Test
	public void test3() throws CompilerException {
		Boolean res = (Boolean) filter.filter("", compiler, new String[]{"var3", "var8"});
		assertEquals(true, res);
	}
	
	@Test
	public void test4() throws CompilerException {
		Boolean res = (Boolean) filter.filter(-0l, compiler, new String[]{"var5", "var4"});
		assertEquals(false, res);
	}
	
	@Test
	public void test5() throws CompilerException {
		Boolean res = (Boolean) filter.filter(-02l, compiler, new String[]{"var8", "var9"});
		assertEquals(true, res);
	}
	
	@Test
	public void testGetName() {
		assertEquals("or", filter.getName());
	}

}
