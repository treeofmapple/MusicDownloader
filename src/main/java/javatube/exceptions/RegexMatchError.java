package javatube.exceptions;

import javatube.exceptions.global.GlobalException;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class RegexMatchError extends GlobalException {
    
	public RegexMatchError(String msg) {
        super(msg);
    }
	
	public RegexMatchError(String msg, Throwable cause) {
        super(msg, cause);
    }
	
}