package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.wahlzeit.testEnvironmentProvider.SysConfigProvider;
import org.wahlzeit.testEnvironmentProvider.UserSessionProvider;

public class PhotoGlobalsTest {
	@ClassRule
	public static SysConfigProvider sysConfigProvider = new SysConfigProvider();
	
	@Rule
	public TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider()).
			around(new UserSessionProvider());
	
	@Before
	public void setup() {
		PhotoGlobals.init(new LandmarkPhotoFactory(), new LandmarkPhotoManager());
	}
	
	@Test
	public void testInstanceofFactory() {
		assertTrue(PhotoGlobals.getInstance().getFactory() instanceof LandmarkPhotoFactory);
	}
	
	@Test
	public void testInstanceofManager() {
		assertTrue(PhotoGlobals.getInstance().getManager() instanceof LandmarkPhotoManager);
	}
	
	@Test
	public void testInstanceofCreatePhoto() {
		assertTrue(PhotoGlobals.getInstance().getFactory().createPhoto() instanceof LandmarkPhoto);
	}

}
