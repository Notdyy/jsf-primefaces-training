package my.example.exception;

public class AgeUnderLimitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgeUnderLimitException(String message) {
		super(message);
	}
}
