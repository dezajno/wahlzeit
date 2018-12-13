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
		origin = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		zero3 = CartesianCoordinate.getInstance(0.0, 0.0, 0.0);
		one3 = CartesianCoordinate.getInstance(1.0, 1.0, 1.0);
		one3c = CartesianCoordinate.getInstance(1.0, 1.0, 1.0);
		oneTwoThree = CartesianCoordinate.getInstance(1.0, 2.0, 3.0);
		oneTwoFour = CartesianCoordinate.getInstance(1.0, 2.0, 4.0);
		oneTwoTwo = CartesianCoordinate.getInstance(1.0, 2.0, 2.0);
		
		x = CartesianCoordinate.getInstance(1, 0, 0);
		y = CartesianCoordinate.getInstance(0, 1, 0);
		z = CartesianCoordinate.getInstance(0, 0, 1);
		
		object = new Object();
	}
	
	@Test
	public void testOrigin() {
		assertEquals(origin, zero3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNaN() throws CoordinateException {
		CartesianCoordinate.getInstance(Double.NaN, 0.0, 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPositiveInfinity() throws CoordinateException {
		CartesianCoordinate.getInstance(Double.POSITIVE_INFINITY, 0.0, 0.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeInfinity() throws CoordinateException {
		CartesianCoordinate.getInstance(Double.NEGATIVE_INFINITY, 0.0, 0.0);
	}
	
	@Test
	public void testShared() throws CoordinateException {
		assertTrue(one3 == one3c);
		
		for(CartesianCoordinate c : new CartesianCoordinate[] {
			origin,
			zero3,
			one3,
			oneTwoThree,
			oneTwoFour,
			oneTwoTwo,
			x,
			y,
			z
		}) {
			assertTrue(c == CartesianCoordinate.getInstance(c.x, c.y, c.z));
		}
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
