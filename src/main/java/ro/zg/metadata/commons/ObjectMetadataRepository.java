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

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.exceptions.MetadataErrorType;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.util.data.GenericNameValue;
import ro.zg.util.hash.HashUtil;

public class ObjectMetadataRepository {
    private Map<Class<?>, Metadata<?>> objectsModels = new HashMap<Class<?>, Metadata<?>>();
    private Map<String, MultivaluedTypeMetadata<?, ?>> multivaluedDataModels = new HashMap<String, MultivaluedTypeMetadata<?,?>>();
    
    public void addDataModels(Collection<Metadata<?>> dmc) {
	for (Metadata<?> dm : dmc) {
	    addDataModel(dm);
	}
    }

    public void addDataModel(Metadata<?> odm) {
	if (odm.isMultivalued()) {
	    MultivaluedTypeMetadata<?, ?> mvdm = (MultivaluedTypeMetadata<?, ?>) odm;
	    multivaluedDataModels.put(mvdm.getParameteriezedTypeHash(), mvdm);
	} else {
	    objectsModels.put(odm.getType(), odm);
	}
    }

    public Metadata<?> getDataModel(Type type) throws MetadataException {
	if(type instanceof Class) {
	    return objectsModels.get((Class<?>)type);
	}
	else if(type instanceof ParameterizedType || type instanceof GenericArrayType) {
	    return multivaluedDataModels.get(getHashForType(type));
	}
	throw new MetadataException(MetadataErrorType.UNKNOWN_OBJECT_TYPE, new GenericNameValue("type",type));
    }
    
    public <T> Metadata<T> getDataModelByClass(Class<T> clazz){
	return (Metadata<T>)objectsModels.get(clazz);
    }
    
    public Metadata getMultivaluedDataModel(Type pt) {
	return multivaluedDataModels.get(getHashForType(pt));
    }
    
    private String getHashForType(Type type) {
	return HashUtil.digestSHA1(type.toString());
    }

    
}
