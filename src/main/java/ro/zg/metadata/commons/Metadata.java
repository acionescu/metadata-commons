package ro.zg.metadata.commons;

import ro.zg.metadata.exceptions.ValidationException;


public interface Metadata<T> {
    String getId();
    Class<T> getType();
    String getTypeName();
    boolean validate(Object value) throws ValidationException;
    boolean isMultivalued();
    boolean isComplexType();
}
