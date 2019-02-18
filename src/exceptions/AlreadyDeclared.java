package exceptions;

public class AlreadyDeclared extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AlreadyDeclared(String str) {
		super(str);
	}
}
