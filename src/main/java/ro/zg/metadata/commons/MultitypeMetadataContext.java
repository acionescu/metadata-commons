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

import ro.zg.metadata.managers.MetadataManager;

public class MultitypeMetadataContext<S, M extends Metadata<?>>
	extends MetadataContext<S, MultitypeMetadata<S, M>> {

    public MultitypeMetadataContext(S source) {
	super(source);
	setMetadata(new MultitypeMetadata<S, M>());
    }

    public MultitypeMetadataContext(S source, MetadataManager metadataManager) {
	super(source, metadataManager);
	setMetadata(new MultitypeMetadata<S, M>());
    }

    public MultitypeMetadataContext(S source, MetadataManager metadataManager,
	    MetadataContext<?, ?> superMetadataContext) {
	super(source, metadataManager, superMetadataContext);
	setMetadata(new MultitypeMetadata<S, M>());
    }

}
