package ro.zg.metadata.builders;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import ro.zg.metadata.commons.ClassAnnotationContext;
import ro.zg.metadata.commons.ClassAnnotationMapperContext;
import ro.zg.metadata.commons.FieldAnnotationContext;
import ro.zg.metadata.commons.FieldAnnotationMapperContext;
import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.factories.MetadataFactory;
import ro.zg.metadata.mappers.ObjectAnnotationMappersManager;

public abstract class ObjectMetadataBuilder<O extends ObjectMetadata<?, ?>> extends
	AbstractMetadataBuilder<Class<?>, MultitypeMetadata<Class<?>, O>, MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>>> {
    private ObjectAnnotationMappersManager annotationMappersManager = new ObjectAnnotationMappersManager();

    

    public ObjectMetadataBuilder(
	    MetadataContextFactory<Class<?>, MultitypeMetadata<Class<?>, O>, MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>>> metadataContextFactory,
	    GenericMetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
	// TODO Auto-generated constructor stub
    }

    
    
    
    /* (non-Javadoc)
     * @see ro.zg.metadata.builders.AbstractMetadataBuilder#buildFromMetadataContext(ro.zg.metadata.commons.MetadataContext)
     */
    @Override
    protected void buildFromMetadataContext(
	    MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>> metadataContext)
	    throws MetadataException {
	/* process all class annotations and create a ClassAnnotationContext */
	ClassAnnotationContext<O> classAnnotationContext = buildClassAnnotationContext(type, metadata);
	/* process fields annotations */
	processClassFields(type, classAnnotationContext);
	/* process class annotations */
	processClassAnnotations(type, classAnnotationContext);
	
    }


    protected abstract void populateObjectMetadataFromSuperTypeMetadata(O objectMetadata,
	    ObjectMetadata<?, ?> superTypeMetadata);

    private ClassAnnotationContext<O> buildClassAnnotationContext(Class<?> type, O objectMetadata)
	    throws MetadataException {
	Class<?> superclass = type.getSuperclass();
	if (superclass != null) {
	    ObjectMetadata<?, ?> superTypeMetadata = (ObjectMetadata<?, ?>) getGenericTypeMetadata(superclass);
	    populateObjectMetadataFromSuperTypeMetadata(objectMetadata, superTypeMetadata);
	}

	ClassAnnotationContext<O> classAnnotationContext = new ClassAnnotationContext(this, objectMetadata);

	return classAnnotationContext;
    }

    private void processClassFields(Class<?> clazz, ClassAnnotationContext<O> cac) throws MetadataException {
	for (Field f : clazz.getDeclaredFields()) {
	    processFieldAnnotations(f, cac);
	}
    }

    private void processFieldAnnotations(Field field, ClassAnnotationContext<O> cac) throws MetadataException {
	Metadata<?> fieldTypeMetadata = getGenericTypeMetadata(field.getGenericType());
	FieldAnnotationContext<O> fieldAnnotationContext = new FieldAnnotationContext<O>(cac, fieldTypeMetadata,field);

	for (Annotation a : field.getAnnotations()) {
	    FieldAnnotationMapperContext<Annotation, ?, O> fieldAnnotationMapperContext = new FieldAnnotationMapperContext(
		    a, fieldAnnotationContext);
	    annotationMappersManager.map(fieldAnnotationMapperContext);
	}
	
	ObjectMetadata objectMetadata = cac.getObjectMetadata();
	
	for(FieldMetadata<?> fm : fieldAnnotationContext.getFieldMetadatas().values()) {
	    objectMetadata.acceptField(fm); 
	}
	
    }
    
    

    private void processClassAnnotations(Class<?> clazz, ClassAnnotationContext<O> cac) throws MetadataException {
	for (Annotation a : clazz.getAnnotations()) {
	    ClassAnnotationMapperContext<Annotation, O> classAnnotationMapperContext = new ClassAnnotationMapperContext<Annotation, O>(
		    a, cac);
	    annotationMappersManager.map(classAnnotationMapperContext);
	}
    }
}
