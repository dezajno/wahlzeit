package org.wahlzeit.model;

/**
 * Represents a 2D cartesian coordinate with double precision
 * @author dezajno
 *
 */
public class Coordinate {
	/**
	 * The (0,0) coordinate
	 */
	public static final Coordinate ORIGIN = new Coordinate(0.0, 0.0, 0.0);
	
	protected final double x, y, z;
	
	/**
	 * Creates a new coordinate with components {@code x} and {@code y}
	 * @param x The x component of the new coordinate
	 * @param y The y component of the new coordinate
	 */
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Creates a copy of another coordinate
	 * @param other The coordinate to create a copy of
	 */
	public Coordinate(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other coordinate may not be null");
		}
		
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	/**
	 * Returns a copy of the x component of this coordinate
	 * @return A copy of the x component of this coordinate
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Returns a copy of the y component of this coordinate
	 * @return A copy of the y component of this coordinate
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Returns a copy of the z component of this coordinate
	 * @return A copy of the z component of this coordinate
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Checks whether this coordinate is equal to {@code other}
	 * @param other The coordinate to check for equality to this coordinate
	 * @return {@code true} if this coordinate is equal to {@code other}, {@code false otherwise}
	 */
	public boolean isEqual(Coordinate other) {
		if(other == null) {
			return false;
		}
		
		return this.x == other.x && this.y == other.y && this.z == other.z;
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
	
	/**
	 * Calculates the distance from this coordinate to {@code other}
	 * @param other The coordinate of which the distance to this node shall be computed
	 * @return The distance from this coordinate to {@code other}
	 */
	public double getDistance(Coordinate other) {
		double diffX = other.x - this.x;
		double diffY = other.y - this.y;
		double diffZ = other.z - this.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}
}
