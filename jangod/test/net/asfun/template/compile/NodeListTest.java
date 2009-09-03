package net.asfun.template.compile;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import net.asfun.template.parse.JangodParser;


public class NodeListTest {

	String script;
	JangodParser parser;
	
	@Test
	public void test1() throws CompilerException {
		script = "abc{{post.title}}def";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
	}
	
	@Test
	public void test2() throws CompilerException {
		script = "{%extends a %}abc{%block as%}{%block as.nest%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
		assertEquals("block", nodes.get(2).toString());
	}
	
	@Test
	public void test3() throws CompilerException {
		script = "{%extends a %}abc{%block as%}{%block as.nest%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
		assertEquals("block", nodes.get(2).toString());
	}
	
	@Test
	public void test4() throws CompilerException {
		script = "{%extends a %}abc{%block as%}{%block as.nest%}{%endblock%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
		assertEquals("block", nodes.get(2).toString());
	}
	
	@Test
	public void test5() throws CompilerException {
		script = "{%extends a %}abc{%block as%}{%block as.nest%}{%endblock%}{%endblock%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
		assertEquals("block", nodes.get(2).toString());
	}
	
	@Test
	public void test6() throws CompilerException {
		script = "{%extends a %}abc{%block as%}{%block as.nest%}{%endfor%}{%endblock%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
		assertEquals("block", nodes.get(2).toString());
	}
	
	@Test
	public void test7() throws CompilerException {
		script = "{%extends a %}abc{%endend%}{%block as%}{%block as.nest%}{%endblock%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(3, nodes.size());
		assertEquals("block", nodes.get(2).toString());
	}
	
	@Test
	public void test8() throws CompilerException {
		script = "{%extends a %}abc{%else%}{%block as%}{%block as.nest%}{%endblock%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(4, nodes.size());
		assertEquals("else", nodes.get(2).toString());
	}
	
	@Test
	public void test9() throws CompilerException {
		script = "{%extends a %}{%if%}abc{%block as%}{%block as.nest%}{%endblock%}{%endblock%}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(2, nodes.size());
		assertEquals("if", nodes.get(1).toString());
	}
	
	@Test
	public void test10() throws CompilerException {
		//TODO 允许注释jangod的代码，也要允许输出{#
		script = "{%extends a %}都督府{{ab}}{#dlff{{dkf}}j#}";
		parser = new JangodParser(script);
		List<Node> nodes = NodeList.makeList(parser, null, 1);
		assertEquals(6, nodes.size());
	}
}
