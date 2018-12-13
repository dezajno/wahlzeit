package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertionUtil.assertNotNull;

public abstract class AbstractCoordinate implements Coordinate {
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate() throws CoordinateException;

	@Override
	public abstract SphericCoordinate asSphericCoordinate() throws CoordinateException;
	
	/**
	 * Asserts that the invariants of this class are still fulfilled. If not, throws an {@link IllegalStateException}
	 * @throws IllegalStateException if class invariants are violated
	 */
	protected void assertClassInvariants() throws CoordinateException {
		
	}
	
	protected double doGetCentralAngle(CartesianCoordinate other) throws CoordinateException {
		CartesianCoordinate thisCart = asCartesianCoordinate();
		
		return Math.acos(thisCart.dot(other) / (thisCart.length()*other.length())) * 180 / Math.PI;
	}
	
	@Override
	public double getCentralAngle(Coordinate other) throws CoordinateException {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		return doGetCentralAngle(other.asCartesianCoordinate());
	}
	
	public double getCentralAngle(CartesianCoordinate other) throws CoordinateException {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		return doGetCentralAngle(other);
	}

	@Override
	public double getCartesianDistance(Coordinate other) throws CoordinateException {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		return this.asCartesianCoordinate().minus(other.asCartesianCoordinate()).length();
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if(other == null) {
			return false;
		}
		
		try {
			return this.asCartesianCoordinate().isEqual(other.asCartesianCoordinate());
		} catch (CoordinateException e) {
			return false;
		}
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
	
	@Override
	public abstract int hashCode();
}
