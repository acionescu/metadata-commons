package ro.zg.metadata.mappers.validation;

import ro.zg.metadata.annotations.validation.Required;
import ro.zg.metadata.commons.AnnotationMapper;
import ro.zg.metadata.commons.AnnotationProcessorContext;
import ro.zg.metadata.commons.FieldMetadataImpl;
import ro.zg.metadata.exceptions.MetadataException;

public class RequiredMapper implements AnnotationMapper<AnnotationProcessorContext<Required, FieldMetadataImpl<?>>>{

    @Override
    public void map(AnnotationProcessorContext<Required, FieldMetadataImpl<?>> amc) throws MetadataException {
	amc.getMetadata().setRequired(true);	
    }

    

}
