package org.wahlzeit.model;

public interface Coordinate {
	/**
	 * Converts this coordinate to a cartesian coordinate
	 * @return a copy of this coordinate in cartesian representation
	 * @throws CoordinateException 
	 */
	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException;

	/**
	 * Converts this coordinate to a spheric coordinate
	 * @return a copy of this coordinate in spheric representation
	 * @throws CoordinateException 
	 */
	public SphericCoordinate asSphericCoordinate() throws CoordinateException;
	
	/**
	 * Calculates the angle between this coordinate and the given {@code other} coordinate
	 * @param other the coordinate to which to calculate the angle
	 * @return the angle between this coordinate and {@code other}
	 * @throws CoordinateException 
	 */
	public double getCentralAngle(Coordinate other) throws CoordinateException;
	
	/**
	 * Returs the distance between this coordinate and the given {@code other} coordinate
	 * @param other the coordinate to which to calculate the cartesian distance
	 * @return the distance between this coordinate and {@code other}
	 * @throws CoordinateException 
	 */
	public double getCartesianDistance(Coordinate other) throws CoordinateException;
	
	/**
	 * Checks whether this coordinate is equal to the given {@code other} coordinate
	 * @param other the coordinate to check for equality to this coordinate
	 * @return true if this coordinate is equal to {@code other}, false otherwise
	 * @throws CoordinateException 
	 */
	public boolean isEqual(Coordinate other);
}
