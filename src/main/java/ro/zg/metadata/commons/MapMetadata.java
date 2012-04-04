/*******************************************************************************
 * Copyright 2011 Adrian Cristian Ionescu
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
import java.util.Map;

//@Persistable
public class MapMetadata<K, T> extends MultivaluedTypeMetadata<Map<K, T>, T> {
    private Class<K> keyType;

    public MapMetadata(ParameterizedType pt) {
	super(pt);
	Type[] typeArguments = pt.getActualTypeArguments();
	if (typeArguments.length < 2) {
	    throw new IllegalArgumentException("Expecting two generic type arguments but there were less");
	}
	Class<K> keyType = (Class<K>) getRawType(typeArguments[0]);
	Class<T> valueType = (Class<T>) getRawType(typeArguments[1]);

	init(keyType, valueType, (Class<? extends Map<K, T>>) pt.getRawType());
    }

    protected void init(Class<K> keyType, Class<T> valueType, Class<? extends Map<K, T>> multivaluedType) {
	super.init(valueType, multivaluedType);
	this.keyType = keyType;
	this.map = true;
    }

    /**
     * @return the keyType
     */
    public Class<K> getKeyType() {
	return keyType;
    }

}
