package ro.zg.metadata.commons;

import ro.zg.metadata.exceptions.MetadataException;

public class ObjectMetadataManager extends GenericMetadataManager{

    public ObjectMetadataManager(ObjectMetadataManagerConfig config) {
	super(config);
    }

    public <T> ObjectMetadata<T, ?> getObjectMetadata(Class<T> clazz) throws MetadataException{
	return (ObjectMetadata<T, ?>)super.getMetadata(clazz);
    }
    
}
