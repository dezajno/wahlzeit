package org.wahlzeit.model;

/**
 * Represents a 2D cartesian coordinate with double precision
 *
 */
public class CartesianCoordinate implements Coordinate {
	public static final double DEFAULT_COMPARE_EPSILON = 0.000001;
	
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
	
	public double getCartesianDistance(double otherX, double otherY, double otherZ) {
		double diffX = otherX - this.x;
		double diffY = otherY - this.y;
		double diffZ = otherZ - this.z;
		return Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		return new CartesianCoordinate(this);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return SphericCoordinate.fromCartesian(this);
	}

	@Override
	public double getCentralAngle(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return doGetCentralAngle(other.asCartesianCoordinate());
	}
	
	public double getCentralAngle(CartesianCoordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		
		return doGetCentralAngle(other);
	}
	
	protected double doGetCentralAngle(CartesianCoordinate other) {
		return Math.acos(this.dot(other) / (getLength()*other.getLength())) * 180 / Math.PI;
	}
	
	/**
	 * Calculates the distance from this coordinate to {@code other}
	 * @param other The coordinate of which the distance to this node shall be computed
	 * @return The distance from this coordinate to {@code other}
	 */
	@Override
	public double getCartesianDistance(Coordinate other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null!");
		}
		
		CartesianCoordinate otherCart = other.asCartesianCoordinate();
		return getCartesianDistance(otherCart.x, otherCart.y, otherCart.z);
	}
	
	public double getLength() {
		return getCartesianDistance(ORIGIN);
	}
	
	public double dot(CartesianCoordinate other) {
		return x * other.x + y * other.y + z * other.z;
	}
	
	protected static boolean isDoubleEqual(double d1, double d2, double epsilon) {
		return Math.abs(d2 - d1) <= epsilon;
	}
	
	protected boolean doIsEqual(CartesianCoordinate other, double epsilon) {
		return
				isDoubleEqual(this.x, other.x, epsilon) &&
				isDoubleEqual(this.y, other.y, epsilon) &&
				isDoubleEqual(this.z, other.z, epsilon);
	}
	
	public boolean isEqual(CartesianCoordinate other, double epsilon) {
		if(other == null) {
			return false;
		}
		
		return doIsEqual(other, epsilon);
	}
	
	public boolean isEqual(Coordinate other, double epsilon) {
		if(other == null) {
			return false;
		}
		
		return doIsEqual(other.asCartesianCoordinate(), epsilon);
	}

	/**
	 * Checks whether this coordinate is equal to {@code other}
	 * @param other The coordinate to check for equality to this coordinate
	 * @return {@code true} if this coordinate is equal to {@code other}, {@code false otherwise}
	 */
	@Override
	public boolean isEqual(Coordinate other) {
		if(other == null) {
			return false;
		}
		
		return doIsEqual(other.asCartesianCoordinate(), DEFAULT_COMPARE_EPSILON);
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
	
	@Override
	public String toString() {
		return String.format("%s(%.2f, %.2f, %.2f)", this.getClass().getSimpleName(), x, y, z);
	}
}
