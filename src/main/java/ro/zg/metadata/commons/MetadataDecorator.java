package ro.zg.metadata.commons;

public class MetadataDecorator<T, M extends Metadata<T>> extends MetadataImpl<T> {
    protected M nestedMetadata;

    /**
     * @return the nestedMetadata
     */
    public M getNestedMetadata() {
        return nestedMetadata;
    }

    /**
     * @param nestedMetadata the nestedMetadata to set
     */
    public void setNestedMetadata(M nestedMetadata) {
        this.nestedMetadata = nestedMetadata;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "MetadataDecorator [nestedMetadata=" + nestedMetadata + "]";
    }

    
    
}
