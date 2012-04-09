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

public class FieldAnnotationMapperContext<A extends Annotation,F extends FieldMetadata<?>, P extends ObjectMetadata<?, F>> extends AnnotationProcessorContext<A,F>{
    private FieldAnnotationContext<P> fieldAnnotationContext;
    
    
    public FieldAnnotationMapperContext(A annotation, FieldAnnotationContext<P> fieldAnnotationContext) {
	super(annotation);
	this.fieldAnnotationContext = fieldAnnotationContext;
    }


    /**
     * @return the fieldAnnotationContext
     */
    public FieldAnnotationContext<P> getFieldAnnotationContext() {
        return fieldAnnotationContext;
    }

}
