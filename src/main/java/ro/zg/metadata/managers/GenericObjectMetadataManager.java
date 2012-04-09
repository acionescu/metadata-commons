/*******************************************************************************
 * Copyright 2012 AdrianIonescu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ro.zg.metadata.managers;

import java.lang.reflect.Type;

import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataBuildersManager;
import ro.zg.metadata.commons.ObjectMetadataManagerConfig;
import ro.zg.metadata.commons.ObjectMetadataRepository;
import ro.zg.metadata.exceptions.MetadataException;

public class GenericObjectMetadataManager implements MetadataManager{
    private ObjectMetadataManagerConfig config;
    private ObjectMetadataRepository metadataRepository=new ObjectMetadataRepository();
    private MetadataBuildersManager metadataBuildersManager;
    
    public GenericObjectMetadataManager(ObjectMetadataManagerConfig config) {
	super();
	this.config = config;
    }

    @Override
    public <T, M extends Metadata<?>> M getMetadata(T input) throws MetadataException {
	Type type = (Type)input;
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
