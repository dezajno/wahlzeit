package org.wahlzeit.model;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class LocationTest {
	private Optional<Coordinate> testCoord;
	private Location emptyCons;
	private Location someCoord;
	private Location someCoordCopied;
	private Location otherCoordGiven;
	
	@Before
	public void setup() {
		emptyCons = new Location();
		
		testCoord = Optional.of(new Coordinate(4.2, 0.1, 3.4));
		
		someCoord = new Location(testCoord);
		someCoordCopied = new Location(someCoord);
		otherCoordGiven = new Location(Optional.of(Coordinate.ORIGIN));
	}
	
	@Test
	public void testEmptyCons() {
		assertEquals(true, !emptyCons.coord.isPresent());
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
		new Location((Optional<Coordinate>)null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetNull() {
		otherCoordGiven.setCoord(null);
	}
	
	@Test
	public void testCoordSideEffects() {
		Location l1 = new Location(testCoord);
		Location l2 = new Location(testCoord);
		Location l3 = new Location(l1);
		l1.setCoord(Optional.of(new Coordinate(0.0, 0.0, testCoord.get().z + 1.0)));
		
		assertNotEquals(l1.coord.get(), l2.coord.get());
		assertNotEquals(l1.coord.get(), l3.coord.get());
	}
	
	@Test
	public void testGetCoord() {
		assertEquals(someCoord.getCoord().get(), someCoordCopied.getCoord().get());
		assertNotEquals(someCoord.getCoord().get(), otherCoordGiven.getCoord().get());
	}

	@Test
	public void testSetCoord() {
		Location l = new Location();
		l.setCoord(testCoord);
		
		assertEquals(testCoord.get(), l.coord.get());
	}

}
