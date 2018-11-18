package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {
	public static SphericCoordinate fromCartesian(CartesianCoordinate cartesianCoordinate) {
		if(cartesianCoordinate == null) {
			throw new IllegalArgumentException("cartesianCoordinate may not be null");
		}
		
		double x = cartesianCoordinate.x;
		double y = cartesianCoordinate.y;
		double z = cartesianCoordinate.z;
		
		double radius = Math.sqrt(x*x + y*y + z*z);
		double polar = Math.acos(z / radius) * 180 / Math.PI;
		double azimuth = Math.atan2(y, x) * 180 / Math.PI;
		
		return new SphericCoordinate(radius, polar, azimuth);
	}
	
	protected final double radius, polar, azimuth;
	
	public SphericCoordinate(double radius, double polar, double azimuth) {
		this.radius = radius;
		this.polar = polar;
		this.azimuth = azimuth;
	}
	
	public SphericCoordinate(SphericCoordinate other) {
		this.radius = other.radius;
		this.polar = other.polar;
		this.azimuth = other.azimuth;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return CartesianCoordinate.fromSpheric(this);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return new SphericCoordinate(this);
	}

	@Override
	public double getCentralAngle(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return asCartesianCoordinate().getCentralAngle(other);
	}

	@Override
	public double getCartesianDistance(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return asCartesianCoordinate().getCartesianDistance(other.asCartesianCoordinate());
	}
	
	protected boolean doIsEqual(CartesianCoordinate other) {		
		return this.asCartesianCoordinate().isEqual(other.asCartesianCoordinate());
	}

	public boolean isEqual(SphericCoordinate other) {
		if(other == null) {
			return false;
		}
		
		return doIsEqual(other.asCartesianCoordinate());
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if(other == null) {
			return false;
		}
		
		return doIsEqual(other.asCartesianCoordinate());
	}

	public boolean isEqual(CartesianCoordinate other) {
		if(other == null) {
			return false;
		}
		
		return doIsEqual(other);
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
	public String toString() {
		return String.format("%s(%.2f, %.2f, %.2f)", this.getClass().getSimpleName(), radius, polar, azimuth);
	}
}
