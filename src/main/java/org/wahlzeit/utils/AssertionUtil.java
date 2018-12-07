package org.wahlzeit.utils;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class AssertionUtil {
	private AssertionUtil() {
		
	}
	
	public static <T, E extends RuntimeException> void assertPredicate(Predicate<T> pred, T value, Function<T,E> exceptionSupplier) throws E {
		if(!pred.test(value)) {
			throw exceptionSupplier.apply(value);
		}
	}
	
	public static <T, E extends RuntimeException> void assertNotNull(T value, Supplier<E> exceptionSupplier) throws E {
		assertPredicate((v) -> v != null, value, (v) -> exceptionSupplier.get());
	}
	
	public static <E extends RuntimeException> void assertFinite(double value, Function<Double,E> exceptionSupplier) throws E {
		assertPredicate((v) -> Double.isFinite(v), value, exceptionSupplier);
	}
}
