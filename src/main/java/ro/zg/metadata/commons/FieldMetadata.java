package ro.zg.metadata.commons;

public interface FieldMetadata<T> extends Metadata<T> {

    String getName();

    Class<? extends T> getImplementation();

    Metadata<T> getValueMetadata();

    boolean isRequired();
    
    Object createValueFromString(String data) throws Exception;
}
