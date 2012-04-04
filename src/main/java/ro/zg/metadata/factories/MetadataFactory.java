package ro.zg.metadata.factories;

import ro.zg.metadata.commons.Metadata;

public interface MetadataFactory<T, M extends Metadata<?>> {

     M createMetadata(T type);

}
