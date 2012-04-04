package ro.zg.metadata.commons;

import ro.zg.metadata.managers.MetadataManager;

public class MultitypeMetadataContext<S, M extends MultitypeMetadata<S,?>> extends MetadataContext<S, M>{

    
    
    public MultitypeMetadataContext(S source) {
	super(source);
    }

    public MultitypeMetadataContext(S source, MetadataManager<S, M> metadataManager) {
	super(source, metadataManager);
    }

    public MultitypeMetadataContext(S source, MetadataManager<S, M> metadataManager,
	    MetadataContext<?, ?> superMetadataContext) {
	super(source, metadataManager, superMetadataContext);
    }

}
