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
package ro.zg.metadata.mappers;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ro.zg.metadata.commons.AnnotationMapper;
import ro.zg.metadata.commons.AnnotationMapperContext;
import ro.zg.metadata.commons.AnnotationMappersManager;
import ro.zg.metadata.commons.AnnotationProcessorContext;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;

public class BaseAnnotationMappersManager<C extends AnnotationProcessorContext<? extends Annotation,? extends MetadataContext<?, Metadata<?>>>> implements AnnotationMappersManager<C>{
    protected Map<Class<? extends Annotation>,AnnotationMapper<AnnotationMapperContext<?,?>>> mappers=new HashMap<Class<? extends Annotation>,AnnotationMapper<? extends AnnotationMapperContext<?,?>>>();
    
    public void map(C amc) throws MetadataException {
	AnnotationMapper<AnnotationMapperContext<?,?>> mapper = mappers.get(amc.getAnnotation().annotationType());
	if(mapper!=null) {
	    mapper.map(amc);
	}
	else {
	    System.out.println("No mapper for "+amc);
	}
    }
    
    public Set<Class<? extends Annotation>> getKnownAnnotations(){
	return mappers.keySet();
    }
    
    protected void addMapper(Class<? extends Annotation> a, AnnotationMapper<C> mapper) {
	mappers.put(a, mapper);
    }
}
