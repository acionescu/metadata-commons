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
package ro.zg.metadata.commons;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.annotations.AnnotationType;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataFactory;

public abstract class MultitypeAnnotationMappersManager<C extends AnnotationProcessorContext<?, ?>> {
    private Map<String, AnnotationMappersManager<AnnotationMapperContext<Annotation, Metadata<?>>>> annotationMappersManagers = new HashMap<String, AnnotationMappersManager<AnnotationMapperContext<Annotation, Metadata<?>>>>();
    protected Map<String, String> typeDependencies = new HashMap<String, String>();
    protected Map metadataFactories = new HashMap();

    public void map(C apc) throws MetadataException {
	Class<? extends Annotation> annotationType = apc.getAnnotation()
		.annotationType();
	String mt = annotationType.getAnnotation(AnnotationType.class).value();
	AnnotationMappersManager<AnnotationMapperContext<Annotation, Metadata<?>>> annotationMappersManager = annotationMappersManagers
		.get(mt);

	if (annotationMappersManager != null) {
	    annotationMappersManager.map(getAnnotationMapperContext(apc, mt));
	}
    }

    protected AnnotationMapperContext<Annotation, Metadata<?>> getAnnotationMapperContext(
	    C amc, String type) {
	MultitypeMetadataContext<?, Metadata<?>> mmc = (MultitypeMetadataContext<?, Metadata<?>>) amc
		.getMetadataContext();
	MultitypeMetadata<?, Metadata<?>> mm = mmc.getMetadata();
	Metadata<?> currentMetadata = mm.getMetadataByType(type);
	if (currentMetadata == null) {
	    currentMetadata = createMetadataForType(type, mmc.getSource());
	    mm.addMetadataForType(currentMetadata, type);
	}
	return new AnnotationMapperContext<Annotation, Metadata<?>>(
		amc.getAnnotation(), currentMetadata);
    }

    private Metadata<?> createMetadataForType(String type, Object source) {
	MetadataFactory metadataFactory = (MetadataFactory)metadataFactories.get(type);
	if (metadataFactory == null) {
	    throw new IllegalArgumentException(
		    "Factory missing for metadata type: '" + type + "'");
	}
	return metadataFactory.createMetadata(source.getClass());
    }

    public void addMapper(
	    String type,
	    AnnotationMappersManager<? extends AnnotationMapperContext<Annotation, Metadata<?>>> mappersManager) {
	annotationMappersManagers.put(type,
		(AnnotationMappersManager) mappersManager);
    }

    protected String getTypeDependency(String type) {
	return typeDependencies.get(type);
    }
}
