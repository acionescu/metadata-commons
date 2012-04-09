/*******************************************************************************
 * Copyright 2012 AdrianIonescu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
