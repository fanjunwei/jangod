package net.asfun.template.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import net.asfun.template.parse.ParserException;

public class TemplateLoader {

	public static String root;
	
	public static void setBase(String rootPath) {
		if ( ! rootPath.endsWith(File.separator) ) {
			root = rootPath + File.separator;
		} else {
			root = rootPath;
		}
	}
	
	public static boolean isSetup() {
		return root != null;
	}
	
	public static Reader getReader(String fileName) throws ParserException {
		if ( root == null ) {
			throw new ParserException("Set template root path before you start render");
		}
		try {
			return new FileReader(root + fileName);
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
	}
	
}
