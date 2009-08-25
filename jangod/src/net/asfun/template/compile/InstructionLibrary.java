package net.asfun.template.compile;

import net.asfun.template.inst.*;

public class InstructionLibrary extends SimpleLibrary<Instruction>{

	private static InstructionLibrary lib;
	
	static {
		lib = new InstructionLibrary();
	}
	
	@Override
	protected void initialize() {
		Instruction set = new SetInst();
		register(set.getName(), set);
	}
	
	public static Instruction getInstruction(String name) throws CompilerException {
		return lib.fetch(name);
	}
	
	public static void addInstruction(Instruction inst) {
		lib.register(inst.getName(), inst);
	}

}
