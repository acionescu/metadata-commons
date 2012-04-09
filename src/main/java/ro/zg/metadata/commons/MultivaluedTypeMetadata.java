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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import ro.zg.util.hash.HashUtil;


//@Persistable
public class MultivaluedTypeMetadata<M,T> extends MetadataImpl<T>{
    private String parameteriezedTypeHash;
    protected Class<? extends M> multivaluedType;
    protected boolean collection;
    protected boolean map;
    protected boolean array;

    
    public MultivaluedTypeMetadata(Type type) {
	parameteriezedTypeHash=HashUtil.digestSHA1(type.toString());
    }
    
    
    protected void init(Class<T> type, Class<? extends M> multivaluedType) {
	setType(type);
	setComplexType(true);
	this.multivalued=true;
	this.multivaluedType = multivaluedType;
    }
    
    
    protected Class<?> getRawType(Type type) {
	if (type instanceof Class) {
	    return (Class<?>) type;
	} else if (type instanceof ParameterizedType) {
	    return (Class<?>) ((ParameterizedType) type).getRawType();
	} 
	throw new IllegalArgumentException("Unknown type " + type);
    }
    

    /**
     * @return the collection
     */
    public boolean isCollection() {
        return collection;
    }

    /**
     * @return the map
     */
    public boolean isMap() {
        return map;
    }

    /**
     * @param collection the collection to set
     */
    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    /**
     * @param map the map to set
     */
    public void setMap(boolean map) {
        this.map = map;
    }

    /**
     * @return the multivaluedType
     */
    public Class<? extends M> getMultivaluedType() {
        return multivaluedType;
    }

    /**
     * @return the parameteriezedTypeHash
     */
    public String getParameteriezedTypeHash() {
        return parameteriezedTypeHash;
    }


    /**
     * @return the array
     */
    public boolean isArray() {
        return array;
    }


    /**
     * @param array the array to set
     */
    public void setArray(boolean array) {
        this.array = array;
    }
    
}
