package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SphericCoordinateTest {
	private static final double DELTA = 0.00000001;
	
	private SphericCoordinate c1;
	private SphericCoordinate equalToC1;
	
	private SphericCoordinate zero;
	private SphericCoordinate r0p45a0;
	private SphericCoordinate r1p90a0;
	private SphericCoordinate r1p90a90;
	private SphericCoordinate r1p0a0;

	private CartesianCoordinate z;
	
	@Before
	public void setup() throws CoordinateException {
		c1 = SphericCoordinate.getInstance(1, 33, 33);
		equalToC1 = SphericCoordinate.getInstance(1, 33, 33);
		
		zero = SphericCoordinate.getInstance(0, 0, 0);
		r0p45a0 = SphericCoordinate.getInstance(0, 45, 0);
		r1p90a0 = SphericCoordinate.getInstance(1, 90, 0);
		r1p90a90 = SphericCoordinate.getInstance(1, 90, 90);
		r1p0a0 = SphericCoordinate.getInstance(1, 0, 0);
		
		z = CartesianCoordinate.getInstance(0, 0, 1);
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
		assertTrue(c1 == equalToC1);
		
		for(SphericCoordinate c : new SphericCoordinate[] {
			zero,
			r0p45a0,
			r1p90a0,
			r1p90a90,
			r1p0a0
		}) {
			assertTrue(c == SphericCoordinate.getInstance(c.radius, c.polar, c.azimuth));
		}
	}
	
	@Test
	public void testEqual() {
		assertTrue(c1.isEqual(equalToC1));
		assertTrue(equalToC1.isEqual(c1));
		assertTrue(zero.isEqual(r0p45a0));
		assertTrue(r0p45a0.isEqual(zero));
		assertTrue(z.isEqual(r1p0a0));
		assertTrue(r1p0a0.isEqual(z));
	}
	
	@Test
	public void testGetCentralAngle() throws CoordinateException {
		assertEquals(90, r1p0a0.getCentralAngle(r1p90a0), DELTA);
		assertEquals(90, r1p90a0.getCentralAngle(r1p0a0), DELTA);
	}
	
	@Test
	public void testAsCartesianCoordinate() throws CoordinateException {
		assertEquals(r1p0a0, r1p0a0.asCartesianCoordinate().asSphericCoordinate());
		assertEquals(r1p90a0, r1p90a0.asCartesianCoordinate().asSphericCoordinate());
		assertEquals(r1p90a90, r1p90a90.asCartesianCoordinate().asSphericCoordinate());
	}

	@After
	public void tearDown() {
		
	}
	
}
