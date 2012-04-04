package ro.zg.metadata.commons;

import java.util.HashMap;
import java.util.Map;

public class MultitypeMetadata<T,M extends Metadata<?>> extends MetadataImpl<T>{
    private Map<String, M> metadatas=new HashMap<String, M>();

    /**
     * @return the metadatas
     */
    public Map<String, M> getMetadatas() {
        return metadatas;
    }

    /**
     * @param metadatas the metadatas to set
     */
    public void setMetadatas(Map<String, M> metadatas) {
        this.metadatas = metadatas;
    }
    
    public M getMetadataByType(String type) {
	return metadatas.get(type);
    }
    
    public void addMetadataForType(M metadata, String type) {
	metadatas.put(type, metadata);
    }
}
