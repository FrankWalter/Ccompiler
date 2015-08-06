package type;

import symbol.Symbol;

public final class NAME extends Type {
	public Symbol name;
	public int level;
	
	public NAME(Symbol n, int l) {
		name = n;
		level = l;
	}
}
