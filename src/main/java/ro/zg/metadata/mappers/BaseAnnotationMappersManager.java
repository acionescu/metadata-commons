package ro.zg.metadata.mappers;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ro.zg.metadata.commons.AnnotationMapper;
import ro.zg.metadata.commons.AnnotationProcessorContext;
import ro.zg.metadata.commons.AnnotationMappersManager;
import ro.zg.metadata.exceptions.MetadataException;

public class BaseAnnotationMappersManager<C extends AnnotationProcessorContext<?,?>> implements AnnotationMappersManager<C>{
    protected Map<Class<? extends Annotation>,AnnotationMapper<C>> mappers=new HashMap<Class<? extends Annotation>, AnnotationMapper<C>>();
    
    public void map(C amc) throws MetadataException {
	AnnotationMapper<C> mapper = mappers.get(amc.getAnnotation().annotationType());
	if(mapper!=null) {
	    mapper.map(amc);
	}
	else {
	    System.out.println("No mapper for "+amc);
	}
    }
    
    public Set<Class<? extends Annotation>> getKnownAnnotations(){
	return mappers.keySet();
    }
    
    protected void addMapper(Class<? extends Annotation> a, AnnotationMapper<C> mapper) {
	mappers.put(a, mapper);
    }
}
