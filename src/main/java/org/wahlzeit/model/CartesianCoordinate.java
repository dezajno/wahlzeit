package org.wahlzeit.model;

/**
 * Represents a 2D cartesian coordinate with double precision
 *
 */
public class CartesianCoordinate extends AbstractCoordinate {
	/**
	 * The (0,0) coordinate
	 */
	public static final CartesianCoordinate ORIGIN = new CartesianCoordinate(0.0, 0.0, 0.0);

	public static CartesianCoordinate fromSpheric(SphericCoordinate sphericCoordinate) {
		if(sphericCoordinate == null) {
			throw new IllegalArgumentException("sphericCoordinate may not be null");
		}
		
		double radius = sphericCoordinate.radius;
		double polar = sphericCoordinate.polar * Math.PI / 180;
		double azimuth = sphericCoordinate.azimuth * Math.PI / 180;
		
		double x = radius * Math.sin(polar) * Math.cos(azimuth);
		double y = radius * Math.sin(polar) * Math.sin(azimuth);
		double z = radius * Math.cos(polar);
		
		return new CartesianCoordinate(x, y, z);
	}
	
	protected final double x, y, z;
	
	/**
	 * Creates a new coordinate with components {@code x} and {@code y}
	 * @param x The x component of the new coordinate
	 * @param y The y component of the new coordinate
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Creates a copy of another coordinate
	 * @param other The coordinate to create a copy of
	 */
	public CartesianCoordinate(CartesianCoordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other coordinate may not be null");
		}
		
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return SphericCoordinate.fromCartesian(this);
	}
	
	@Override
	public String toString() {
		return String.format("%s(%.2f, %.2f, %.2f)", this.getClass().getSimpleName(), x, y, z);
	}
}
