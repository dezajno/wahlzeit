package org.wahlzeit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LandmarkTypeTest {
	
	LandmarkType one;
	LandmarkType two;
	LandmarkType three;

	@Before
	public void setUp() throws Exception {
		this.one = LandmarkType.getInstance("one");
		this.two = LandmarkType.getInstance("two", one);
		this.three = LandmarkType.getInstance("three", two);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsSubType() {
		assertFalse(one.isSubType());
		assertTrue(two.isSubType());
		assertTrue(three.isSubType());
	}
	
	@Test
	public void testIsDirectSubTypeOf() {
		assertFalse(one.isDirectSubTypeOf(one));
		assertFalse(two.isDirectSubTypeOf(two));
		assertFalse(three.isDirectSubTypeOf(three));
		
		assertFalse(one.isDirectSubTypeOf(two));
		assertFalse(one.isDirectSubTypeOf(three));
		assertFalse(two.isDirectSubTypeOf(three));
		
		assertFalse(three.isDirectSubTypeOf(one));
		assertTrue(two.isDirectSubTypeOf(one));
		assertTrue(three.isDirectSubTypeOf(two));
	}
	
	@Test
	public void testIsSubTypeOf() {
		assertFalse(one.isSubTypeOf(one));
		assertFalse(two.isSubTypeOf(two));
		assertFalse(three.isSubTypeOf(three));
	
		assertFalse(one.isSubTypeOf(two));
		assertFalse(one.isSubTypeOf(three));
		assertFalse(two.isSubTypeOf(three));
		
		assertTrue(three.isSubTypeOf(one));
		assertTrue(two.isSubTypeOf(one));
		assertTrue(three.isSubTypeOf(two));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testHierarchyCheck() {
		LandmarkType.getInstance("two", three);
	}
	
	@Test
	public void testGetInstance() {
		LandmarkType.getInstance("two", one);
		assertTrue(one == LandmarkType.getInstance("one"));
		assertTrue(one.equals(LandmarkType.getInstance("one")));
		assertFalse(one.equals(two));
		assertFalse(one.equals(LandmarkType.getInstance("two")));
	}

}
