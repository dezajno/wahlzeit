package org.wahlzeit.model;

@PatternInstance(
	patternName = "Singleton",
	participants = {
		"Singleton"
	}
)
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
