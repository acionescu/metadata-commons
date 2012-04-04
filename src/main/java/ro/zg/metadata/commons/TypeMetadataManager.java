package ro.zg.metadata.commons;

import java.lang.reflect.Type;

import ro.zg.metadata.exceptions.MetadataException;

public interface TypeMetadataManager {

    <T extends Type, M extends Metadata<T>> M getMetadata(T type) throws MetadataException;
    
}
