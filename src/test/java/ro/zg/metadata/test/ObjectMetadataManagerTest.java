package ro.zg.metadata.test;

import org.junit.Before;
import org.junit.Test;

import ro.zg.metadata.commons.GenericMetadataManager;
import ro.zg.metadata.commons.MetadataBuildersManager;
import ro.zg.metadata.commons.MetadataImpl;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.commons.ObjectMetadataManager;
import ro.zg.metadata.commons.ObjectMetadataManagerConfig;
import ro.zg.metadata.exceptions.MetadataException;

public class ObjectMetadataManagerTest {
    private ObjectMetadataManager omm;
    
    @Before
    public void init() {
	ObjectMetadataManagerConfig objectMetadataManagerConfig = new ObjectMetadataManagerConfig();
	omm = new ObjectMetadataManager(objectMetadataManagerConfig);
	MetadataBuildersManager metadataBuildersManager = new MetadataBuildersManager((GenericMetadataManager)omm);
	omm.setMetadataBuildersManager(metadataBuildersManager);
    }
    
    @Test
    public void testGetMetadata() throws MetadataException {
	ObjectMetadata<MetadataImpl, ?> om = omm.getObjectMetadata(MetadataImpl.class);
	System.out.println(om.getFields());
    }

}
