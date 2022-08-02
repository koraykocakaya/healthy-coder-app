package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * 1. @ParameterizedTest annotatino yardimiyla metoda parametre gecilebilmektedir
 * 2. Bununla birlikte @CsvSource'ta oldugu gibi birden cok parametre ile kontrol edilebilmektedir, 
 * 	bu sekilde kullandigimizda her bir value icin testi tekrar calistiracaktir
 * 3. @CsvSource icin virgul ile parametreler verilebilmektedir, 
 * 4. Ayrica yorum olmasi acisindan @ParameterizedTest name verilerek testin hangi inputlarla calistirildigi goruntulenebilmektedir
 * 5. @CsvSource gibi direkt Csv filedan (@CsvFileSource) alabilecegimiz yontemler de bulunmaktadir
 * 6. Parametre olarak null veya Empty gecmek icin @NullSource ve @EmptySource annotatinolari kullanmamiz gerekmektedir (Bunlar diger sourcelarla birlikte de kullanilabilmektedir)
 * 7. Tek parametre alan metotlarda direkt @ValueSource veya @EnumSource ile de verilebilmektedir
 * 8. @CsvSource icin delimeter parametresi ile input degerlerini nasil ayiracagimiz belirtilebilmektedir
 * 9. Ayrica Source icin @MethodSource annotationu ile metotun return ettikleri kontrol edecek sekilde donulebilmektedir
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
	
	@ParameterizedTest (name = "height = {0}, weight = {1}")
	@CsvSource (value = {"1.74:90", "1.80:120"}, delimiter = ':' )
	public void isDietRecommended_Parameterized_withDelimeter(double height, double weight) {
		
		// given parameter
		
		
		// when
		boolean result = BMICalculator.isDietRecommended(weight, height); 
				
		// then
		assertTrue(result);
	}
	
	@ParameterizedTest
	@MethodSource ("listStringForCheck")
	public void isNotEmpty_ForString(String input) {
		assertFalse(input.isEmpty());
	}
	
	private static Stream<String> listStringForCheck() {
		return Stream.of(" ", "asd");
	}
}
