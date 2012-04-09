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

import ro.zg.metadata.commons.ClassAnnotationContext;
import ro.zg.metadata.commons.ClassAnnotationMapperContext;
import ro.zg.metadata.commons.FieldAnnotationContext;
import ro.zg.metadata.commons.FieldAnnotationMapperContext;
import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.factories.MetadataFactory;
import ro.zg.metadata.managers.GenericObjectMetadataManager;
import ro.zg.metadata.mappers.ObjectAnnotationMappersManager;

public abstract class ObjectMetadataBuilder<O extends ObjectMetadata<?, ?>>
		extends
		AbstractMetadataBuilder<Class<?>, MultitypeMetadata<Class<?>, O>, MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>>> {
	private ObjectAnnotationMappersManager annotationMappersManager = new ObjectAnnotationMappersManager();

	public ObjectMetadataBuilder(
			MetadataContextFactory<Class<?>, MultitypeMetadata<Class<?>, O>, MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>>> metadataContextFactory,
			GenericObjectMetadataManager metadataManager) {
		super(metadataContextFactory, metadataManager);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ro.zg.metadata.builders.AbstractMetadataBuilder#buildFromMetadataContext
	 * (ro.zg.metadata.commons.MetadataContext)
	 */
	@Override
	protected void buildFromMetadataContext(
			MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>> metadataContext)
			throws MetadataException {

		/* process fields annotations */
		processClassFields(metadataContext);

		/* extract metadata info from supertypes */
		absorbSuperTypesMetadata(metadataContext);

		/* process class annotations */
		processClassAnnotations(metadataContext);

	}

	protected abstract void populateObjectMetadataFromSuperTypeMetadata(
			MultitypeMetadata<Class<?>, O> targetMetadata,
			MultitypeMetadata<Class<?>, O> superTypeMetadata);

	private void absorbSuperTypesMetadata(
			MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>> metadataContext)
			throws MetadataException {
		Class<?> type = metadataContext.getSource();
		Class<?> superclass = type.getSuperclass();
		if (superclass != null) {
			MultitypeMetadata<Class<?>, O> superTypeMetadata = (MultitypeMetadata<Class<?>, O>) getGenericTypeMetadata(superclass);
			populateObjectMetadataFromSuperTypeMetadata(
					metadataContext.getMetadata(), superTypeMetadata);
		}
	}

	private void processClassFields(
			MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>> metadataContext)
			throws MetadataException {
		Class<?> clazz = metadataContext.getSource();
		for (Field f : clazz.getDeclaredFields()) {
			processFieldAnnotations(f, cac);
		}
	}

	private void processFieldAnnotations(Field field,
			ClassAnnotationContext<O> cac) throws MetadataException {
		Metadata<?> fieldTypeMetadata = getGenericTypeMetadata(field
				.getGenericType());
		FieldAnnotationContext<O> fieldAnnotationContext = new FieldAnnotationContext<O>(
				cac, fieldTypeMetadata, field);

		for (Annotation a : field.getAnnotations()) {
			FieldAnnotationMapperContext<Annotation, ?, O> fieldAnnotationMapperContext = new FieldAnnotationMapperContext(
					a, fieldAnnotationContext);
			annotationMappersManager.map(fieldAnnotationMapperContext);
		}

		ObjectMetadata objectMetadata = cac.getObjectMetadata();

		for (FieldMetadata<?> fm : fieldAnnotationContext.getFieldMetadatas()
				.values()) {
			objectMetadata.acceptField(fm);
		}

	}

	private void processClassAnnotations(MultitypeMetadataContext<Class<?>, MultitypeMetadata<Class<?>, O>> metadataContext) throws MetadataException {
		Class<?> clazz = metadataContext.getSource();
		for (Annotation a : clazz.getAnnotations()) {
			ClassAnnotationMapperContext<Annotation, O> classAnnotationMapperContext = new ClassAnnotationMapperContext<Annotation, O>(
					a, cac);
			annotationMappersManager.map(classAnnotationMapperContext);
		}
	}
}
