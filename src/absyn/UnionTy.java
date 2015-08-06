package absyn;

import symbol.Symbol;

public class UnionTy extends Ty {
	public static String label = "UnionTy";
	public StructDecls structdecls;
	private static int count = 0;
	
	public UnionTy(StructDecls sds) {
		count++;
		TySymbol = Symbol.getSymbol("struct !!"+count);
		structdecls = sds;
	}
	
	public UnionTy(symbol.Symbol sym, StructDecls sds) {
		TySymbol = Symbol.getSymbol("struct " + sym.toString());
		structdecls = sds;
	}
}
