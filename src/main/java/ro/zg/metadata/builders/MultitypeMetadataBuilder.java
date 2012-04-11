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
import java.util.HashMap;
import java.util.Map;

import ro.zg.metadata.annotations.AnnotationType;
import ro.zg.metadata.annotations.SimpleMetadataTypes;
import ro.zg.metadata.commons.AnnotationMapperContext;
import ro.zg.metadata.commons.AnnotationMappersManager;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.factories.MetadataFactory;
import ro.zg.metadata.managers.MetadataManager;

public abstract class MultitypeMetadataBuilder<T, M extends Metadata<?>>
	extends
	AbstractMetadataBuilder<T, MultitypeMetadata<T, M>, MultitypeMetadataContext<T, M>> {
    private Map<String, AnnotationMappersManager<AnnotationMapperContext<Annotation, Metadata<?>>>> annotationMappersManagers = new HashMap<String, AnnotationMappersManager<AnnotationMapperContext<Annotation, Metadata<?>>>>();
    private Map<String, MetadataFactory<T, M>> metadataFactories = new HashMap<String, MetadataFactory<T, M>>();
    private String defaultMetadataType = SimpleMetadataTypes.SIMPLE;

    public MultitypeMetadataBuilder(
	    MetadataContextFactory<T, MultitypeMetadata<T, M>, MultitypeMetadataContext<T, M>> metadataContextFactory,
	    MetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
    }

    
    
    @Override
    protected void beforeBuild(MultitypeMetadataContext<T, M> metadataContext) {
	/*
	 * Creates the default metadata
	 */
	getMetadataFromContext(metadataContext, defaultMetadataType, true);
    }



    protected void processAnnotation(Annotation annotation,
	    MultitypeMetadataContext<T, M> mmc) throws MetadataException {
	Class<? extends Annotation> annotationType = annotation
		.annotationType();
	String mt = annotationType.getAnnotation(AnnotationType.class).value();
	AnnotationMappersManager<AnnotationMapperContext<Annotation, Metadata<?>>> annotationMappersManager = annotationMappersManagers
		.get(mt);

	if (annotationMappersManager != null) {
	    annotationMappersManager.map(getAnnotationMapperContext(mmc,
		    annotation, mt));
	}
    }

    protected AnnotationMapperContext<Annotation, Metadata<?>> getAnnotationMapperContext(
	    MultitypeMetadataContext<T, M> mmc, Annotation annotation,
	    String type) {

	M currentMetadata = getMetadataFromContext(mmc, type, true);

	return new AnnotationMapperContext<Annotation, Metadata<?>>(annotation,
		currentMetadata);
    }

    protected M getMetadataFromContext(MultitypeMetadataContext<T, M> mmc,
	    String type, boolean createIfInexistent) {
	MultitypeMetadata<T, M> mm = mmc.getMetadata();
	M metadata = mm.getMetadataByType(type);
	if (metadata == null && createIfInexistent) {
	    metadata = createMetadataForType(type, mmc.getSource());
	    mm.addMetadataForType(metadata, type);
	}
	return metadata;
    }

    protected M createMetadataForType(String type, T source) {
	MetadataFactory<T, M> metadataFactory = metadataFactories
		.get(type);
	if (metadataFactory == null) {
	    throw new IllegalArgumentException(
		    "Factory missing for metadata type: '" + type + "'");
	}
	return metadataFactory.createMetadata(source);
    }

    public void addMapper(
	    String type,
	    AnnotationMappersManager<? extends AnnotationMapperContext<Annotation, Metadata<?>>> mappersManager) {
	annotationMappersManagers.put(type,
		(AnnotationMappersManager) mappersManager);
    }

    public <F extends MetadataFactory<T, M>> void addMetadataFactory(
	    String type, F factory) {
	metadataFactories.put(type, factory);
    }
}
