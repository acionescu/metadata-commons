package ro.zg.metadata.factories;

import ro.zg.metadata.commons.FieldMetadataImpl;
import ro.zg.metadata.commons.Metadata;

public class SimpleFieldMetadataFactory<T> implements MetadataDecoratorFactory<T,Metadata<T>, FieldMetadataImpl<T>>{

    @Override
    public FieldMetadataImpl<T> createMetadata(Metadata<T> nestedMetadata) {
	return new FieldMetadataImpl<T>(nestedMetadata);
    }

  
}
