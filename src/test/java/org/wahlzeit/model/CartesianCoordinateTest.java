package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.NumberUtil;

public class CartesianCoordinateTest {
	private static final double DOUBLE_COMPARE_TOLERANCE = NumberUtil.DOUBLE_COMPARE_TOLERANCE;
	
	private CartesianCoordinate origin;
	private CartesianCoordinate zero3;
	private CartesianCoordinate one3;
	private CartesianCoordinate one3c;
	private CartesianCoordinate oneTwoThree;
	private CartesianCoordinate oneTwoFour;
	private CartesianCoordinate oneTwoTwo;
	
	private CartesianCoordinate x;
	private CartesianCoordinate y;
	private CartesianCoordinate z;
	
	private Object object;
	
	@Before
	public void setup() throws CoordinateException {
		origin = CartesianCoordinate.ORIGIN;
		zero3 = new CartesianCoordinate(0.0, 0.0, 0.0);
		one3 = new CartesianCoordinate(1.0, 1.0, 1.0);
		one3c = new CartesianCoordinate(one3);
		oneTwoThree = new CartesianCoordinate(1.0, 2.0, 3.0);
		oneTwoFour = new CartesianCoordinate(1.0, 2.0, 4.0);
		oneTwoTwo = new CartesianCoordinate(1.0, 2.0, 2.0);
		
		x = new CartesianCoordinate(1, 0, 0);
		y = new CartesianCoordinate(0, 1, 0);
		z = new CartesianCoordinate(0, 0, 1);
		
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
	public void testNullCopy() throws CoordinateException {
		new CartesianCoordinate(null);
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
	public void testGetDistance() throws CoordinateException {
		assertEquals(0.0, one3.getCartesianDistance(one3c), DOUBLE_COMPARE_TOLERANCE);
		assertEquals(0.0, origin.getCartesianDistance(zero3), DOUBLE_COMPARE_TOLERANCE);
		assertEquals(1.0, oneTwoFour.getCartesianDistance(oneTwoThree), DOUBLE_COMPARE_TOLERANCE);
		assertEquals(Math.sqrt(2.0), one3.getCartesianDistance(oneTwoTwo), DOUBLE_COMPARE_TOLERANCE);
	}

	@Test
	public void testAsSpheric() throws CoordinateException {
		assertTrue(origin.asSphericCoordinate() instanceof SphericCoordinate);
		assertEquals(x, x.asSphericCoordinate().asCartesianCoordinate());
		assertEquals(y, y.asSphericCoordinate().asCartesianCoordinate());
		assertEquals(z, z.asSphericCoordinate().asCartesianCoordinate());
	}
}
