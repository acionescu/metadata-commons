package ro.zg.metadata.commons;

import java.lang.annotation.Annotation;

public class FieldAnnotationMapperContext<A extends Annotation,F extends FieldMetadata<?>, P extends ObjectMetadata<?, F>> extends AnnotationProcessorContext<A,F>{
    private FieldAnnotationContext<P> fieldAnnotationContext;
    
    
    public FieldAnnotationMapperContext(A annotation, FieldAnnotationContext<P> fieldAnnotationContext) {
	super(annotation);
	this.fieldAnnotationContext = fieldAnnotationContext;
    }


    /**
     * @return the fieldAnnotationContext
     */
    public FieldAnnotationContext<P> getFieldAnnotationContext() {
        return fieldAnnotationContext;
    }

}
