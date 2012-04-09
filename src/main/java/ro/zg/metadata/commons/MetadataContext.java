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

public class MetadataContext<S, M extends Metadata<?>> {
    private S source;
    private M metadata;
    private MetadataManager metadataManager;
    private MetadataContext<?, ?> superMetadataContext;

    public MetadataContext(S source) {
	super();
	this.source = source;
    }

    public MetadataContext(S source, MetadataManager metadataManager) {
	super();
	this.source = source;
	this.metadataManager = metadataManager;
    }

    public MetadataContext(S source, MetadataManager metadataManager,
	    MetadataContext<?, ?> superMetadataContext) {
	super();
	this.source = source;
	this.metadataManager = metadataManager;
	this.superMetadataContext = superMetadataContext;
    }

    /**
     * @return the source
     */
    public S getSource() {
	return source;
    }

    /**
     * @return the metadataManager
     */
    public MetadataManager getMetadataManager() {
	return metadataManager;
    }

    /**
     * @return the superMetadataContext
     */
    public MetadataContext<?, ?> getSuperMetadataContext() {
	return superMetadataContext;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(S source) {
	this.source = source;
    }

    /**
     * @param metadataManager
     *            the metadataManager to set
     */
    public void setMetadataManager(MetadataManager metadataManager) {
	this.metadataManager = metadataManager;
    }

    /**
     * @param superMetadataContext
     *            the superMetadataContext to set
     */
    public void setSuperMetadataContext(
	    MetadataContext<?, ?> superMetadataContext) {
	this.superMetadataContext = superMetadataContext;
    }

    /**
     * @return the metadata
     */
    public M getMetadata() {
	return metadata;
    }

    /**
     * @param metadata
     *            the metadata to set
     */
    public void setMetadata(M metadata) {
	this.metadata = metadata;
    }

}
