package javatube.exceptions;

import javatube.exceptions.global.GlobalException;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class RegexMatchException extends GlobalException {
    
	public RegexMatchException(String msg) {
        super(msg);
    }
	
	public RegexMatchException(String msg, Throwable cause) {
        super(msg, cause);
    }
	
}