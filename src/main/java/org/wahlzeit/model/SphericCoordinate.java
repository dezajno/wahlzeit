package org.wahlzeit.model;

import org.wahlzeit.utils.NumberUtil;

public class SphericCoordinate extends AbstractCoordinate {
	public static SphericCoordinate fromCartesian(CartesianCoordinate cartesianCoordinate) {
		if(cartesianCoordinate == null) {
			throw new IllegalArgumentException("cartesianCoordinate may not be null");
		}
		
		cartesianCoordinate.assertClassInvariants();
		
		double x = cartesianCoordinate.x;
		double y = cartesianCoordinate.y;
		double z = cartesianCoordinate.z;
		
		double radius = Math.sqrt(x*x + y*y + z*z);
		double polar = 0.0;
		double azimuth = 0.0;
		
		if(!NumberUtil.isEqual(radius, 0.0)) {
			polar = Math.acos(z / radius) * 180 / Math.PI;
			azimuth = Math.atan2(y, x) * 180 / Math.PI;
		}
		
		assert Double.isFinite(radius);
		assert Double.isFinite(polar);
		assert Double.isFinite(azimuth);
		
		SphericCoordinate spheric = new SphericCoordinate(radius, polar, azimuth);
		
		spheric.assertClassInvariants();
		
		return spheric;
	}
	
	protected final double radius, polar, azimuth;
	
	public SphericCoordinate(double radius, double polar, double azimuth) {
		assertFinite(radius, "radius");
		assertFinite(polar, "polar");
		assertFinite(azimuth, "azimuth");
		
		this.radius = radius;
		this.polar = polar;
		this.azimuth = azimuth;
	}
	
	public SphericCoordinate(SphericCoordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		other.assertClassInvariants();

		this.radius = other.radius;
		this.polar = other.polar;
		this.azimuth = other.azimuth;
	}
	
	@Override
	protected void assertClassInvariants() {
		super.assertClassInvariants();
		
		assert Double.isFinite(azimuth);		
		assert Double.isFinite(polar);		
		assert Double.isFinite(radius);
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
