package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertionUtil.assertNotNull;

public abstract class AbstractCoordinate implements Coordinate {
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();

	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	/**
	 * Asserts that the invariants of this class are still fulfilled. If not, throws an {@link IllegalStateException}
	 * @throws IllegalStateException if class invariants are violated
	 */
	protected void assertClassInvariants() throws IllegalStateException {
		
	}
	
	protected double doGetCentralAngle(CartesianCoordinate other) {
		CartesianCoordinate thisCart = asCartesianCoordinate();
		
		return Math.acos(thisCart.dot(other) / (thisCart.length()*other.length())) * 180 / Math.PI;
	}
	
	@Override
	public double getCentralAngle(Coordinate other) {
		assertClassInvariants();
		
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		return doGetCentralAngle(other.asCartesianCoordinate());
	}
	
	public double getCentralAngle(CartesianCoordinate other) {
		assertClassInvariants();

		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		return doGetCentralAngle(other);
	}

	@Override
	public double getCartesianDistance(Coordinate other) {
		assertClassInvariants();

		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		return this.asCartesianCoordinate().minus(other.asCartesianCoordinate()).length();
	}

	@Override
	public boolean isEqual(Coordinate other) {
		assertClassInvariants();
		
		if(other == null) {
			return false;
		}
		
		return this.asCartesianCoordinate().isEqual(other.asCartesianCoordinate());
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
