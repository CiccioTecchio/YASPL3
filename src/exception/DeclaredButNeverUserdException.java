package exception;

public class DeclaredButNeverUserdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DeclaredButNeverUserdException(String mess) {
		System.err.print(mess);
	}
}
