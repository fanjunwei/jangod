package net.asfun.template.compile;

import net.asfun.template.inst.SetInst;

public class InstructionLibrary extends SimpleLibrary<Instruction>{

	private static InstructionLibrary lib;
	
	static {
		lib = new InstructionLibrary();
	}
	
	@Override
	protected void initialize() {
		Instruction set = new SetInst();
		lib.register(set.getInstName(), set);
	}
	
	public static Instruction getInstruction(String name) throws CompilerException {
		return lib.fetch(name);
	}

}
