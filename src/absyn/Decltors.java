package absyn;

import java.util.LinkedList;

public class Decltors {
	public String label = "Decltors";
	public LinkedList<Decltor> list = new LinkedList<Decltor>();
	
	public Decltors(Decltor d) {
		list.addFirst(d);
	}
}
