package ro.zg.metadata.builders;

import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.exceptions.MetadataException;

public interface MetadataBuilder<T, M extends Metadata<?>> {
    
     M buildMetadata(T input) throws MetadataException;
}
