package ro.zg.metadata.factories;

import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataDecorator;

public interface MetadataDecoratorFactory<T,M extends Metadata<T>,D extends MetadataDecorator<T, M>> {

    D createMetadata(M nestedMetadata);
}
