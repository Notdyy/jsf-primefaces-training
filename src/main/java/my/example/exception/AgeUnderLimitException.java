package my.example.exception;

public class AgeUnderLimitException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgeUnderLimitException(String code) {
		super(code);
	}
}
