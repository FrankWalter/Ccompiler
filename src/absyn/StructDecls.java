package absyn;

import java.util.LinkedList;

public class StructDecls {
	public class StructDecl {
		public Ty type;
		public Decltors decltors;
		
		public StructDecl(Ty t, Decltors d) {
			type = t;
			decltors = d;
		}
	}
	
	public LinkedList<StructDecl> list = new LinkedList<StructDecl>();
	
	public StructDecls(Ty t, Decltors d) {
		list.addFirst(new StructDecl(t, d));
	}
	
	public void addLast(Ty t, Decltors d) {
		list.addLast(new StructDecl(t, d));
	}
}
