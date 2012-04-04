package ro.zg.metadata.builders;

import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.commons.ObjectMetadataImpl;
import ro.zg.metadata.factories.MetadataFactory;
import ro.zg.metadata.factories.ObjectMetadataFactory;

public class DefaultObjectMetadataBuilder extends ObjectMetadataBuilder<ObjectMetadataImpl<?,?>>{

    public DefaultObjectMetadataBuilder(GenericMetadataManager metadataManager) {
	super(new ObjectMetadataFactory(), metadataManager);
    }
    
    public DefaultObjectMetadataBuilder(MetadataFactory<Class<?>, ObjectMetadataImpl<?, ?>> metadataFactory,
	    GenericMetadataManager metadataManager) {
	super(metadataFactory, metadataManager);
    }

    @Override
    protected void populateObjectMetadataFromSuperTypeMetadata(ObjectMetadataImpl<?, ?> objectMetadata,
	    ObjectMetadata<?, ?> superTypeMetadata) {
	// TODO Auto-generated method stub
	
    }

}
