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

import ro.zg.metadata.commons.FieldMetadata;
import ro.zg.metadata.commons.MultitypeMetadata;
import ro.zg.metadata.commons.MultitypeMetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;
import ro.zg.metadata.managers.MetadataManager;

public class FieldMetadataBuilder<F extends FieldMetadata<?>>
	extends
	AbstractMetadataBuilder<Field, MultitypeMetadata<Field, F>, MultitypeMetadataContext<Field, F>> {

    public FieldMetadataBuilder(
	    MetadataContextFactory<Field, MultitypeMetadata<Field, F>, MultitypeMetadataContext<Field, F>> metadataContextFactory,
	    MetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
    }

    @Override
    protected void buildFromMetadataContext(
	    MultitypeMetadataContext<Field, F> metadataContext)
	    throws MetadataException {
	Field field = metadataContext.getSource();

	for (Annotation a : field.getAnnotations()) {
	    /*
	     * TODO: don't forget to check that this field should actually be
	     * processed, stop processing if it is transient or static..
	     */
	    processAnnotation(a, metadataContext);
	}

    }

}
