package ro.zg.metadata.commons;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.annotations.AnnotationType;
import ro.zg.metadata.exceptions.MetadataException;

public abstract class MultitypeAnnotationMappersManager<C extends AnnotationProcessorContext<?, ?>> implements
	AnnotationMappersManager<C> {
    private Map<String, AnnotationMappersManager<AnnotationProcessorContext<?, ?>>> annotationMappersManagers = new HashMap<String, AnnotationMappersManager<AnnotationProcessorContext<?, ?>>>();
    protected Map<String,String> typeDependencies=new HashMap<String, String>();
    
    
    @Override
    public void map(C amc) throws MetadataException {
	Class<? extends Annotation> annotationType = amc.getAnnotation().annotationType();
	String mt = annotationType.getAnnotation(AnnotationType.class).value();
	AnnotationMappersManager<AnnotationProcessorContext<?, ?>> annotationMappersManager = annotationMappersManagers
		.get(mt);

	if (annotationMappersManager != null) {
	    prepareContext(amc, mt);
	    annotationMappersManager.map(amc);
	}
    }

    protected abstract void prepareContext(C amc, String type);

    public void addMapper(String type, AnnotationMappersManager<? extends AnnotationProcessorContext<?, ?>> mappersManager) {
	annotationMappersManagers.put(type, (AnnotationMappersManager) mappersManager);
    }

    protected String getTypeDependency(String type) {
	return typeDependencies.get(type);
    }
}
