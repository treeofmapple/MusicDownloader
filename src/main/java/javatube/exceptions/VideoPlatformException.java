package javatube.exceptions;

import javatube.exceptions.global.ErrorType;
import javatube.exceptions.global.GlobalException;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class VideoPlatformException extends GlobalException {
    private final ErrorType type;
	
    public VideoPlatformException(ErrorType type, String message) {
        super(message);
        this.type = type;
    }
    
    public VideoPlatformException(ErrorType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
    
    public ErrorType getType() {
        return type;
    }
	
}