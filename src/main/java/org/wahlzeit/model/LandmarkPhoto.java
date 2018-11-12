package org.wahlzeit.model;

import com.google.appengine.repackaged.com.google.type.Date;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class LandmarkPhoto extends Photo {
	protected String country;
	protected String city;
	protected int metersTall;
	protected Date dateBuilt;
	
	public LandmarkPhoto() {
		super();
	}
	
	public LandmarkPhoto(PhotoId id) {
		super(id);
	}
}
