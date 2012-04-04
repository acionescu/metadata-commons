package ro.zg.metadata.builders;

import java.lang.reflect.ParameterizedType;

import ro.zg.metadata.commons.CollectionMetadata;
import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;

public class CollectionMetadataBuilder extends AbstractMetadataBuilder<ParameterizedType, CollectionMetadata<?>,MetadataContext<ParameterizedType,CollectionMetadata<?>>> {

    public CollectionMetadataBuilder(
	    MetadataContextFactory<ParameterizedType, CollectionMetadata<?>, MetadataContext<ParameterizedType, CollectionMetadata<?>>> metadataContextFactory,
	    GenericMetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
	// TODO Auto-generated constructor stub
    }

    @Override
    protected void buildFromMetadataContext(MetadataContext<ParameterizedType, CollectionMetadata<?>> metadataContext)
	    throws MetadataException {
	// TODO Auto-generated method stub
	
    }

   

   


}
