package type;

public final class VOID extends Type {
	public boolean eq(Type t) {
		return t.actual() instanceof VOID;
	}
}
