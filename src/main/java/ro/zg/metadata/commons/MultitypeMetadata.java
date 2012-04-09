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

import java.util.HashMap;
import java.util.Map;

public class MultitypeMetadata<T,M extends Metadata<?>> extends MetadataImpl<T>{
    private Map<String, M> metadatas=new HashMap<String, M>();

    /**
     * @return the metadatas
     */
    public Map<String, M> getMetadatas() {
        return metadatas;
    }

    /**
     * @param metadatas the metadatas to set
     */
    public void setMetadatas(Map<String, M> metadatas) {
        this.metadatas = metadatas;
    }
    
    public M getMetadataByType(String type) {
	return metadatas.get(type);
    }
    
    public void addMetadataForType(M metadata, String type) {
	metadatas.put(type, metadata);
    }
}
