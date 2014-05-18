package operator;

public class InvalidOperationException extends RuntimeException {

	private static final long serialVersionUID = -628540809921028868L;

	public InvalidOperationException(Throwable throwable) {
		super(throwable);
	}
}
