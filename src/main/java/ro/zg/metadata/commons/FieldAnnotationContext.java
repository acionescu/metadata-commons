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
