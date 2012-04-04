package ro.zg.metadata.factories;

import java.lang.reflect.Field;

import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.Metadata;

public interface FieldMetadataFactory<M extends FieldMetadata<?>> {
    
    M createFieldMetadata();

    <T> M createFieldMetadata(Metadata<T> valueTypeMetadata);
    
    <T> M createFieldMetadata(Field field, Metadata<T> valueTypeMetadata);
}
