package ro.zg.metadata.mappers.validation;

import ro.zg.metadata.annotations.validation.Required;
import ro.zg.metadata.commons.AnnotationMapper;
import ro.zg.metadata.commons.FieldAnnotationMapperContext;
import ro.zg.metadata.commons.FieldMetadataImpl;
import ro.zg.metadata.commons.ObjectMetadataImpl;
import ro.zg.metadata.mappers.BaseAnnotationMappersManager;

public class SimpleFieldAnnotationMappersManager extends BaseAnnotationMappersManager<FieldAnnotationMapperContext<?,FieldMetadataImpl<?>,ObjectMetadataImpl<?, FieldMetadataImpl<?>>>>{

    public SimpleFieldAnnotationMappersManager() {
	addMapper(Required.class, (AnnotationMapper)new RequiredMapper());
    }

}
