package net.asfun.template.util;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VariableTest {

	private Object obj;
	private Variable var;
	private Object res;
	
	@Before
	public void setUp() throws Exception {
		obj = new java.util.Date();
	}
	
	@Test
	public void test1() {
		var = new Variable("now.DAte");
		res = var.resolve(obj);
		assertEquals(9,res);
	}

}
