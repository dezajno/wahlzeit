package org.wahlzeit.model;

import org.wahlzeit.utils.NumberUtil;

import static org.wahlzeit.utils.AssertionUtil.*;

public class SphericCoordinate extends AbstractCoordinate {
	/**
	 * Creates a SphericCoordinate from a cartesian coordinate
	 * @param cartesianCoordinate the cartesian coordinate from which to create a SphericCoordinate
	 * @return the created SphericCoordinate
	 */
	public static SphericCoordinate fromCartesian(CartesianCoordinate cartesianCoordinate) {
		assertNotNull(cartesianCoordinate, () -> new IllegalArgumentException("cartesianCoordinate may not be null"));
		
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
	
	/**
	 * Creates a new spheric coordinate with the given {@code radius} and {@code polar}- and {@code azimuth}-angles
	 * @param radius the radius of the new coordinate
	 * @param polar the polar angle of the new coordinate
	 * @param azimuth the azimuth angle of the new coordinate
	 */
	public SphericCoordinate(double radius, double polar, double azimuth) {
		assertFinite(azimuth, (v) -> new IllegalArgumentException("azimuth may not be NaN or Inf (was " + v + ")"));
		assertFinite(polar, (v) -> new IllegalArgumentException("polar may not be NaN or Inf (was " + v + ")"));
		assertFinite(radius, (v) -> new IllegalArgumentException("radius may not be NaN or Inf (was " + v + ")"));
		
		this.radius = radius;
		this.polar = polar;
		this.azimuth = azimuth;
	}
	
	/**
	 * Creates a copy of the given {@code other} coordinate
	 * @param other the coordinate of which to create a copy
	 */
	public SphericCoordinate(SphericCoordinate other) {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		
		other.assertClassInvariants();

		this.radius = other.radius;
		this.polar = other.polar;
		this.azimuth = other.azimuth;
	}
	
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		super.assertClassInvariants();
		
		assertFinite(azimuth, (v) -> new IllegalStateException("azimuth may not become NaN or Inf (was " + v + ")"));
		assertFinite(polar, (v) -> new IllegalStateException("polar may not become NaN or Inf (was " + v + ")"));
		assertFinite(radius, (v) -> new IllegalStateException("radius may not become NaN or Inf (was " + v + ")"));
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
