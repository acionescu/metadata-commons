package ro.zg.metadata.factories;

import java.lang.reflect.GenericArrayType;

import ro.zg.metadata.commons.ArrayMetadata;

public class ArrayMetadataFactory implements MetadataFactory<GenericArrayType, ArrayMetadata<?>>{

    @Override
    public ArrayMetadata<?> createMetadata(GenericArrayType type) {
	return new ArrayMetadata(type);
    }

}
