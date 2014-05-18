package operator;

public class InsufficientParametersException extends Exception {

	private static final long serialVersionUID = 3015222592080021214L;

	public InsufficientParametersException(String message) {
		super(message);
	}

	public InsufficientParametersException(Throwable throwable) {
		super(throwable);
	}
}
