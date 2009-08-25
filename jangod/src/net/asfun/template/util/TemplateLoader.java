package net.asfun.template.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import net.asfun.template.parse.ParserException;

public class TemplateLoader {
	
	public static final String ROOT_KEY = "'TPL\"ROOT,DIR";
	private String encoding = "utf-8";
	private String root = null;
	
	public void setBase(String rootPath) {
		if ( rootPath == null) return;
		if ( ! rootPath.endsWith(File.separator) ) {
			root = rootPath + File.separator;
		} else {
			root = rootPath;
		}
	}
	
	public void setEncoding(String enc) {
		encoding = enc;
	}
	
	public Reader getReader(String fileName) throws ParserException {
		return getReader(fileName, encoding);
	}
	
	public Reader getReader(String fileName, String encoding) throws ParserException {
		if ( root != null ) {
			fileName = root + fileName;
		}
		try {
			return new InputStreamReader(new FileInputStream(fileName),encoding);
		} catch (UnsupportedEncodingException e) {
			throw new ParserException("Unsupported encoding >>> " + encoding);
		} catch (FileNotFoundException e) {
			throw new ParserException(e.getMessage());
		}
	}
	
}
