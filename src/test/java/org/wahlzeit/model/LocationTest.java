package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	private Location someCoord;
	private Location someCoordCopied;
	private Location otherCoordGiven;
	
	@Before
	public void setup() throws CoordinateException {
		CartesianCoordinate testCoord = new CartesianCoordinate(4.2, 0.1, 3.4);
		
		someCoord = new Location(testCoord);
		someCoordCopied = new Location(someCoord);
		otherCoordGiven = new Location(CartesianCoordinate.ORIGIN);
	}
	
	@Test
	public void testCopy() {
		assertEquals(someCoord, someCoordCopied);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullCopy() {
		new Location((Location)null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullCoord() {
		new Location((CartesianCoordinate)null);
	}
	
	@Test
	public void testGetCoord() {
		assertEquals(someCoord.getCoord(), someCoordCopied.getCoord());
		assertNotEquals(someCoord.getCoord(), otherCoordGiven.getCoord());
	}
}
