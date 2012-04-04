package ro.zg.metadata.commons;

import java.lang.reflect.Type;

import ro.zg.metadata.exceptions.MetadataException;

public class GenericMetadataManager implements TypeMetadataManager{
    private ObjectMetadataManagerConfig config;
    private ObjectMetadataRepository metadataRepository=new ObjectMetadataRepository();
    private MetadataBuildersManager metadataBuildersManager;
    
    public GenericMetadataManager(ObjectMetadataManagerConfig config) {
	super();
	this.config = config;
    }

    @Override
    public <T extends Type, M extends Metadata<T>> M getMetadata(T type) throws MetadataException {
	M metadata = (M)metadataRepository.getDataModel(type);
	if(metadata == null) {
	    if(config.isAutomaticObjectModelCreationOn()) {
		metadata = (M)metadataBuildersManager.buildMetadata(type);
		addMetadata(metadata);
	    }
	    else {
		throw new RuntimeException("No metadata found for type "+type);
	    }
	}
	return metadata;
    } 
    
    private void addMetadata(Metadata<?> metadata) {
	metadataRepository.addDataModel(metadata);
    }

    /**
     * @return the config
     */
    public ObjectMetadataManagerConfig getConfig() {
        return config;
    }

    /**
     * @return the metadataRepository
     */
    public ObjectMetadataRepository getMetadataRepository() {
        return metadataRepository;
    }

    /**
     * @return the metadataBuildersManager
     */
    public MetadataBuildersManager getMetadataBuildersManager() {
        return metadataBuildersManager;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(ObjectMetadataManagerConfig config) {
        this.config = config;
    }

    /**
     * @param metadataRepository the metadataRepository to set
     */
    public void setMetadataRepository(ObjectMetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    /**
     * @param metadataBuildersManager the metadataBuildersManager to set
     */
    public void setMetadataBuildersManager(MetadataBuildersManager metadataBuildersManager) {
        this.metadataBuildersManager = metadataBuildersManager;
    }
    
}
