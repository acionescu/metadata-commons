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
