package org.wahlzeit.model;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartesianCoordinateImplTest {
	private static final double DELTA = 0.00000001;
	
	private CartesianCoordinateImpl origin;
	private CartesianCoordinateImpl zero3;
	private CartesianCoordinateImpl one3;
	private CartesianCoordinateImpl one3c;
	private CartesianCoordinateImpl oneTwoThree;
	private CartesianCoordinateImpl oneTwoFour;
	private CartesianCoordinateImpl oneTwoTwo;
	
	private Object object;
	
	@Before
	public void setup() {
		origin = CartesianCoordinateImpl.ORIGIN;
		zero3 = new CartesianCoordinateImpl(0.0, 0.0, 0.0);
		one3 = new CartesianCoordinateImpl(1.0, 1.0, 1.0);
		one3c = new CartesianCoordinateImpl(one3);
		oneTwoThree = new CartesianCoordinateImpl(1.0, 2.0, 3.0);
		oneTwoFour = new CartesianCoordinateImpl(1.0, 2.0, 4.0);
		oneTwoTwo = new CartesianCoordinateImpl(1.0, 2.0, 2.0);
		
		object = new Object();
	}
	
	@Test
	public void testOrigin() {
		assertEquals(origin, zero3);
	}
	
	@Test
	public void testCopy() {
		assertEquals(one3, one3c);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullCopy() {
		new CartesianCoordinateImpl(null);
	}

	@Test
	public void testGetX() {
		assertEquals(0.0, origin.getX(), DELTA);
		assertEquals(0.0, zero3.getX(), DELTA);
		assertEquals(1.0, one3.getX(), DELTA);
		assertEquals(1.0, oneTwoThree.getX(), DELTA);
	}

	@Test
	public void testGetY() {
		assertEquals(0.0, origin.getY(), DELTA);
		assertEquals(0.0, zero3.getY(), DELTA);
		assertEquals(1.0, one3.getY(), DELTA);
		assertEquals(2.0, oneTwoThree.getY(), DELTA);
	}

	@Test
	public void testGetZ() {
		assertEquals(0.0, origin.getZ(), DELTA);
		assertEquals(0.0, zero3.getZ(), DELTA);
		assertEquals(1.0, one3.getZ(), DELTA);
		assertEquals(3.0, oneTwoThree.getZ(), DELTA);
	}

	@Test
	public void testIsEqual() {
		assertEquals(true, one3.isEqual(one3));
		assertEquals(true, one3.isEqual(one3c));
		assertEquals(false, one3.isEqual(oneTwoThree));
		assertEquals(false, one3.isEqual(null));
	}

	@Test
	public void testEquals() {
		assertEquals(false, one3.equals(object));
		assertEquals(true, one3.equals(one3));
		assertEquals(true, one3.equals(one3c));
		assertEquals(false, one3.equals(oneTwoThree));
		assertEquals(false, one3.equals(null));
	}

	@Test
	public void testGetDistance() {
		assertEquals(0.0, one3.getCartesianDistance(one3c), DELTA);
		assertEquals(0.0, origin.getCartesianDistance(zero3), DELTA);
		assertEquals(1.0, oneTwoFour.getCartesianDistance(oneTwoThree), DELTA);
		assertEquals(Math.sqrt(2.0), one3.getCartesianDistance(oneTwoTwo), DELTA);
	}

	@Test
	public void testAsSpheric() {
		assertTrue(origin.asSphericCoordinate() instanceof SphericCoordinate);
	}
}
