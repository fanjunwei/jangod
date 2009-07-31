package net.asfun.template.parse;

public interface ParserConstants {

	int TOKEN_PREFIX = '{';
	int TOKEN_POSTFIX = '}';
	int TOKEN_FIXED = 0;
	int TOKEN_NOTE = '#';
	int TOKEN_TAG = '%';
	int TOKEN_ECHO = '{';
	int TOKEN_ECHO2 = '}';
	int TOKEN_INST = '!';

	int EOF = 0;
	int PREFIX_NOTE = 5;
	int POSTFIX_NOTE = 6;
	int PREFIX_TAG = 7;
	int POSTFIX_TAG = 8;
	int PREFIX_ECHO = 9;
	int POSTFIX_ECHO = 10;
	int PREFIX_CTRL = 11;
	int POSTFIX_CTRL = 12;

	String[] border = { 
		"<EOF>", 
		"\" \"", 
		"\"\\t\"", 
		"\"\\n\"", 
		"\"\\r\"",
		"{#", 
		"#}", 
		"{%", 
		"%}", 
		"{{", 
		"}}", 
		"{!", 
		"!}" 
	};

}
