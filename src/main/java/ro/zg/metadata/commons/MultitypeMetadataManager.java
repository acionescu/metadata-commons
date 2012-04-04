package ro.zg.metadata.commons;

import ro.zg.metadata.managers.MetadataManager;

public interface MultitypeMetadataManager<T,M extends MultitypeMetadata<?>> extends MetadataManager<T,M>{
    
    <N extends Metadata<?>> N getMetadata(T input, String type);
}
