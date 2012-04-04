package ro.zg.metadata.exceptions;

import java.util.Arrays;

import ro.zg.util.data.GenericNameValue;


public class MetadataException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5174434897693516481L;
    
    
    private MetadataErrorType errorType;
    private GenericNameValue[] params;
    
    
    public MetadataException(MetadataErrorType errorType, GenericNameValue... params) {
	super();
	this.errorType = errorType;
	this.params = params;
    }


    public MetadataException(Throwable cause, MetadataErrorType errorType, GenericNameValue... params) {
	super(cause);
	this.errorType = errorType;
	this.params = params;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "MetadataException [errorType=" + errorType + ", params=" + Arrays.toString(params) + "]";
    }
    
    
    
    
}
