package net.asfun.template.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.asfun.template.parse.ParserException;
import net.asfun.template.parse.Token;
import net.asfun.template.parse.TokenManager;

public class ParserTest {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fr = new FileReader(new File("D:/workspace/jvalog/war/themes/default/index.html"));
			int c;
			while( (c = fr.read()) != -1) {
				sb.append((char)c);
			}
			TokenManager.init(sb.toString());
			Token tk = TokenManager.getNextToken();
			while ( tk != null ) {
				System.out.println(tk);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				tk = TokenManager.getNextToken();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
