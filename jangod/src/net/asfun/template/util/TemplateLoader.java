package net.asfun.template.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public class TemplateLoader {
	
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
	
	public Reader getReader(String fileName) throws IOException {
		return getReader(fileName, encoding);
	}
	
	public Reader getReader(String fileName, String encoding) throws IOException {
		if ( root != null ) {
			fileName = root + fileName;
		}
		try {
			return new InputStreamReader(new FileInputStream(fileName),encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IOException("Unsupported encoding >>> " + encoding);
		} catch (FileNotFoundException e) {
			throw new IOException(e.getMessage());
		}
	}
	
}
