package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {
	public static final double DEFAULT_COMPARE_EPSILON = 0.000001;
	
	@Override
	public abstract CartesianCoordinate asCartesianCoordinate();

	@Override
	public abstract SphericCoordinate asSphericCoordinate();
	
	private static double dot(CartesianCoordinate a, CartesianCoordinate b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	private static double length(CartesianCoordinate c) {
		return Math.sqrt(dot(c, c));
	}
	
	private static CartesianCoordinate minus(CartesianCoordinate a, CartesianCoordinate b) {
		return new CartesianCoordinate(a.x - b.x, a.y - b.y, a.z - b.z);
	}
	
	protected double doGetCentralAngle(CartesianCoordinate other) {
		CartesianCoordinate thisCart = asCartesianCoordinate();
		
		return Math.acos(dot(thisCart, other) / (length(thisCart)*length(other))) * 180 / Math.PI;
	}
	
	@Override
	public double getCentralAngle(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return doGetCentralAngle(other.asCartesianCoordinate());
	}
	
	public double getCentralAngle(CartesianCoordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return doGetCentralAngle(other);
	}

	@Override
	public double getCartesianDistance(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return length(minus(this.asCartesianCoordinate(), other.asCartesianCoordinate()));
	}
	
	protected static boolean isDoubleEqual(double d1, double d2, double epsilon) {
		return Math.abs(d2 - d1) <= epsilon;
	}
	
	protected boolean areEqual(CartesianCoordinate a, CartesianCoordinate b, double epsilon) {
		return
				isDoubleEqual(a.x, b.x, epsilon) &&
				isDoubleEqual(a.y, b.y, epsilon) &&
				isDoubleEqual(a.z, b.z, epsilon);
	}
	
	public boolean isEqual(CartesianCoordinate other, double epsilon) {
		if(other == null) {
			return false;
		}
		
		return areEqual(this.asCartesianCoordinate(), other.asCartesianCoordinate(), epsilon);
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if(other == null) {
			return false;
		}
		
		return areEqual(this.asCartesianCoordinate(), other.asCartesianCoordinate(), DEFAULT_COMPARE_EPSILON);
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
