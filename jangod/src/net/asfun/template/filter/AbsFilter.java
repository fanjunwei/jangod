package net.asfun.template.filter;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.asfun.template.compile.CompilerException;
import net.asfun.template.compile.Filter;

public class AbsFilter implements Filter{

	@Override
	public Object filter(Object object, String... arg) throws CompilerException {
		if ( object instanceof Integer ) {
			return Math.abs((Integer)object);
		}
		if ( object instanceof Float ) {
			return Math.abs((Float)object);
		}
		if ( object instanceof Long ) {
			return Math.abs((Long)object);
		}
		if ( object instanceof Short ) {
			return Math.abs((Short)object);
		}
		if ( object instanceof Double ) {
			return Math.abs((Double)object);
		}
		if ( object instanceof BigDecimal ) {
			return ((BigDecimal)object).abs();
		}
		if ( object instanceof BigInteger ) {
			return ((BigInteger)object).abs();
		}
		if ( object instanceof Byte ) {
			return Math.abs((Byte)object);
		}
		if ( object instanceof String ) {
			try {
				return new BigDecimal(object.toString()).abs();
			} catch (Exception e) {
				throw new CompilerException(object + " can't be dealed with abs filter");
			}
		}
		return object;
	}

	@Override
	public String getName() {
		return "abc";
	}

}
