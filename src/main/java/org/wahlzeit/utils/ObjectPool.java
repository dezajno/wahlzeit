package org.wahlzeit.utils;

import java.util.HashMap;
import java.util.Map;

public class ObjectPool<T> {
	protected final Map<T,T> objs = new HashMap<>();
	
	public ObjectPool() {
		
	}
	
	public synchronized T create(T newObj) {
		T obj = objs.get(newObj);
		if(obj == null) {
			obj = newObj;
			objs.put(obj, obj);
		}
		
		return obj;
	}
}
