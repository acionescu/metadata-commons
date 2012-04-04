package ro.zg.metadata.commons;

import ro.zg.metadata.managers.MetadataManager;

public class MetadataContext<S, M extends Metadata<?>> {
    private S source;
    private M metadata;
    private MetadataManager<S, M> metadataManager;
    private MetadataContext<?, ?> superMetadataContext;
    
    
    public MetadataContext(S source) {
	super();
	this.source = source;
    }

    public MetadataContext(S source, MetadataManager<S, M> metadataManager) {
	super();
	this.source = source;
	this.metadataManager = metadataManager;
    }

    public MetadataContext(S source, MetadataManager<S, M> metadataManager, MetadataContext<?, ?> superMetadataContext) {
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
    public MetadataManager<S, M> getMetadataManager() {
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
    public void setMetadataManager(MetadataManager<S, M> metadataManager) {
	this.metadataManager = metadataManager;
    }

    /**
     * @param superMetadataContext
     *            the superMetadataContext to set
     */
    public void setSuperMetadataContext(MetadataContext<?, ?> superMetadataContext) {
	this.superMetadataContext = superMetadataContext;
    }

    /**
     * @return the metadata
     */
    public M getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(M metadata) {
        this.metadata = metadata;
    }
    
    

}
