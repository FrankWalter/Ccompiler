package absyn;

public class Var extends Expr {
	public String label = "Var";
	public symbol.Symbol symbol;
	
	public Var(symbol.Symbol sym) {
		symbol = sym;
	}
}
