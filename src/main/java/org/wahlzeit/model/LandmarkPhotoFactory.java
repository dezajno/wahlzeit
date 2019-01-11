package org.wahlzeit.model;

@PatternInstance(
	patternName = "Abstract Factory",
	participants = {
		"ConcreteFactory"
	}
)
public class LandmarkPhotoFactory extends PhotoFactory {
	/**
	 * @methodtype factory
	 */
	@Override
	public LandmarkPhoto createPhoto() {
		return new LandmarkPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 */
	public LandmarkPhoto createPhoto(PhotoId id) {
		return new LandmarkPhoto(id);
	}

	/**
	 * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
	 * Google Cloud storage.
	 */
	public LandmarkPhoto loadPhoto(PhotoId id) {
	   /* Photo result =
                OfyService.ofy().load().type(Photo.class).ancestor(KeyFactory.createKey("Application", "Wahlzeit")).filter(Photo.ID, id).first().now();
        for (PhotoSize size : PhotoSize.values()) {
            GcsFilename gcsFilename = new GcsFilename("picturebucket", filename);



        }*/
		return null;
	}
}
