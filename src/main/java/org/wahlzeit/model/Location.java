package org.wahlzeit.model;

import java.util.Optional;

/**
 * Represents a location
 * @author dezajno
 *
 */
public class Location {
	protected final Coordinate coord;

	/**
	 * Creates a new location with the given coordinate
	 * @param coord The coordinate of the new location
	 */
	public Location(Coordinate coord) {
		if(coord == null) {
			throw new IllegalArgumentException("coord may not be null");
		}
		this.coord = coord;
	}
	
	/**
	 * Creates a copy of a given location
	 * @param other The location to create a copy of
	 */
	public Location(Location other) {
		if(other == null) {
			throw new IllegalArgumentException("other may not be null");
		}
		this.coord = other.coord;
	}
	
	/**
	 * Returns the coordinate of this location
	 * @return The coordinate of this location
	 */
	public Coordinate getCoord() {
		return coord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		return true;
	}
}
