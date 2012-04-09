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
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.builders.ArrayMetadataBuilder;
import ro.zg.metadata.builders.DefaultObjectMetadataBuilder;
import ro.zg.metadata.builders.MetadataBuilder;
import ro.zg.metadata.builders.ParameterizedTypeMetadataBuilder;
import ro.zg.metadata.exceptions.MetadataErrorType;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.managers.GenericObjectMetadataManager;
import ro.zg.metadata.managers.ObjectMetadataManager;
import ro.zg.util.data.GenericNameValue;
import ro.zg.util.data.reflection.ReflectionUtility;

public class MetadataBuildersManager implements MetadataBuilder<Type, Metadata<?>> {
    private Map<Class<?>, MetadataBuilder<Type, Metadata<?>>> metadataBuilders = new HashMap<Class<?>, MetadataBuilder<Type, Metadata<?>>>();

    public MetadataBuildersManager(GenericObjectMetadataManager metadataManager) {
	addMetadataBuilder(Class.class, new DefaultObjectMetadataBuilder(metadataManager));
	addMetadataBuilder(GenericArrayType.class, new ArrayMetadataBuilder(metadataManager));
	addMetadataBuilder(ParameterizedType.class, new ParameterizedTypeMetadataBuilder(
		(ObjectMetadataManager) metadataManager));
    }

    @Override
    public Metadata<?> buildMetadata(Type type) throws MetadataException {
	MetadataBuilder<Type, Metadata<?>> metadataBuilder = null;
	if (type instanceof Class) {
	    Class<?> clazz = (Class<?>)type;
	  
	    if (ReflectionUtility.checkSimpleFieldType(clazz) || Class.class.isAssignableFrom(clazz)) {
		return new MetadataImpl(clazz);
	    }

	    metadataBuilder = metadataBuilders.get(Class.class);
	} else if (type instanceof ParameterizedType) {
	    metadataBuilder = metadataBuilders.get(ParameterizedType.class);
	} else if (type instanceof GenericArrayType) {
	    metadataBuilder = metadataBuilders.get(GenericArrayType.class);
	} else {
	    throw new MetadataException(MetadataErrorType.UNKNOWN_OBJECT_TYPE, new GenericNameValue("type",
		    type.getClass()));
	}

	if (metadataBuilder != null) {
	    return metadataBuilder.buildMetadata(type);
	}
	throw new MetadataException(MetadataErrorType.UNKNOWN_OBJECT_TYPE,
		new GenericNameValue("type", type.getClass()));
    }

    protected <T extends Type, M extends Metadata<?>> void addMetadataBuilder(Class<?> clazz,
	    MetadataBuilder<T, M> metadataBuilder) {
	metadataBuilders.put(clazz, (MetadataBuilder) metadataBuilder);
    }

}
