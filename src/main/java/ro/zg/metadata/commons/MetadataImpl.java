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
package ro.zg.metadata.commons;

import ro.zg.metadata.annotations.validation.Required;
import ro.zg.metadata.exceptions.ValidationException;


//@Persistable
public class MetadataImpl<T> implements Metadata<T>{
//    @ObjectId
    private String id;
    @Required
    private Class<T> type;
    private boolean complexType;
    protected boolean multivalued;
    
    
    
    public MetadataImpl() {
	super();
    }


    public MetadataImpl(Class<T> type, boolean complexType) {
	super();
	this.type = type;
	this.complexType = complexType;
    }


    public MetadataImpl(Class<T> type) {
	super();
	this.type = type;
    }
    
    
    /**
     * @return the multivalued
     */
    public boolean isMultivalued() {
        return multivalued;
    }


    /**
     * @return the type
     */
    public Class<T> getType() {
        return type;
    }


    /**
     * @return the complexType
     */
    public boolean isComplexType() {
        return complexType;
    }
    
    public String getTypeName() {
	return type.getName();
    }


    /**
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * @param type the type to set
     */
    public void setType(Class<T> type) {
        this.type = type;
    }


    /**
     * @param complexType the complexType to set
     */
    public void setComplexType(boolean complexType) {
        this.complexType = complexType;
    }


    /**
     * @param multivalued the multivalued to set
     */
    public void setMultivalued(boolean multivalued) {
        this.multivalued = multivalued;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Metadata [type=" + type + ", complexType=" + complexType + "]";
    }


   

    @Override
    public boolean validate(Object value) throws ValidationException{
	//TODO: this needs to check for validators
	return true;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (complexType ? 1231 : 1237);
	result = prime * result + (multivalued ? 1231 : 1237);
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	MetadataImpl<T> other = (MetadataImpl<T>) obj;
	if (complexType != other.complexType)
	    return false;
	if (multivalued != other.multivalued)
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }
    
    
    
}
