package org.wahlzeit.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.wahlzeit.utils.AssertionUtil;

public class LandmarkManager {
	protected static LandmarkManager INSTANCE;
	
	protected Map<String, LandmarkType> landmarkTypes;
	protected Set<Landmark> createdLandmarks;
	
	protected LandmarkManager() {
		landmarkTypes = new HashMap<>();
		createdLandmarks = new HashSet<>();
	}
	
	public static LandmarkManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new LandmarkManager();
		}
		
		return INSTANCE;
	}
	
	protected LandmarkType doGetType(String typeId, LandmarkType superType) {
		LandmarkType type = landmarkTypes.get(typeId);
		
		if(type == null) {
			if(superType == null) {
				type = LandmarkType.getInstance(typeId);
			} else {
				type = LandmarkType.getInstance(typeId, superType);
			}
			landmarkTypes.put(typeId, type);
		}
		return type;
	}
	
	protected LandmarkType doGetType(String... typeIdHierarchy) {
		LandmarkType curType = null;
		
		for(String typeId : typeIdHierarchy) {
			curType = doGetType(typeId, curType);
		}
		
		return curType;
	}
	
	public Landmark createLandmark(String... typeIdHierarchy) {
		AssertionUtil.assertNotNull(typeIdHierarchy, () -> new IllegalArgumentException("typeIdHierarchy may not be null!"));
		AssertionUtil.assertPredicate((v) -> v.length != 0, typeIdHierarchy, (v) -> new IllegalArgumentException("typeIdHierarchy may not be empty!"));
		
		LandmarkType type = doGetType(typeIdHierarchy);
		Landmark lm = Landmark.getInstance(type);
		
		if(!createdLandmarks.contains(lm)) {
			createdLandmarks.add(lm);
		}
		
		return lm;
	}
	
	public LandmarkType type(String... typeIdHierarchy) {
		AssertionUtil.assertNotNull(typeIdHierarchy, () -> new IllegalArgumentException("typeIdHierarchy may not be null!"));
		AssertionUtil.assertPredicate((v) -> v.length != 0, typeIdHierarchy, (v) -> new IllegalArgumentException("typeIdHierarchy may not be empty!"));
		
		return doGetType(typeIdHierarchy);
	}
}
