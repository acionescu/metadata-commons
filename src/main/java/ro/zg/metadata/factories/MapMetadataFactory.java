package ro.zg.metadata.factories;

import java.lang.reflect.ParameterizedType;

import ro.zg.metadata.commons.MapMetadata;

public class MapMetadataFactory implements MetadataFactory<ParameterizedType, MapMetadata<?,?>>{

    @Override
    public MapMetadata<?, ?> createMetadata(ParameterizedType type) {
	return new MapMetadata(type);
    }

}
