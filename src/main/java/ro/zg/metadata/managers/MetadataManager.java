package ro.zg.metadata.managers;

import ro.zg.metadata.commons.Metadata;

public interface MetadataManager<T,M extends Metadata<?>> {

    M getMetadata(T input);
    
}
