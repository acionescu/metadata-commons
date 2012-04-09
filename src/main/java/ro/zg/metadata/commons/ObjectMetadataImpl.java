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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import ro.zg.metadata.constants.MetadataConstants;
import ro.zg.metadata.exceptions.MetadataErrorType;
import ro.zg.metadata.exceptions.MetadataException;
import ro.zg.util.data.GenericNameValue;
import ro.zg.util.data.reflection.ReflectionUtility;

//@Persistable
public class ObjectMetadataImpl<T,F extends FieldMetadata<?>> extends MetadataImpl<T> implements ObjectMetadata<T, F>{
//    @Implementation(type = LinkedHashMap.class)
//    @Link(name = "object_type_fields", key = "name", lazy = false)
    protected Map<String, F> fields;
    
    public ObjectMetadataImpl() {
	super();
	// TODO Auto-generated constructor stub
    }

    public ObjectMetadataImpl(Class<T> type) {
	super(type,true);
	// TODO Auto-generated constructor stub
    }
    
    public boolean acceptField(F fieldMetadata) {
	if(isFieldElligible(fieldMetadata)) {
	    addFieldMetadata(fieldMetadata);
	    return true;
	}
	return false;
    }
    
    /**
     * to be overwritten by subclass
     * @param fieldMetadata
     * @return
     */
    protected boolean isFieldElligible(F fieldMetadata) {
	return (fieldMetadata instanceof FieldMetadata );
    }
    
    private void initFields() {
	fields = new LinkedHashMap<String, F>();
    }
    
    protected void addFieldMetadata(F fiedlMetadata) {
	if(fields==null) {
	    initFields();
	}
	String fieldName = fiedlMetadata.getName();
	fields.put(fieldName, fiedlMetadata);
    }
    

    public F getField(String name) {

	int nestedIndex = name.indexOf(MetadataConstants.NESTED_FIELD_SEPARATOR);
	if (nestedIndex <= 0 || nestedIndex == (name.length() - 1)) {
	    return fields.get(name);
	}
	String mainField = name.substring(0, nestedIndex);
	F currentField = fields.get(mainField);
	if (currentField != null) {
	    return ((ObjectMetadata<?,F>) currentField.getValueMetadata()).getField(name.substring(nestedIndex + 1));
	}
	return null;
    }
    
    public ObjectMetadata<?,F> getObjectDataModelForField(String fieldName) {
	F fdm = getField(fieldName);
	if (fdm == null) {
	    return null;
	}
	Metadata<?> dm = fdm.getValueMetadata();
	if (dm.isComplexType()) {
	    return (ObjectMetadata<?,F>) dm;
	}
	return null;
    }
    
    public Map<String, Object> getFieldValuesMap(T target) throws MetadataException {
	Map<String, Object> valuesMap = new HashMap<String, Object>();
	for (F fdm : fields.values()) {
	    String fieldName = fdm.getName();
	    try {
		valuesMap.put(fieldName, getValueForField(target, fieldName));
	    } catch (Exception e) {
		throw new MetadataException(MetadataErrorType.GET_FIELD_ERROR, new GenericNameValue("type", getType()),
			new GenericNameValue("field", fieldName));
	    }
	}
	return valuesMap;
    }
    
    
    public Object getValueForField(T target, String fieldName) throws MetadataException {
	try {
	    return ReflectionUtility.getValueForField(target, fieldName);
	} catch (Exception e) {
	    throw new MetadataException(e, MetadataErrorType.GET_FIELD_ERROR, new GenericNameValue("field", fieldName),
		    new GenericNameValue("target", target));
	}
    }
    
    public void setFieldValue(Object target, String fieldName, String fieldValue) throws MetadataException {
	try {
	    ReflectionUtility.setValueToField(target, fieldName, fieldValue);
	} catch (Exception e) {
	    throw new MetadataException(e, MetadataErrorType.SET_FIELD_ERROR, new GenericNameValue(fieldName, fieldValue));
	}
    }
    
    /**
     * @return the fields
     */
    public Map<String, F> getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(Map<String, F> fields) {
        this.fields = fields;
    }

}
