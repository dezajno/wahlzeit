package org.wahlzeit.model;

import java.util.Objects;

import org.wahlzeit.utils.AssertionUtil;
import org.wahlzeit.utils.ObjectPool;
	
public class Landmark {
	private static final long serialVersionUID = 84390275893024L;
	
	protected static transient final ObjectPool<Landmark> POOL = new ObjectPool<>();
	
	protected LandmarkType type;
	
	protected Landmark(LandmarkType type) {
		this.type = type;
	}
	
	public static Landmark getInstance(LandmarkType type) {
		AssertionUtil.assertNotNull(type, () -> new IllegalArgumentException("type may not be null"));
		
		return POOL.storeOrGet(new Landmark(type));
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Landmark other = (Landmark) obj;
		return Objects.equals(type, other.type);
	}
}
