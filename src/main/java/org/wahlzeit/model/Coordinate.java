package org.wahlzeit.model;

public interface Coordinate {
	/**
	 * Converts this coordinate to a cartesian coordinate
	 * @return a copy of this coordinate in cartesian representation
	 */
	public CartesianCoordinate asCartesianCoordinate();

	/**
	 * Converts this coordinate to a spheric coordinate
	 * @return a copy of this coordinate in spheric representation
	 */
	public SphericCoordinate asSphericCoordinate();
	
	/**
	 * Calculates the angle between this coordinate and the given {@code other} coordinate
	 * @param other the coordinate to which to calculate the angle
	 * @return the angle between this coordinate and {@code other}
	 */
	public double getCentralAngle(Coordinate other);
	
	/**
	 * Returs the distance between this coordinate and the given {@code other} coordinate
	 * @param other the coordinate to which to calculate the cartesian distance
	 * @return the distance between this coordinate and {@code other}
	 */
	public double getCartesianDistance(Coordinate other);
	
	/**
	 * Checks whether this coordinate is equal to the given {@code other} coordinate
	 * @param other the coordinate to check for equality to this coordinate
	 * @return true if this coordinate is equal to {@code other}, false otherwise
	 */
	public boolean isEqual(Coordinate other);
}
