package ro.zg.metadata.factories;

import ro.zg.metadata.commons.ObjectMetadataImpl;

public class ObjectMetadataFactory implements
	MetadataFactory<Class<?>, ObjectMetadataImpl<?, ?>> {

    @Override
    public ObjectMetadataImpl<?, ?> createMetadata(Class<?> type) {
	return new ObjectMetadataImpl(type);
    }

}
