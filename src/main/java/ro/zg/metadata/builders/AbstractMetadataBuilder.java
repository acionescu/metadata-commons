package ro.zg.metadata.builders;

import java.lang.reflect.Type;
import java.util.Hashtable;
import java.util.Map;

import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.Metadata;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;

public abstract class AbstractMetadataBuilder<T, M extends Metadata<?>, C extends MetadataContext<T,M>> implements MetadataBuilder<T,M> {
    private MetadataContextFactory<T, M, C> metadataContextFactory;
    private GenericMetadataManager metadataManager;
    private Map<T,C> pendingMetadata=new Hashtable<T, C>();


    public AbstractMetadataBuilder(MetadataContextFactory<T, M, C> metadataContextFactory,
	    GenericMetadataManager metadataManager) {
	super();
	this.metadataContextFactory = metadataContextFactory;
	this.metadataManager = metadataManager;
    }

    @Override
    public synchronized M buildMetadata(T type) throws MetadataException {
	if(pendingMetadata.containsKey(type)) {
	    return pendingMetadata.get(type).getMetadata();
	}
	C metadataContext = createMetadataContext(type);
	pendingMetadata.put(type, metadataContext);
	buildFromMetadataContext(metadataContext);
	pendingMetadata.remove(type);
	return metadataContext.getMetadata();
    }
    
    private  C createMetadataContext(T type) {
	return metadataContextFactory.createMetadataContext(type);
    }
    
    protected C getPendingMetadataContext(T type) {
	return pendingMetadata.get(type);
    }
    
    protected Metadata<?> getGenericTypeMetadata(Type type) throws MetadataException {
	return metadataManager.getMetadata(type);
    }
    
    
    protected abstract void buildFromMetadataContext(C metadataContext) throws MetadataException;
    
}
