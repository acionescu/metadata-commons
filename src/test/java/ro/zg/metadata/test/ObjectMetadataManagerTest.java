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
package ro.zg.metadata.test;

import org.junit.Before;
import org.junit.Test;

import ro.zg.metadata.annotations.SimpleMetadataTypes;
import ro.zg.metadata.commons.MetadataBuildersManager;
import ro.zg.metadata.commons.MetadataImpl;
import ro.zg.metadata.commons.ObjectMetadata;
import ro.zg.metadata.commons.ObjectMetadataManagerConfig;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.metadata.managers.GenericObjectMetadataManager;
import ro.zg.metadata.managers.ObjectMetadataManager;

public class ObjectMetadataManagerTest {
    private ObjectMetadataManager omm;
    
    @Before
    public void init() {
	ObjectMetadataManagerConfig objectMetadataManagerConfig = new ObjectMetadataManagerConfig();
	omm = new ObjectMetadataManager(objectMetadataManagerConfig);
	MetadataBuildersManager metadataBuildersManager = new MetadataBuildersManager(omm);
	omm.setMetadataBuildersManager(metadataBuildersManager);
    }
    
    @Test
    public void testGetMetadata() throws MetadataException {
//	ObjectMetadata<MetadataImpl, ?> om = omm.getObjectMultitypeMetadata(MetadataImpl.class);
	ObjectMetadata<MetadataImpl<?>, ?> om = omm.getobjectMetadata(MetadataImpl.class, SimpleMetadataTypes.SIMPLE);
	System.out.println(om.getFields());
    }

}
