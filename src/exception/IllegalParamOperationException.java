package exception;

public class IllegalParamOperationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IllegalParamOperationException(String mess) {
		System.err.println(mess);
		System.exit(1);
	}
	

}
