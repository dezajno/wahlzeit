package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {
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
	public String toString() {
		return String.format("%s(%.2f, %.2f, %.2f)", this.getClass().getSimpleName(), radius, polar, azimuth);
	}
}
