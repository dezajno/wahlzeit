package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertionUtil.assertFinite;
import static org.wahlzeit.utils.AssertionUtil.assertNotNull;

import org.wahlzeit.utils.NumberUtil;
import org.wahlzeit.utils.ObjectPool;

/**
 * Represents a 2D cartesian coordinate with double precision
 *
 */
public class CartesianCoordinate extends AbstractCoordinate {
	protected static final ObjectPool<CartesianCoordinate> POOL = new ObjectPool<>();
	
	/**
	 * The (0,0) coordinate
	 */
	public static final CartesianCoordinate ORIGIN;
	
	static {
		try {
			ORIGIN = new CartesianCoordinate(0.0, 0.0, 0.0);
		} catch (CoordinateException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Creates a CartesianCoordinate from a spheric coordinate
	 * @param sphericCoordinate the spheric coordinate from which to create a CartesianCoordinate
	 * @return the created CartesianCoordinate
	 * @throws CoordinateException 
	 */
	public static CartesianCoordinate fromSpheric(SphericCoordinate sphericCoordinate) throws CoordinateException {
		assertNotNull(sphericCoordinate, () -> new IllegalArgumentException("sphericCoordinate may not be null"));
		
		double radius = sphericCoordinate.radius;
		double polar = sphericCoordinate.polar * Math.PI / 180;
		double azimuth = sphericCoordinate.azimuth * Math.PI / 180;
		
		double x = radius * Math.sin(polar) * Math.cos(azimuth);
		double y = radius * Math.sin(polar) * Math.sin(azimuth);
		double z = radius * Math.cos(polar);
		
		// According to the javadoc of Math.cos and Math.sin, violation of the following
		// conditions is not possible.
		assert Double.isFinite(x);
		assert Double.isFinite(y);
		assert Double.isFinite(z);
		
		CartesianCoordinate cart = new CartesianCoordinate(x, y, z);
		
		return cart;
	}
	
	public static CartesianCoordinate getInstance(double x, double y, double z) throws CoordinateException {
		return POOL.create(new CartesianCoordinate(x, y, z));
	}
	
	protected final double x, y, z;
	
	/**
	 * Creates a new coordinate with components {@code x} and {@code y}
	 * @param x The x component of the new coordinate
	 * @param y The y component of the new coordinate
	 * @throws CoordinateException 
	 */
	protected CartesianCoordinate(double x, double y, double z) throws CoordinateException {
		assertFinite(x, (v) -> new IllegalArgumentException("x may not be NaN or Inf (was " + v + ")"));
		assertFinite(y, (v) -> new IllegalArgumentException("y may not be NaN or Inf (was " + v + ")"));
		assertFinite(z, (v) -> new IllegalArgumentException("z may not be NaN or Inf (was " + v + ")"));

		this.x = x;
		this.y = y;
		this.z = z;
		
		assertClassInvariants();
	}
	
	/**
	 * Creates a copy of another coordinate
	 * @param other The coordinate to create a copy of
	 * @throws CoordinateException 
	 */
	protected CartesianCoordinate(CartesianCoordinate other) throws CoordinateException {
		assertNotNull(other, () -> new IllegalArgumentException("other coordinate may not be null"));
		
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
		
		assertClassInvariants();
	}
	
	@Override
	protected void assertClassInvariants() throws CoordinateException {
		super.assertClassInvariants();
		
		assertFinite(x, (v) -> new CoordinateException("x may not become NaN or Inf (was " + v + ")"));
		assertFinite(y, (v) -> new CoordinateException("y may not become NaN or Inf (was " + v + ")"));
		assertFinite(z, (v) -> new CoordinateException("z may not become NaN or Inf (was " + v + ")"));
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
	 * @throws CoordinateException 
	 */
	public CartesianCoordinate minus(CartesianCoordinate other) throws CoordinateException {
		assertNotNull(other, () -> new IllegalArgumentException("other may not be null"));
		return new CartesianCoordinate(x - other.x, y - other.y, z - other.z);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() throws CoordinateException {
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
