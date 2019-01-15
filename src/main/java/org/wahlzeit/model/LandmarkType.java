package org.wahlzeit.model;

import java.util.Objects;

import org.wahlzeit.utils.AssertionUtil;
import org.wahlzeit.utils.ObjectPool;

public class LandmarkType {
	private static final long serialVersionUID = 4382947892375L;

	protected static transient final ObjectPool<LandmarkType> POOL = new ObjectPool<>();
	
	protected LandmarkType superType;
	protected String id;
	
	protected LandmarkType(String id, LandmarkType superType) {
		this.id = id;
		this.superType = superType;
	}
	
	protected static LandmarkType doGetInstance(String id, LandmarkType superType) {
		return POOL.storeOrGet(new LandmarkType(id, superType));
	}
	
	public static LandmarkType getInstance(String id, LandmarkType superType) {
		AssertionUtil.assertNotNull(id, () -> new IllegalArgumentException("id may not be null"));
		AssertionUtil.assertNotNull(superType, () -> new IllegalArgumentException("superType may not be null"));
		
		LandmarkType type = doGetInstance(id, superType);
		AssertionUtil.assertPredicate((v) -> v.equals(type.superType), superType, (v) -> new IllegalArgumentException("Type " + v + " already exists with another subtype"));
		
		return type;
	}
	
	public static LandmarkType getInstance(String id) {
		AssertionUtil.assertNotNull(id, () -> new IllegalArgumentException("id may not be null"));
		
		return doGetInstance(id, null);
	}
	
	public LandmarkType getSuperType() {
		return superType;
	}
	
	public boolean isSubType() {
		return superType != null;
	}
	
	public boolean isDirectSubTypeOf(LandmarkType otherType) {
		AssertionUtil.assertNotNull(otherType, () -> new IllegalArgumentException("otherType may not be null!"));
		
		return otherType.equals(superType);
	}
	
	public boolean isSubTypeOf(LandmarkType otherType) {
		AssertionUtil.assertNotNull(otherType, () -> new IllegalArgumentException("otherType may not be null!"));
		
		LandmarkType curType = this;
		
		while((curType = curType.superType) != null) {
			if(otherType.equals(curType)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LandmarkType other = (LandmarkType) obj;
		return Objects.equals(id, other.id);
	}
}
