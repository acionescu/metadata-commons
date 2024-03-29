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

public class AnnotationMapperContext<A extends Annotation, M extends Metadata<?>> {
    private A annotation;
    private M metadata;
    
    
    public AnnotationMapperContext(A annotation, M metadata) {
	super();
	this.annotation = annotation;
	this.metadata = metadata;
    }


    public A getAnnotation() {
        return annotation;
    }


    public void setAnnotation(A annotation) {
        this.annotation = annotation;
    }


    public M getMetadata() {
        return metadata;
    }


    public void setMetadata(M metadata) {
        this.metadata = metadata;
    }
    
    
    
}
