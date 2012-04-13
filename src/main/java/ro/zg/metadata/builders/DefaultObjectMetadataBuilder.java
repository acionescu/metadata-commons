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

import ro.zg.metadata.annotations.SimpleMetadataTypes;
import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;
import ro.zg.metadata.commons.ObjectMetadataImpl;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.factories.MetadataFactory;
import ro.zg.metadata.factories.ObjectMetadataFactory;
import ro.zg.metadata.managers.ObjectMetadataManager;

public class DefaultObjectMetadataBuilder extends
	ObjectMetadataBuilder<ObjectMetadataImpl<?, FieldMetadata<?,?>>> {

    // public DefaultObjectMetadataBuilder(ObjectMetadataManager
    // metadataManager) {
    // // MetadataContextFactory<Type, MultitypeMetadata<Type,?>,
    // MultitypeMetadataContext<Type, MultitypeMetadata<Type,?>>> mcf = new
    // MultitypeMetadataContextFactory<Type, MultitypeMetadata<Type,?>>();
    // super(new MultitypeMetadataContextFactory(), metadataManager);
    // }
    //
    // public DefaultObjectMetadataBuilder(
    // MetadataFactory<Class<?>, ObjectMetadataImpl<?, ?>> metadataFactory,
    // ObjectMetadataManager metadataManager) {
    // super(new MultitypeMetadataContextFactory(), metadataManager);
    // }

    public DefaultObjectMetadataBuilder(
	    MetadataContextFactory<Class<?>, MultitypeMetadata<Class<?>, ObjectMetadataImpl<?, FieldMetadata<?,?>>>, MultitypeMetadataContext<Class<?>, ObjectMetadataImpl<?, FieldMetadata<?,?>>>> metadataContextFactory,
	    ObjectMetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
	setFieldMetadataBuilder(new DefaultFieldMetadataBuilder(
		(MetadataContextFactory) metadataContextFactory,
		metadataManager));
	addMetadataFactory(SimpleMetadataTypes.SIMPLE,
		(MetadataFactory)new ObjectMetadataFactory());
    }

    @Override
    protected void populateObjectMetadataFromSuperTypeMetadata(
	    MultitypeMetadata<Class<?>, ObjectMetadataImpl<?, FieldMetadata<?,?>>> targetMetadata,
	    MultitypeMetadata<Class<?>, ObjectMetadataImpl<?, FieldMetadata<?,?>>> superTypeMetadata) {

    }

}
