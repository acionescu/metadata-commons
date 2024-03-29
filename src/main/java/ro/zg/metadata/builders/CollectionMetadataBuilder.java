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

import ro.zg.metadata.commons.CollectionMetadata;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.managers.ObjectMetadataManager;

public class CollectionMetadataBuilder extends AbstractMetadataBuilder<ParameterizedType, CollectionMetadata<?>,MetadataContext<ParameterizedType,CollectionMetadata<?>>> {

    public CollectionMetadataBuilder(
	    MetadataContextFactory<ParameterizedType, CollectionMetadata<?>, MetadataContext<ParameterizedType, CollectionMetadata<?>>> metadataContextFactory,
	    ObjectMetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
    }

    @Override
    protected void buildFromMetadataContext(MetadataContext<ParameterizedType, CollectionMetadata<?>> metadataContext)
	    throws MetadataException {
	
    }

   

   


}
