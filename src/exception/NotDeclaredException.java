package exception;

public class NotDeclaredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NotDeclaredException(String mess) {
		System.err.println(mess);
		System.exit(1);
	}
}
