package my.example.exception;

public abstract class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	protected BaseException(String code) {
		super(code);
	}

}
