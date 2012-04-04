/*******************************************************************************
 * Copyright 2011 Adrian Cristian Ionescu
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


public class AnnotationProcessorContext<A extends Annotation, M extends MetadataContext<?>> {
    private A annotation;
    private M metadataContext;
    
    public AnnotationProcessorContext() {
	super();
    }

    public AnnotationProcessorContext(A annotation) {
	super();
	this.annotation = annotation;
    }
    
    

    public AnnotationProcessorContext(A annotation, M metadataContext) {
	super();
	this.annotation = annotation;
	this.metadataContext = metadataContext;
    }

    /**
     * @return the annotation
     */
    public A getAnnotation() {
        return annotation;
    }

    /**
     * @param annotation the annotation to set
     */
    public void setAnnotation(A annotation) {
        this.annotation = annotation;
    }

    /**
     * @return the metadataContext
     */
    public M getMetadataContext() {
        return metadataContext;
    }

    /**
     * @param metadataContext the metadataContext to set
     */
    public void setMetadataContext(M metadataContext) {
        this.metadataContext = metadataContext;
    }
}
