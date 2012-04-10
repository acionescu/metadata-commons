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
package ro.zg.metadata.builders;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

import ro.zg.metadata.commons.CollectionMetadata;
import ro.zg.metadata.commons.MapMetadata;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.managers.ObjectMetadataManager;
import ro.zg.util.data.reflection.ReflectionUtility;

public class ParameterizedTypeMetadataBuilder implements
	MetadataBuilder<ParameterizedType, Metadata<?>> {
    private ObjectMetadataManager metadataManager;
    private MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> collectionMetadataBuilder;
    private MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> mapMetadataBuilder;

    public ParameterizedTypeMetadataBuilder(
	    MetadataContextFactory<?, ? extends Metadata<?>, ? extends MetadataContext<?, Metadata<?>>> metadataContextFactory,
	    ObjectMetadataManager objectMetadataManager) {
	super();
	this.metadataManager = objectMetadataManager;
	collectionMetadataBuilder = new CollectionMetadataBuilder(
		(MetadataContextFactory) metadataContextFactory,
		objectMetadataManager);
	mapMetadataBuilder = new MapMetadataBuilder(
		(MetadataContextFactory) metadataContextFactory,
		objectMetadataManager);
    }

    public ParameterizedTypeMetadataBuilder(
	    ObjectMetadataManager objectMetadataManager,
	    MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> collectionMetadataBuilder,
	    MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> mapMetadataBuilder) {
	super();
	this.metadataManager = objectMetadataManager;
	this.collectionMetadataBuilder = collectionMetadataBuilder;
	this.mapMetadataBuilder = mapMetadataBuilder;
    }

    @Override
    public Metadata<?> buildMetadata(ParameterizedType pt)
	    throws MetadataException {
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
     * @param collectionMetadataBuilder
     *            the collectionMetadataBuilder to set
     */
    public void setCollectionMetadataBuilder(
	    MetadataBuilder<ParameterizedType, ? extends CollectionMetadata<?>> collectionMetadataBuilder) {
	this.collectionMetadataBuilder = collectionMetadataBuilder;
    }

    /**
     * @param mapMetadataBuilder
     *            the mapMetadataBuilder to set
     */
    public void setMapMetadataBuilder(
	    MetadataBuilder<ParameterizedType, ? extends MapMetadata<?, ?>> mapMetadataBuilder) {
	this.mapMetadataBuilder = mapMetadataBuilder;
    }

    /**
     * @return the metadataManager
     */
    public ObjectMetadataManager getMetadataManager() {
	return metadataManager;
    }

    /**
     * @param metadataManager
     *            the metadataManager to set
     */
    public void setMetadataManager(ObjectMetadataManager metadataManager) {
	this.metadataManager = metadataManager;
    }

}
