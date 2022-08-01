package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * 1. Exceptioni test etmek icin ilgili cagriyi Executable olarak setleyip sonrasinda assertThrows ile kontrol edebiliriz.
 * 2. Direkt assertThrows icinde de cagrilabilir ancak when, then olmasi acisindan bu sekilde tasarlandi
 * @author korayk
 */
public class Test03ExceptionTest {

	
	@Test
	void should_ThrowArithmeticException_When_HeightIsZero() {
		// given
		double height = 0;
		double weight = 74.0;
		
		// when
		Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

		// then
		assertThrows(ArithmeticException.class, executable);
	}
}
