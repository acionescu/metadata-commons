package ro.zg.metadata.commons;

import ro.zg.metadata.exceptions.MetadataException;

public interface AnnotationMappersManager<C extends AnnotationProcessorContext<?,?>> {
    void map(C amc) throws MetadataException;
}
