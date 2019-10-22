package exception;

public class AlreadyDeclaredException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public AlreadyDeclaredException(String mess) {
		System.err.println(mess);
		System.exit(1);
	}
		
}
