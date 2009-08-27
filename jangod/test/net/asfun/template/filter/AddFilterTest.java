package net.asfun.template.filter;


import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AddFilterTest extends ZzzBase{

	@Before
	public void setUp() throws Exception {
		filter = new AddFilter();
	}

	@Test
	public void testInt() throws CompilerException {
		Object res = filter.filter(562, compiler, new String[]{"56"});
		assertEquals(618, res);
	}
	
	@Test
	public void testInteger() throws CompilerException {
		Object res = filter.filter(new Integer(-298), compiler, new String[]{"\"23\""});
		assertEquals(-275, res);
	}
	
	@Test(expected=CompilerException.class)
	public void testFloat() throws CompilerException {
		Object res = filter.filter(new Double(-20.24), compiler, new String[]{"abc"});
		assertEquals(20.24f, (Double)res, 0.01f);
	}
	
	@Test
	public void testDouble() throws CompilerException {
		compiler.assignEngineScope("var1", 25);
		Object res = filter.filter(new Double(-20.24), compiler, new String[]{"var1"});
		assertEquals(4.76f, (Double)res, 0.01f);
	}
	
	@Test
	public void testLong() throws CompilerException {
		Object res = filter.filter(new Long(-0), compiler, new String[]{"2"});
		assertEquals(2, res);
	}
	
	@Test
	public void testShort() throws CompilerException {
		Object res = filter.filter(new Short((short) -22222222), compiler, new String[]{"'2'"});
		assertEquals((short)-22222220, res);
	}
	
	@Test
	public void testByte() throws CompilerException {
		Object res = filter.filter(new Byte((byte) 222), compiler, new String[]{"-3"});
		assertEquals((byte)219, res);
	}
	
	@Test
	public void testString() throws CompilerException {
		Object res = filter.filter("-215.5256", compiler, new String[]{"-15.2"});
		assertEquals(-230.72559d, (Double)res, 0.0001d);
	}
	
	@Test
	public void testBigInt() throws CompilerException {
		Object res = filter.filter(BigInteger.valueOf(-1547898522234l), compiler, new String[]{"2234"});
		assertEquals(BigInteger.valueOf(-1547898520000l), res);
	}
	
	@Test(expected=CompilerException.class)
	public void testString2() throws CompilerException {
		Object res = filter.filter("abcd", compiler, new String[]{"2"});
		assertEquals(12, res);
	}
	
	@Test
	public void testOther() throws CompilerException {
		Filter af = new AbsFilter();
		Object res = filter.filter(af, compiler, new String[]{"1"});
		assertEquals(af, res);
	}

	@Ignore
	public void testGetName() {
		assertEquals("add", filter.getName());
	}
}
