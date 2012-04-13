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

public class MetadataDecoratorImpl<T, M extends Metadata<T>> extends MetadataImpl<T> implements MetadataDecorator<T, M>{
    protected M nestedMetadata;
    private boolean required;

    /**
     * @return the nestedMetadata
     */
    public M getNestedMetadata() {
        return nestedMetadata;
    }

    /**
     * @param nestedMetadata the nestedMetadata to set
     */
    public void setNestedMetadata(M nestedMetadata) {
        this.nestedMetadata = nestedMetadata;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String toString() {
	return "MetadataDecoratorImpl [nestedMetadata=" + nestedMetadata
		+ ", required=" + required + "]";
    }

  
    
}