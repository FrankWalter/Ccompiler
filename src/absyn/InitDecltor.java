package absyn;

//import addr.Temp;

public class InitDecltor {
	public String label = "InitDecltor";
	public Decltor decltor;
	public Initer initer;
//	public Temp varAddr;

	public InitDecltor(Decltor dtor, Initer iter) {
		decltor = dtor;
		initer = iter;
	}
}
