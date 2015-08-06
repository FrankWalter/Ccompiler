package absyn;

import symbol.Symbol;

public class StructTy extends Ty {
	public static String label = "StructTy";
	public StructDecls structdecls;
	private static int count = 0;
	
	public StructTy(StructDecls sds) {
		count++;
		TySymbol = Symbol.getSymbol("struct !!"+count);
		structdecls = sds;
	}
	
	public StructTy(symbol.Symbol sym, StructDecls sds) {
		TySymbol = Symbol.getSymbol("struct " + sym.toString());
		structdecls = sds;
	}
	
	
}
