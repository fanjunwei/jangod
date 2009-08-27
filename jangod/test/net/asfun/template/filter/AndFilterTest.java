package net.asfun.template.filter;


import static org.junit.Assert.*;

import java.util.ArrayList;

import net.asfun.template.compile.CompilerException;

import org.junit.Before;
import org.junit.Test;

public class AndFilterTest extends ZzzBase{

	@Before
	public void setUp() throws Exception {
		filter = new AndFilter();
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
		Boolean res = (Boolean) filter.filter(12, compiler, new String[]{"var1", "var3"});
		assertEquals(false, res);
	}
	
	@Test
	public void test3() throws CompilerException {
		Boolean res = (Boolean) filter.filter(-78, compiler, new String[]{"var1", "var4"});
		assertEquals(false, res);
	}
	
	@Test
	public void test4() throws CompilerException {
		Boolean res = (Boolean) filter.filter(-56l, compiler, new String[]{"var5", "var9"});
		assertEquals(false, res);
	}
	
	@Test
	public void test5() throws CompilerException {
		Boolean res = (Boolean) filter.filter(-0l, compiler, new String[]{"var8", "var9"});
		assertEquals(false, res);
	}
	
	@Test
	public void testGetName() {
		assertEquals("and", filter.getName());
	}

}
