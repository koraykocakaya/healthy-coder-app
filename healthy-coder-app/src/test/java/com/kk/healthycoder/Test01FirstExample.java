package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * 1. Unit test yazacagimiz methodu @Test ile isaretlememiz gerekecektir
 * 2. Metot isimlerinin buradaki gibi bilgilendirici olmasi kullanim acisindan saglikli olacaktir
 * 3. Herhangi bir kontrol (assert) olmadigi durumda otomatik olarak test pass sayilacaktir 
 * 4. Kontrol icin expected deger ise returnValue assert metotlari yardimiyla kontrol edilmelidir
 * 5. Buradaki gibi ilgili test metodunu given, when, then diye bolmek anlasilirligi artiracaktir (BDD)
 * @author korayk
 */
public class Test01FirstExample {

	
	@Test
	void should_ReturnTrue_When_DietRecommended() {
		// given
		double height = 1.74;
		double weight = 95.0;
		
		// when
		boolean result = BMICalculator.isDietRecommended(weight, height); 
				
		// then
		assertTrue(result);
	}
	
	@Test
	void notAssertTest() {
		// given
		double height = 1.74;
		double weight = 95.0;
		
		// when
		BMICalculator.isDietRecommended(weight, height); 
				
		// then
	}
}
