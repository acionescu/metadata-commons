package ro.zg.metadata.exceptions;

import ro.zg.util.data.GenericNameValue;

public class ValidationException extends Exception{

    /**
     * 
     */
    private static final long serialVersionUID = -2759556836329161048L;

    private ValidationErrorType errorType;
    private GenericNameValue[] params;
    
    public ValidationException(ValidationErrorType errorType, GenericNameValue... params) {
	super();
	this.errorType = errorType;
	this.params = params;
    }

    public ValidationException(Throwable cause, ValidationErrorType errorType, GenericNameValue... params) {
	super(cause);
	this.errorType = errorType;
	this.params = params;
    }
    
    
    
}
