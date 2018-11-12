package org.wahlzeit.model;

public class LandmarkPhotoManager extends PhotoManager {
	private static LandmarkPhotoManager instance;
	
	private static LandmarkPhotoManager setup() {
		return new LandmarkPhotoManager();
	}
	
	public static LandmarkPhotoManager getInstance() {
		if(instance == null) {
			instance = setup();
		}
		
		return instance;
	}
}
