package net.asfun.template.filter;

import net.asfun.template.compile.CompilerException;

public interface Filter {

	Object filter(Object object, String... arg) throws CompilerException;
}
