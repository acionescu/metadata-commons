package ro.zg.metadata.commons;

import java.lang.annotation.Annotation;

public class ClassAnnotationMapperContext<A extends Annotation,M extends ObjectMetadata<?,?>> extends AnnotationProcessorContext<A,M>{
    private ClassAnnotationContext<M> classAnnotationContext;

    public ClassAnnotationMapperContext(A annotation, ClassAnnotationContext<M> classAnnotationContext) {
	super(annotation);
	this.classAnnotationContext = classAnnotationContext;
    }

    /**
     * @return the classAnnotationContext
     */
    public ClassAnnotationContext<M> getClassAnnotationContext() {
        return classAnnotationContext;
    }
    
   
   
    
    
}
