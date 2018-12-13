package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertionUtil.assertFinite;
import static org.wahlzeit.utils.AssertionUtil.assertNotNull;

import java.util.Objects;

import org.wahlzeit.utils.NumberUtil;
import org.wahlzeit.utils.ObjectPool;

public class SphericCoordinate extends AbstractCoordinate {
	protected static final ObjectPool<SphericCoordinate> POOL = new ObjectPool<>();

	/**
	 * Creates a SphericCoordinate from a cartesian coordinate
	 * @param cartesianCoordinate the cartesian coordinate from which to create a SphericCoordinate
	 * @return the created SphericCoordinate
	 * @throws CoordinateException 
	 */
	public static SphericCoordinate fromCartesian(CartesianCoordinate cartesianCoordinate) throws CoordinateException {
		assertNotNull(cartesianCoordinate, () -> new IllegalArgumentException("cartesianCoordinate may not be null"));
		
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
		
		// According to the javadoc of Math.cos and Math.atan2, violation of the following
		// conditions is not possible.
		assert Double.isFinite(radius);
		assert Double.isFinite(polar);
		assert Double.isFinite(azimuth);
		
		SphericCoordinate spheric = SphericCoordinate.getInstance(radius, polar, azimuth);
		
		return spheric;
	}
	
	public static SphericCoordinate getInstance(double radius, double polar, double azimuth) throws CoordinateException {
		assertFinite(azimuth, (v) -> new IllegalArgumentException("azimuth may not be NaN or Inf (was " + v + ")"));
		assertFinite(polar, (v) -> new IllegalArgumentException("polar may not be NaN or Inf (was " + v + ")"));
		assertFinite(radius, (v) -> new IllegalArgumentException("radius may not be NaN or Inf (was " + v + ")"));
		
		SphericCoordinate coord = POOL.storeOrGet(new SphericCoordinate(radius, polar, azimuth));
		
		coord.assertClassInvariants();
		
		return coord;
	}
	
	protected final double radius, polar, azimuth;
	
	/**
	 * Creates a new spheric coordinate with the given {@code radius} and {@code polar}- and {@code azimuth}-angles
	 * @param radius the radius of the new coordinate
	 * @param polar the polar angle of the new coordinate
	 * @param azimuth the azimuth angle of the new coordinate
	 * @throws CoordinateException 
	 */
	protected SphericCoordinate(double radius, double polar, double azimuth) throws CoordinateException {
		this.radius = radius;
		this.polar = polar;
		this.azimuth = azimuth;
	}
	
	@Override
	protected void assertClassInvariants() throws CoordinateException {
		super.assertClassInvariants();
		
		assertFinite(azimuth, (v) -> new CoordinateException("azimuth may not become NaN or Inf (was " + v + ")"));
		assertFinite(polar, (v) -> new CoordinateException("polar may not become NaN or Inf (was " + v + ")"));
		assertFinite(radius, (v) -> new CoordinateException("radius may not become NaN or Inf (was " + v + ")"));
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException {
		return CartesianCoordinate.fromSpheric(this);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%.2f, %.2f, %.2f)", this.getClass().getSimpleName(), radius, polar, azimuth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(radius, polar, azimuth);
	}
}
