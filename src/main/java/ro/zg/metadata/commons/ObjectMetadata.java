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

import java.util.Map;

import ro.zg.metadata.exceptions.MetadataException;

public interface ObjectMetadata<T,F extends FieldMetadata<?>> extends Metadata<T>  {
    Map<String,F> getFields();
    F getField(String name);
    ObjectMetadata<?,F> getObjectDataModelForField(String fieldName);
    Map<String, Object> getFieldValuesMap(T target) throws MetadataException;
    void setFieldValue(Object target, String fieldName, String fieldValue) throws MetadataException;
    boolean acceptField(F fieldMetadata);
}
