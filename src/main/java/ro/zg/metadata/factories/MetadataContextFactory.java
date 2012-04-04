package ro.zg.metadata.factories;

import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataContext;

public interface MetadataContextFactory<T,M extends Metadata<?>,C extends MetadataContext<T,M>> {

    C createMetadataContext(T source);
}
