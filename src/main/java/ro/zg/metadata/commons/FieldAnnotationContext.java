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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FieldAnnotationContext<O extends ObjectMetadata<?, ?>> {
    private ClassAnnotationContext<O> classAnnotationContext;
    private Metadata<?> fieldTypeMetadata;
    private Field field;
    private Deque<Annotation> pendingAnnotations = new ArrayDeque<Annotation>(); 
    private Map<String, ? extends FieldMetadataImpl<?>> fieldMetadatas = new HashMap<String, FieldMetadataImpl<?>>();
    
    
    public FieldAnnotationContext(ClassAnnotationContext<O> classAnnotationContext, Metadata<?> fieldTypeMetadata,
	    Field field) {
	super();
	this.classAnnotationContext = classAnnotationContext;
	this.fieldTypeMetadata = fieldTypeMetadata;
	this.field = field;
    }


    /**
     * @return the classAnnotationContext
     */
    public ClassAnnotationContext<O> getClassAnnotationContext() {
        return classAnnotationContext;
    }


    /**
     * @return the fieldTypeMetadata
     */
    public Metadata<?> getFieldTypeMetadata() {
        return fieldTypeMetadata;
    }


    /**
     * @return the pendingAnnotations
     */
    public Deque<Annotation> getPendingAnnotations() {
        return pendingAnnotations;
    }


    /**
     * @return the fieldMetadatas
     */
    public Map<String, ? extends FieldMetadataImpl<?>> getFieldMetadatas() {
        return fieldMetadatas;
    }


    /**
     * @param classAnnotationContext the classAnnotationContext to set
     */
    public void setClassAnnotationContext(ClassAnnotationContext<O> classAnnotationContext) {
        this.classAnnotationContext = classAnnotationContext;
    }


    /**
     * @param fieldTypeMetadata the fieldTypeMetadata to set
     */
    public void setFieldTypeMetadata(Metadata<?> fieldTypeMetadata) {
        this.fieldTypeMetadata = fieldTypeMetadata;
    }


    /**
     * @param pendingAnnotations the pendingAnnotations to set
     */
    public void setPendingAnnotations(Deque<Annotation> pendingAnnotations) {
        this.pendingAnnotations = pendingAnnotations;
    }


    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }


    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }
    
    
    
}
