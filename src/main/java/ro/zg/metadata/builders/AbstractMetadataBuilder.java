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
import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Map;

import ro.zg.metadata.commons.AnnotationProcessorContext;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.commons.MultitypeAnnotationMappersManager;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.managers.MetadataManager;

public abstract  class AbstractMetadataBuilder<T, M extends Metadata<?>, C extends MetadataContext<T, M>>
		implements MetadataBuilder<T, M> {
	private MetadataContextFactory<T, M, C> metadataContextFactory;
	private MetadataManager metadataManager;
	private Map<T, C> pendingMetadata = new Hashtable<T, C>();
	
	private MultitypeAnnotationMappersManager<AnnotationProcessorContext<? extends Annotation, C>> annotationMappersManager;

	public AbstractMetadataBuilder(
			MetadataContextFactory<T, M, C> metadataContextFactory,
			MetadataManager metadataManager) {
		super();
		this.metadataContextFactory = metadataContextFactory;
		this.metadataManager = metadataManager;
	}

	@Override
	public synchronized M buildMetadata(T type) throws MetadataException {
		if (pendingMetadata.containsKey(type)) {
			return pendingMetadata.get(type).getMetadata();
		}
		C metadataContext = createMetadataContext(type);
		metadataContext.setMetadataManager(metadataManager);
		pendingMetadata.put(type, metadataContext);
		buildFromMetadataContext(metadataContext);
		pendingMetadata.remove(type);
		return metadataContext.getMetadata();
	}

	private C createMetadataContext(T type) {
		return metadataContextFactory.createMetadataContext(type);
	}

	protected C getPendingMetadataContext(T type) {
		return pendingMetadata.get(type);
	}

	protected Metadata<?> getGenericTypeMetadata(Type type)
			throws MetadataException {
		return metadataManager.getMetadata(type);
	}

	protected void processAnnotation(Annotation annotation, C metadataContext) throws MetadataException{
		AnnotationProcessorContext<? extends Annotation, C> apc = new AnnotationProcessorContext<Annotation, C>(annotation,metadataContext);
		annotationMappersManager.map(apc);
	}

	protected abstract void buildFromMetadataContext(C metadataContext)
			throws MetadataException;

	public MetadataContextFactory<T, M, C> getMetadataContextFactory() {
	    return metadataContextFactory;
	}

	public void setMetadataContextFactory(
		MetadataContextFactory<T, M, C> metadataContextFactory) {
	    this.metadataContextFactory = metadataContextFactory;
	}

	public MetadataManager getMetadataManager() {
	    return metadataManager;
	}

	public void setMetadataManager(MetadataManager metadataManager) {
	    this.metadataManager = metadataManager;
	}

	public MultitypeAnnotationMappersManager<AnnotationProcessorContext<? extends Annotation, C>> getAnnotationMappersManager() {
	    return annotationMappersManager;
	}

	public void setAnnotationMappersManager(
		MultitypeAnnotationMappersManager<AnnotationProcessorContext<? extends Annotation, C>> annotationMappersManager) {
	    this.annotationMappersManager = annotationMappersManager;
	}

}
