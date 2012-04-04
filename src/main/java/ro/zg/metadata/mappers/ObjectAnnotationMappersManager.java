package ro.zg.metadata.mappers;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.commons.AnnotationProcessorContext;
import ro.zg.metadata.commons.AnnotationMappersManager;
import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.MultitypeAnnotationMappersManager;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.exceptions.MetadataException;

public class ObjectAnnotationMappersManager implements AnnotationMappersManager<AnnotationProcessorContext<?, ?>> {
    private Map<ElementType, AnnotationMappersManager<AnnotationProcessorContext<?, ?>>> annotationMappersManagers=new HashMap<ElementType, AnnotationMappersManager<AnnotationProcessorContext<?,?>>>();

    public ObjectAnnotationMappersManager() {
	MultitypeAnnotationMappersManager fieldMappersManager = new FieldAnnotationMappersManager<FieldMetadata<?>, ObjectMetadata<?,FieldMetadata<?>>>();
	addMappersManager(ElementType.FIELD, fieldMappersManager);
    }

    @Override
    public void map(AnnotationProcessorContext<?, ?> amc) throws MetadataException {
	Class<? extends Annotation> annotationType = amc.getAnnotation().annotationType();
	ElementType et = annotationType.getAnnotation(Target.class).value()[0];
	AnnotationMappersManager<AnnotationProcessorContext<?, ?>> annotationMappersManager = annotationMappersManagers
		.get(et);
	if (annotationMappersManager != null) {
	    annotationMappersManager.map(amc);
	    return;
	}

	throw new RuntimeException("No mapper found for annotation " + annotationType + " element type " + et);
    }

    public void addMappersManager(ElementType et, AnnotationMappersManager<AnnotationProcessorContext<?, ?>> mappersManager) {
	annotationMappersManagers.put(et, mappersManager);
    }

    /**
     * @return the annotationMappersManagers
     */
    public Map<ElementType, AnnotationMappersManager<AnnotationProcessorContext<?, ?>>> getAnnotationMappersManagers() {
	return annotationMappersManagers;
    }

    /**
     * @param annotationMappersManagers
     *            the annotationMappersManagers to set
     */
    public void setAnnotationMappersManagers(
	    Map<ElementType, AnnotationMappersManager<AnnotationProcessorContext<?, ?>>> annotationMappersManagers) {
	this.annotationMappersManagers = annotationMappersManagers;
    }

}
