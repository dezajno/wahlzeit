package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.type.Date;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;

@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteProduct"
	}
)
@Subclass
public class LandmarkPhoto extends Photo {
	protected String country;
	protected String city;
	protected int metersTall;
	protected Date dateBuilt;
	@Ignore
	protected Landmark landmark;
	
	public LandmarkPhoto() {
		super();
	}
	
	public LandmarkPhoto(PhotoId id) {
		super(id);
	}
}
