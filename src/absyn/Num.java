package absyn;

public class Num extends Expr {
	public String label = "Num";
	public int value;
	
	public Num(int val) {
		value = val;
	}
}
