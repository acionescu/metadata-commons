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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.builders.ObjectMetadataBuilder;

public class ClassAnnotationContext<O extends ObjectMetadata<?, ?>> {
    private ObjectMetadataBuilder<ObjectMetadataImpl<?,?>> objectMetadataBuilder;
    private O objectMetadata;
    private Map<String, FieldAnnotationContext> fieldAnnotationContexts=new HashMap<String, FieldAnnotationContext>();
    private Deque<Class<?>> superTypes=new ArrayDeque<Class<?>>();
    
    public ClassAnnotationContext(ObjectMetadataBuilder<ObjectMetadataImpl<?,?>> objectMetadataBuilder, O objectMetadata) {
	super();
	this.objectMetadataBuilder = objectMetadataBuilder;
	this.objectMetadata = objectMetadata;
    }

    /**
     * @return the superTypes
     */
    public Deque<Class<?>> getSuperTypes() {
        return superTypes;
    }

    /**
     * @return the objectMetadata
     */
    public O getObjectMetadata() {
        return objectMetadata;
    }
    
    
}
