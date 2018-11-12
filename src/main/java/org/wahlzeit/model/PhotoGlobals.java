package org.wahlzeit.model;

public final class PhotoGlobals {
	private static PhotoGlobals instance;
	
	private static PhotoGlobals setup(PhotoFactory factory, PhotoManager manager) {
		manager.init(factory);
		
		return new PhotoGlobals(factory, manager);
	}
	
	public static void init(PhotoFactory factory, PhotoManager manager) {
		instance = setup(factory, manager);
	}
	
	public static PhotoGlobals getInstance() {
		if(instance == null) {
			throw new IllegalStateException("Not initialized");
		}
		
		return instance;
	}
	
	private final PhotoFactory factory;
	private final PhotoManager manager;
	
	private PhotoGlobals(PhotoFactory factory, PhotoManager manager) {
		this.factory = factory;
		this.manager = manager;
	}

	public PhotoFactory getFactory() {
		return factory;
	}

	public PhotoManager getManager() {
		return manager;
	}
}
