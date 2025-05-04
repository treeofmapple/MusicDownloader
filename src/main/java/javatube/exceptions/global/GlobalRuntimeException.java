package javatube.exceptions.global;

@SuppressWarnings("serial")
public abstract class GlobalRuntimeException extends RuntimeException {

	public GlobalRuntimeException(String msg) {
		super(msg);
	}
	
	public GlobalRuntimeException(Throwable cause) {
		super(cause);
	}
	
	public GlobalRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
