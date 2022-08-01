package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 1. @ParameterizedTest annotatino yardimiyla metoda parametre gecilebilmektedir
 * 2. Bununla birlikte @CsvSource'ta oldugu gibi birden cok parametre ile kontrol edilebilmektedir, 
 * 	bu sekilde kullandigimizda her bir value icin testi tekrar calistiracaktir
 * 3. @CsvSource icin virgul ile parametreler verilebilmektedir, 
 * 4. Ayrica yorum olmasi acisindan @ParameterizedTest name verilerek testin hangi inputlarla calistirildigi goruntulenebilmektedir
 * 5. @CsvSource gibi direkt Csv filedan (@CsvFileSource) alabilecegimiz yontemler de bulunmaktadir
 * @author korayk
 */
public class Test05Parameterized {

	
	@ParameterizedTest (name = "height = {0}, weight = {1}")
	@CsvSource ({"1.74,90", "1.80,120"})
	public void isDietRecommended_Parameterized(double height, double weight) {
		
		// given parameter
		
		
		// when
		boolean result = BMICalculator.isDietRecommended(weight, height); 
				
		// then
		assertTrue(result);
	}
}
