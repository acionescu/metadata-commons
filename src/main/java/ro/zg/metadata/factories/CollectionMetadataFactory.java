package ro.zg.metadata.factories;

import java.lang.reflect.ParameterizedType;

import ro.zg.metadata.commons.CollectionMetadata;

public class CollectionMetadataFactory implements MetadataFactory<ParameterizedType, CollectionMetadata<?>>{

    @Override
    public CollectionMetadata<?> createMetadata(ParameterizedType type) {
	return new CollectionMetadata(type);
    }

}
