package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.handlers.TellFriendTest;
import org.wahlzeit.model.AccessRightsTest;
import org.wahlzeit.model.CartesianCoordinateTest;
import org.wahlzeit.model.FlagReasonTest;
import org.wahlzeit.model.GenderTest;
import org.wahlzeit.model.GuestTest;
import org.wahlzeit.model.LocationTest;
import org.wahlzeit.model.PhotoFilterTest;
import org.wahlzeit.model.PhotoGlobalsTest;
import org.wahlzeit.model.SphericCoordinateTest;
import org.wahlzeit.model.TagsTest;
import org.wahlzeit.model.UserStatusTest;
import org.wahlzeit.model.ValueTest;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.services.EmailAddressTest;
import org.wahlzeit.services.mailing.EmailServiceTestSuite;
import org.wahlzeit.utils.StringUtilTest;
import org.wahlzeit.utils.VersionTest;

@RunWith(Suite.class)
@SuiteClasses({
	// TODO
	TellFriendTest.class,
	
	AccessRightsTest.class,
	FlagReasonTest.class,
	GenderTest.class,
	GuestTest.class,
	PhotoFilterTest.class,
	TagsTest.class,
	UserStatusTest.class,
	ValueTest.class,
	
	DatastoreAdapterTest.class,
	
	EmailAddressTest.class,
	EmailServiceTestSuite.class,
	
	StringUtilTest.class,
	VersionTest.class,
	
	PhotoGlobalsTest.class,
	
	LocationTest.class,
	CartesianCoordinateTest.class,
	SphericCoordinateTest.class
})
public class AllTests {

}
