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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ro.zg.metadata.exceptions.ValidationErrorType;
import ro.zg.metadata.exceptions.ValidationException;
import ro.zg.util.data.GenericNameValue;
import ro.zg.util.data.reflection.ReflectionUtility;
import ro.zg.util.parser.utils.CollectionMapParseEventHandler;
import ro.zg.util.parser.utils.JavaCollectionMapParser;
import ro.zg.util.parser.utils.MapTypeDefinition;
import ro.zg.util.parser.utils.MultivaluedTypeDefinition;

//@Persistable
public class FieldMetadataImpl<T, M extends Metadata<T>> extends MetadataDecoratorImpl<T, M>
	implements FieldMetadata<T,M> {
    public static Class<?> DEFAULT_SET_IMPLEMENTATION = HashSet.class;
    public static Class<?> DEFAULT_LIST_IMPLEMENTATION = ArrayList.class;
    public static Class<?> DEFAULT_MAP_IMPLEMENTATION = HashMap.class;

    /**
     * The collection/map parser Lazy initialized
     */
    private static JavaCollectionMapParser collectionMapParser = new JavaCollectionMapParser();

    private transient CollectionMapParseEventHandler fieldParseHandler;

    // @ObjectId
    // private String id;

    private String name;

    // @Link(name = "field_data_model_", lazy = false, allowedTypes = {
    // PersistentObjectMetadataImpl.class, MapDataModel.class,
    // CollectionDataModel.class, MetadataImpl.class })
    // protected Metadata<T> valueMetadata;
    private boolean required;
    protected Class<? extends T> implementation;

    public FieldMetadataImpl(String name) {
	super();
	this.name = name;
    }

    public FieldMetadataImpl(M valueMetadata) {
	super();
	this.nestedMetadata = valueMetadata;
    }

    public FieldMetadataImpl(String name, M valueMetadata) {
	super();
	this.name = name;
	this.nestedMetadata = valueMetadata;
    }

    public Object createValueFromString(String data) throws Exception {
	Metadata<?> dm = getNestedMetadata();
	if (dm.isMultivalued()) {
	    Object response = collectionMapParser.parse(data,
		    getFieldParseHandler());
	    if (dm instanceof CollectionMetadata) {
		CollectionMetadata<?> cdm = (CollectionMetadata<?>) dm;
		if (cdm.isArray()) {
		    Collection<?> collectionResp = (Collection<?>) response;
		    if (collectionResp.size() > 0) {
			return collectionResp.toArray();
		    }
		    return null;
		}
	    } else if (dm instanceof MapMetadata) {
		Map<?, ?> mapResp = (Map<?, ?>) response;
		if (mapResp.size() == 0) {
		    return null;
		}
	    }
	    return response;
	}
	return ReflectionUtility.createObjectByTypeAndValue(getType(), data);
    }

    // private JavaCollectionMapParser getCollectionMapParser() {
    // if (collectionMapParser != null) {
    // return collectionMapParser;
    // }
    //
    // collectionMapParser = new JavaCollectionMapParser();
    // if (implementation != null) {
    // MultivaluedDataModel<?, ?> mvdm = (MultivaluedDataModel) getDataModel();
    // if (mvdm.isCollection()) {
    // collectionMapParser.setCollectionImplType((Class<? extends Collection>)
    // implementation);
    // } else if (mvdm.isMap()) {
    // collectionMapParser.setMapImplType((Class<? extends Map>)
    // implementation);
    // }
    // }
    // return collectionMapParser;
    // }

    private CollectionMapParseEventHandler getFieldParseHandler() {
	if (fieldParseHandler == null) {
	    fieldParseHandler = new CollectionMapParseEventHandler();
	    Class<?> impl = implementation;
	    if (nestedMetadata instanceof CollectionMetadata) {
		CollectionMetadata<T> cdm = (CollectionMetadata<T>) nestedMetadata;

		if (impl == null) {
		    if (cdm.isSet()) {
			impl = DEFAULT_SET_IMPLEMENTATION;
		    } else {
			impl = DEFAULT_LIST_IMPLEMENTATION;
		    }
		}
		fieldParseHandler
			.setCollectionTypeDef(new MultivaluedTypeDefinition(
				impl, cdm.getType()));
	    } else { /* assume map type */
		MapMetadata<?, ?> mdm = (MapMetadata<?, ?>) nestedMetadata;
		if (impl == null) {
		    impl = DEFAULT_MAP_IMPLEMENTATION;
		}
		fieldParseHandler.setMapTypeDef(new MapTypeDefinition(impl, mdm
			.getKeyType(), mdm.getType()));
	    }
	}
	return fieldParseHandler;
    }

    public boolean validate(Object value) throws ValidationException {
	nestedMetadata.validate(value);
	if (isRequired() && value == null) {
	    throw new ValidationException(ValidationErrorType.REQUIRED,
		    new GenericNameValue(getName(), value));
	}
	return true;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the required
     */
    public boolean isRequired() {
	return required;
    }

    /**
     * @return the implementation
     */
    public Class<? extends T> getImplementation() {
	return implementation;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @param required
     *            the required to set
     */
    public void setRequired(boolean required) {
	this.required = required;
    }

    /**
     * @param implementation
     *            the implementation to set
     */
    public void setImplementation(Class<?> implementation) {
	this.implementation = (Class<? extends T>) implementation;
    }

    @Override
    public Class<T> getType() {
	return nestedMetadata.getType();
    }

    @Override
    public String getTypeName() {
	return nestedMetadata.getTypeName();
    }

    @Override
    public boolean isMultivalued() {
	return nestedMetadata.isMultivalued();
    }

    @Override
    public boolean isComplexType() {
	return nestedMetadata.isComplexType();
    }

    @Override
    public M getValueMetadata() {
	return nestedMetadata;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "FieldMetadataImpl [name=" + name + ", required=" + required
		+ ", implementation=" + implementation + ", nestedMetadata="
		+ nestedMetadata + "]";
    }

   

}
