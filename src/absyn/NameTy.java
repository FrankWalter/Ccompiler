package absyn;

public class NameTy extends Ty {
	public String label = "NameTy";
	
	public NameTy(symbol.Symbol sym) {
		TySymbol = sym;
	}
}
