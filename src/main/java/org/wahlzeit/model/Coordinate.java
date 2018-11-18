package org.wahlzeit.model;

public interface Coordinate {
	public CartesianCoordinate asCartesianCoordinate();
	public SphericCoordinate asSphericCoordinate();
	public double getCentralAngle(Coordinate other);
	public double getCartesianDistance(Coordinate other);
	public boolean isEqual(Coordinate other);
}
