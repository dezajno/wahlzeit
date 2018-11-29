package org.wahlzeit.model;

import org.wahlzeit.utils.NumberUtil;

public abstract class AbstractCoordinate implements Coordinate {
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();

	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	protected void assertClassInvariants() {
		
	}
	
	private static double dot(CartesianCoordinate a, CartesianCoordinate b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	private static double length(CartesianCoordinate c) {
		return Math.sqrt(dot(c, c));
	}
	
	private static CartesianCoordinate minus(CartesianCoordinate a, CartesianCoordinate b) {
		return new CartesianCoordinate(a.x - b.x, a.y - b.y, a.z - b.z);
	}
	
	protected static void assertFinite(double v, String name) {
		if(!Double.isFinite(v)) {
			throw new IllegalArgumentException(name + " must not be NaN or Inf (is " + v + ")");
		}
	}
	
	protected double doGetCentralAngle(CartesianCoordinate other) {
		CartesianCoordinate thisCart = asCartesianCoordinate();
		
		return Math.acos(dot(thisCart, other) / (length(thisCart)*length(other))) * 180 / Math.PI;
	}
	
	@Override
	public double getCentralAngle(Coordinate other) {
		assertClassInvariants();
		
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return doGetCentralAngle(other.asCartesianCoordinate());
	}
	
	public double getCentralAngle(CartesianCoordinate other) {
		assertClassInvariants();
		
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return doGetCentralAngle(other);
	}

	@Override
	public double getCartesianDistance(Coordinate other) {
		assertClassInvariants();
		
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return length(minus(this.asCartesianCoordinate(), other.asCartesianCoordinate()));
	}
	
	protected boolean areEqual(CartesianCoordinate a, CartesianCoordinate b) {
		return
				NumberUtil.isEqual(a.x, b.x) &&
				NumberUtil.isEqual(a.y, b.y) &&
				NumberUtil.isEqual(a.z, b.z);
	}

	@Override
	public boolean isEqual(Coordinate other) {
		assertClassInvariants();
		
		if(other == null) {
			return false;
		}
		
		return areEqual(this.asCartesianCoordinate(), other.asCartesianCoordinate());
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) {
			return false;
		} else if(!(other instanceof Coordinate)) {
			return false;
		} else {
			return this.isEqual((Coordinate)other);
		}
	}
}
