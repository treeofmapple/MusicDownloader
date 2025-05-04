package javatube.exceptions;

import javatube.exceptions.global.GlobalRuntimeException;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class DownloadFailedException extends GlobalRuntimeException {

	public DownloadFailedException(String msg) {
		super(msg);
	}

	public DownloadFailedException(Throwable cause) {
		super(cause);
	}
	
	public DownloadFailedException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
