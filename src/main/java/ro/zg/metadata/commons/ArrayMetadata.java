package ro.zg.metadata.commons;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

public class ArrayMetadata<T> extends MultivaluedTypeMetadata<Array, T> {

    public ArrayMetadata(GenericArrayType arrayType) {
	super(arrayType);
	Type type = arrayType.getGenericComponentType();
	init((Class<T>) getRawType(type), Array.class);
	this.array=true;
    }

}
