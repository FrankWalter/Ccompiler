package env;

import addr.Temp;
import translate.Level;
import type.Type;

public class VarEntry implements Entry {
	public Type type;
	public Temp varAddr = null;
	public Level level = null;
	
	public VarEntry(Type t) {
		type = t;
	}

	public VarEntry(Type t, Temp va) {
		type = t;
		varAddr = va;
	}
	
	public VarEntry(Type t, Level l) {
		type = t;
		level = l;
	}
}
