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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import ro.zg.metadata.annotations.SimpleMetadataTypes;
import ro.zg.metadata.commons.AnnotationProcessorContext;
import ro.zg.metadata.commons.FieldMetadataImpl;
import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;
import ro.zg.metadata.factories.FieldMetadataFactory;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.managers.MetadataManager;
import ro.zg.metadata.mappers.FieldAnnotationMappersManager;
import ro.zg.metadata.mappers.validation.SimpleFieldAnnotationMappersManager;

public class DefaultFieldMetadataBuilder extends FieldMetadataBuilder<FieldMetadataImpl<?>>{

    public DefaultFieldMetadataBuilder(
	    MetadataContextFactory<Field, MultitypeMetadata<Field, FieldMetadataImpl<?>>, MultitypeMetadataContext<Field, FieldMetadataImpl<?>>> metadataContextFactory,
	    MetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
	setAnnotationMappersManager(new FieldAnnotationMappersManager<AnnotationProcessorContext<? extends Annotation,MultitypeMetadataContext<Field,FieldMetadataImpl<?>>>>());
	addMapper(SimpleMetadataTypes.SIMPLE,
		new SimpleFieldAnnotationMappersManager());
	addMetadataFactory(SimpleMetadataTypes.SIMPLE,
		new FieldMetadataFactory());
    }

}
