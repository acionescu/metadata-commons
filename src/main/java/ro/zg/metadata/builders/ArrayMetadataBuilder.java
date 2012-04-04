package ro.zg.metadata.builders;

import java.lang.reflect.GenericArrayType;

import ro.zg.metadata.commons.ArrayMetadata;
import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;

public class ArrayMetadataBuilder
	extends
	AbstractMetadataBuilder<GenericArrayType, ArrayMetadata<?>, MetadataContext<GenericArrayType, ArrayMetadata<?>>> {

    public ArrayMetadataBuilder(
	    MetadataContextFactory<GenericArrayType, ArrayMetadata<?>, MetadataContext<GenericArrayType, ArrayMetadata<?>>> metadataContextFactory,
	    GenericMetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
	// TODO Auto-generated constructor stub
    }

    @Override
    protected void buildFromMetadataContext(MetadataContext<GenericArrayType, ArrayMetadata<?>> metadataContext)
	    throws MetadataException {
	// TODO Auto-generated method stub

    }

}
