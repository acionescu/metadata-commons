package ro.zg.metadata.builders;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

import ro.zg.metadata.commons.CollectionMetadata;
import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.MapMetadata;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.util.data.reflection.ReflectionUtility;

public class ParameterizedTypeMetadataBuilder implements MetadataBuilder<ParameterizedType, Metadata<?>>{
    private GenericMetadataManager metadataManager;
    private MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> collectionMetadataBuilder;
    private MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> mapMetadataBuilder;
    
    
    public ParameterizedTypeMetadataBuilder(GenericMetadataManager objectMetadataManager) {
	super();
	this.metadataManager = objectMetadataManager;
	collectionMetadataBuilder = new CollectionMetadataBuilder((GenericMetadataManager)objectMetadataManager);
	mapMetadataBuilder = new MapMetadataBuilder((GenericMetadataManager)objectMetadataManager);
    }
    
    public ParameterizedTypeMetadataBuilder(GenericMetadataManager objectMetadataManager,
	    MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> collectionMetadataBuilder,
	    MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> mapMetadataBuilder) {
	super();
	this.metadataManager = objectMetadataManager;
	this.collectionMetadataBuilder = collectionMetadataBuilder;
	this.mapMetadataBuilder = mapMetadataBuilder;
    }




    @Override
    public Metadata<?> buildMetadata(ParameterizedType pt) throws MetadataException {
	Class<?> clazz = (Class<?>) pt.getRawType();
	if (ReflectionUtility.checkInstanceOf(clazz, Collection.class)) {
	    return collectionMetadataBuilder.buildMetadata(pt);
	} else if (ReflectionUtility.checkInstanceOf(clazz, Map.class)) {
	    return mapMetadataBuilder.buildMetadata(pt);
	}
	return metadataManager.getMetadata(clazz);
    }


    /**
     * @return the collectionMetadataBuilder
     */
    public MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> getCollectionMetadataBuilder() {
        return collectionMetadataBuilder;
    }


    /**
     * @return the mapMetadataBuilder
     */
    public MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> getMapMetadataBuilder() {
        return mapMetadataBuilder;
    }




    /**
     * @param collectionMetadataBuilder the collectionMetadataBuilder to set
     */
    public void setCollectionMetadataBuilder(
    	MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> collectionMetadataBuilder) {
        this.collectionMetadataBuilder = collectionMetadataBuilder;
    }


    /**
     * @param mapMetadataBuilder the mapMetadataBuilder to set
     */
    public void setMapMetadataBuilder(MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> mapMetadataBuilder) {
        this.mapMetadataBuilder = mapMetadataBuilder;
    }

    /**
     * @return the metadataManager
     */
    public GenericMetadataManager getMetadataManager() {
        return metadataManager;
    }

    /**
     * @param metadataManager the metadataManager to set
     */
    public void setMetadataManager(GenericMetadataManager metadataManager) {
        this.metadataManager = metadataManager;
    }
    
}
