package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertionUtil.assertFinite;
import static org.wahlzeit.utils.AssertionUtil.assertNotNull;

import org.wahlzeit.utils.NumberUtil;

/**
 * Represents a 2D cartesian coordinate with double precision
 *
 */
public class CartesianCoordinate extends AbstractCoordinate {
	/**
	 * The (0,0) coordinate
	 */
	public static final CartesianCoordinate ORIGIN = new CartesianCoordinate(0.0, 0.0, 0.0);

	/**
	 * Creates a CartesianCoordinate from a spheric coordinate
	 * @param sphericCoordinate the spheric coordinate from which to create a CartesianCoordinate
	 * @return the created CartesianCoordinate
	 */
	public static CartesianCoordinate fromSpheric(SphericCoordinate sphericCoordinate) {
		assertNotNull(sphericCoordinate, () -> new IllegalArgumentException("sphericCoordinate may not be null"));
		
		sphericCoordinate.assertClassInvariants();
		
		double radius = sphericCoordinate.radius;
		double polar = sphericCoordinate.polar * Math.PI / 180;
		double azimuth = sphericCoordinate.azimuth * Math.PI / 180;
		
		double x = radius * Math.sin(polar) * Math.cos(azimuth);
		double y = radius * Math.sin(polar) * Math.sin(azimuth);
		double z = radius * Math.cos(polar);
		
		CartesianCoordinate cart = new CartesianCoordinate(x, y, z);
		
		cart.assertClassInvariants();
		
		return cart;
	}
	
	protected final double x, y, z;
	
	/**
	 * Creates a new coordinate with components {@code x} and {@code y}
	 * @param x The x component of the new coordinate
	 * @param y The y component of the new coordinate
	 */
	public CartesianCoordinate(double x, double y, double z) {
		assertFinite(x, (v) -> new IllegalArgumentException("x may not be NaN or Inf (was " + v + ")"));
		assertFinite(y, (v) -> new IllegalArgumentException("y may not be NaN or Inf (was " + v + ")"));
		assertFinite(z, (v) -> new IllegalArgumentException("z may not be NaN or Inf (was " + v + ")"));

		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Creates a copy of another coordinate
	 * @param other The coordinate to create a copy of
	 */
	public CartesianCoordinate(CartesianCoordinate other) {
		assertNotNull(other, () -> new IllegalArgumentException("other coordinate may not be null"));
		
		other.assertClassInvariants();
		
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	@Override
	protected void assertClassInvariants() throws IllegalStateException {
		super.assertClassInvariants();
		
		assertFinite(x, (v) -> new IllegalStateException("x may not become NaN or Inf (was " + v + ")"));
		assertFinite(y, (v) -> new IllegalStateException("y may not become NaN or Inf (was " + v + ")"));
		assertFinite(z, (v) -> new IllegalStateException("z may not become NaN or Inf (was " + v + ")"));
	}
	
	/**
	 * Calculates the dot product of this coordinate and the given {@code other} coordinate
	 * @param other the coordinate to which to calculate the dot product
	 * @return this.x * other.x + this.y * other.y + this.z * other.z
	 */
	public double dot(CartesianCoordinate other) {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		return x * other.x + y * other.y + z * other.z;
	}
	
	/**
	 * Calculates the euclidean length (l2 norm) of this coordinate
	 * @return the euclidean length (l2 norm) of this coordinate
	 */
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	/**
	 * Subtracts {@code other} from this coordinate and returns the result
	 * @param other the coordinate to subtract from this coordinate
	 * @return this - other
	 */
	public CartesianCoordinate minus(CartesianCoordinate other) {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		return new CartesianCoordinate(x - other.x, y - other.y, z - other.z);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return SphericCoordinate.fromCartesian(this);
	}
	
	/**
	 * Checks if this cartesian coordinate is equal to the given cartesian coordinate
	 * @param other the coordinate to compare to this coordiante
	 * @return true if this coordinate is equal to {@code other}, false otherwise
	 */
	public boolean isEqual(CartesianCoordinate other) {
		if(other == null) {
			return false;
		}
		
		return
			NumberUtil.isEqual(x, other.x) &&
			NumberUtil.isEqual(y, other.y) &&
			NumberUtil.isEqual(z, other.z);
	}
	
	@Override
	public String toString() {
		return String.format("%s(%.2f, %.2f, %.2f)", this.getClass().getSimpleName(), x, y, z);
	}
}
