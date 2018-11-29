package org.wahlzeit.utils;

public class NumberUtil {
	public static final double DOUBLE_COMPARE_TOLERANCE = 0.000001;
	
	public static boolean isEqual(double v1, double v2) {
		return Math.abs(v2 - v1) < DOUBLE_COMPARE_TOLERANCE;
	}
}
