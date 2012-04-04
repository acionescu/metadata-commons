package ro.zg.metadata.factories;

import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;

public class MultitypeMetadataContextFactory<T,M extends MultitypeMetadata<T,?>> implements MetadataContextFactory<T, M, MultitypeMetadataContext<T,M>>{

    @Override
    public MultitypeMetadataContext<T, M> createMetadataContext(T source) {
	return new MultitypeMetadataContext<T, M>(source);
    }

}
