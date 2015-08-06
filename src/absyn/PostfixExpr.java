package absyn;

public class PostfixExpr extends Expr {
	public String label = "PostfixExpr";
	public Expr pfe;
	public symbol.Symbol symbol;
	public PfType pt;
	
	public PostfixExpr(Expr e, symbol.Symbol s, PfType t) {
		pfe = e;
		symbol = s;
		pt = t;
	}
	
	public static enum PfType { DOT, PTR }
}
