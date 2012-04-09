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
package ro.zg.metadata.mappers;

import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.annotations.SimpleMetadataTypes;
import ro.zg.metadata.commons.FieldAnnotationContext;
import ro.zg.metadata.commons.FieldAnnotationMapperContext;
import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.FieldMetadataImpl;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataDecorator;
import ro.zg.metadata.commons.MultitypeAnnotationMappersManager;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.factories.MetadataDecoratorFactory;
import ro.zg.metadata.factories.SimpleFieldMetadataFactory;
import ro.zg.metadata.mappers.validation.SimpleFieldAnnotationMappersManager;

public class FieldAnnotationMappersManager<M extends FieldMetadata<?>, O extends ObjectMetadata<?, M>> extends
	MultitypeAnnotationMappersManager<FieldAnnotationMapperContext<?, M, O>> {
    protected Map<String, MetadataDecoratorFactory<?,Metadata<?>, MetadataDecorator<?, Metadata<?>>>> fieldMetadataFactories = new HashMap<String, MetadataDecoratorFactory<?,Metadata<?>, MetadataDecorator<?, Metadata<?>>>>();
    
    public FieldAnnotationMappersManager() {
	addMapper(SimpleMetadataTypes.SIMPLE, new SimpleFieldAnnotationMappersManager());
	fieldMetadataFactories.put(SimpleMetadataTypes.SIMPLE, new SimpleFieldMetadataFactory());
    }

    @Override
    protected void prepareContext(FieldAnnotationMapperContext<?, M, O> amc, String type) {
	FieldAnnotationContext<O> fieldAnnotationContext = amc.getFieldAnnotationContext();
	amc.setMetadata((M) getFieldMetadata(fieldAnnotationContext, type));
    }

    protected <F extends MetadataDecorator<?, ? extends Metadata<?>>> F getFieldMetadata(FieldAnnotationContext<O> fac,
	    String type) {
	Map<String, F> fieldMetadatas = (Map<String, F>) fac.getFieldMetadatas();
	F fieldMetadata = (F) fieldMetadatas.get(type);
	if (fieldMetadata != null) {
	    return fieldMetadata;
	}
	String dependencyType = getTypeDependency(type);
	if (dependencyType == null) {
	    fieldMetadata = (F) fieldMetadataFactories.get(type).createMetadata(fac.getFieldTypeMetadata());
	    ((FieldMetadataImpl<?>) fieldMetadata).setName(fac.getField().getName());
	} else {
	    F dependencyMetadata = getFieldMetadata(fac, dependencyType);
	    fieldMetadata = (F) fieldMetadataFactories.get(type).createMetadata(dependencyMetadata);
	}
	fieldMetadatas.put(type, fieldMetadata);
	return fieldMetadata;
    }

}
