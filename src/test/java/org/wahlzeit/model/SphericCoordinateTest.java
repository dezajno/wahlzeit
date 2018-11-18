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
	public void setup() {
		c1 = new SphericCoordinate(1, 33, 33);
		equalToC1 = new SphericCoordinate(1, 33, 33);
		
		zero = new SphericCoordinate(0, 0, 0);
		r0p45a0 = new SphericCoordinate(0, 45, 0);
		r1p90a0 = new SphericCoordinate(1, 90, 0);
		r1p90a90 = new SphericCoordinate(1, 90, 90);
		r1p0a0 = new SphericCoordinate(1, 0, 0);
		
		z = new CartesianCoordinate(0, 0, 1);
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
	public void testGetCentralAngle() {
		assertEquals(90, r1p0a0.getCentralAngle(r1p90a0), DELTA);
		assertEquals(90, r1p90a0.getCentralAngle(r1p0a0), DELTA);
	}
	
	@Test
	public void testAsCartesianCoordinate() {
		assertEquals(r1p0a0, r1p0a0.asCartesianCoordinate().asSphericCoordinate());
		assertEquals(r1p90a0, r1p90a0.asCartesianCoordinate().asSphericCoordinate());
		assertEquals(r1p90a90, r1p90a90.asCartesianCoordinate().asSphericCoordinate());
	}

	@After
	public void tearDown() {
		
	}
	
}
