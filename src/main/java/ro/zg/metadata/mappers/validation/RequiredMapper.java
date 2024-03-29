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
package ro.zg.metadata.mappers.validation;

import ro.zg.metadata.annotations.validation.Required;
import ro.zg.metadata.commons.AnnotationMapper;
import ro.zg.metadata.commons.AnnotationMapperContext;
import ro.zg.metadata.commons.FieldMetadataImpl;
import ro.zg.metadata.exceptions.MetadataException;

public class RequiredMapper
	implements
	AnnotationMapper<AnnotationMapperContext<Required, FieldMetadataImpl<?,?>>> {

    @Override
    public void map(AnnotationMapperContext<Required, FieldMetadataImpl<?,?>> amc)
	    throws MetadataException {
	amc.getMetadata().setRequired(true);

    }
}
