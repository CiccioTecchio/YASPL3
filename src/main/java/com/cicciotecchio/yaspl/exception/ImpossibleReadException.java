package exception;

public class ImpossibleReadException extends RuntimeException {

	public ImpossibleReadException(String id) {
		System.err.println(id);
	}
}
