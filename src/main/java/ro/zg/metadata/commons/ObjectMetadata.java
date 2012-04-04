package ro.zg.metadata.commons;

import java.util.Map;

import ro.zg.metadata.exceptions.MetadataException;

public interface ObjectMetadata<T,F extends FieldMetadata<?>> extends Metadata<T>  {
    Map<String,F> getFields();
    F getField(String name);
    ObjectMetadata<?,F> getObjectDataModelForField(String fieldName);
    Map<String, Object> getFieldValuesMap(T target) throws MetadataException;
    void setFieldValue(Object target, String fieldName, String fieldValue) throws MetadataException;
    boolean acceptField(F fieldMetadata);
}
