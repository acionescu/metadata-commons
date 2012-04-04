package ro.zg.metadata.builders;

import java.lang.reflect.ParameterizedType;

import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.MapMetadata;
import ro.zg.metadata.commons.MetadataContext;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.factories.MetadataContextFactory;

public class MapMetadataBuilder extends AbstractMetadataBuilder<ParameterizedType, MapMetadata<?, ?>,MetadataContext<ParameterizedType,MapMetadata<?, ?>>>{

    
    public MapMetadataBuilder(
	    MetadataContextFactory<ParameterizedType, MapMetadata<?, ?>, MetadataContext<ParameterizedType, MapMetadata<?, ?>>> metadataContextFactory,
	    GenericMetadataManager metadataManager) {
	super(metadataContextFactory, metadataManager);
	// TODO Auto-generated constructor stub
    }

    @Override
    protected void buildFromMetadataContext(MetadataContext<ParameterizedType, MapMetadata<?, ?>> metadataContext)
	    throws MetadataException {
	// TODO Auto-generated method stub
	
    }

   

}
