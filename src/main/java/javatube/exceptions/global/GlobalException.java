package javatube.exceptions.global;

@SuppressWarnings("serial")
public abstract class GlobalException extends Exception {

	public GlobalException(String msg) {
		super(msg);
	}

	public GlobalException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
