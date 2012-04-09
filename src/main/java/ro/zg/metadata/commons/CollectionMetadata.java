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
import java.util.Collection;
import java.util.List;
import java.util.Set;

import ro.zg.commons.exceptions.ContextAwareException;
import ro.zg.commons.exceptions.ExceptionContext;
import ro.zg.util.data.GenericNameValue;
import ro.zg.util.data.reflection.ReflectionUtility;

//@Persistable
public class CollectionMetadata<T> extends MultivaluedTypeMetadata<Collection<T>, T> {
    private boolean set;
    private boolean list;

    public CollectionMetadata(ParameterizedType parameterizedType) {
	super(parameterizedType);
	Type[] typeArguments = parameterizedType.getActualTypeArguments();
	if (typeArguments.length == 0) {
	    throw new IllegalArgumentException("Expecting one generic type argument but there was none");
	}

	Class<T> nestedType = (Class<T>) getRawType(typeArguments[0]);

	init(nestedType, (Class<? extends Collection<T>>) parameterizedType.getRawType());
    }

    protected void init(Class<T> type, Class<? extends Collection<T>> multivaluedType) {
	super.init(type, multivaluedType);
	this.collection = true;
	initCollectionType();
    }

    private void initCollectionType() {
	if (checkSuperType(List.class)) {
	    list = true;
	} else if (checkSuperType(Set.class)) {
	    set = true;
	} else {
	    throw new IllegalArgumentException("Unknown collection " + multivaluedType.getName());
	}

    }

    private boolean checkSuperType(Class<?> superClass) {
	return ReflectionUtility.checkInstanceOf(multivaluedType, superClass);
    }

    /**
     * @return the set
     */
    public boolean isSet() {
	return set;
    }

    /**
     * @return the list
     */
    public boolean isList() {
	return list;
    }

    

    public Collection<T> fromString(Collection<String> input) throws ContextAwareException {
	try {
	    Collection<T> container = multivaluedType.newInstance();
	    for (String item : input) {
		container.add((T) ReflectionUtility.createObjectByTypeAndValue(getType(), item));
	    }
	    return container;

	} catch (Exception e) {
	    ExceptionContext ec = new ExceptionContext();
	    ec.put(new GenericNameValue("input", input));
	    ec.put(new GenericNameValue("collectionType", multivaluedType));
	    throw new ContextAwareException("CONVERSION_EXCEPTION", e, ec);
	}
    }

}
