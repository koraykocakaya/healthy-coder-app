package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;

/**
 * 1. Tag yardimiyla hangi testlerin calisacagini belirtebiliriz,
 * 2. Bu sayede ornegin CI surecleri icin sadece Integration testlerinin calisacagini belirtebiliriz  
 * 3. IDE'den calistirirken de Tag ayrica belirtilebilmekte
 * 4. Direkt maven uzerinden de 'mvn test -Dgroups="load"' seklinde calistirdigimizda sadece load tagi olanlar calisacaktir
 * @author korayk
 */
public class Test09Tags {

	
	@Tag("load")
	@ParameterizedTest
	@EmptySource
	void isEmptyString(String str) {
		assertTrue(str.isEmpty());
	}
	
	@Tag("secondTag")
	@ParameterizedTest
	@CsvSource ({"asdad", "asd"})
	void isNotEmptyString(String str) {
		assertFalse(str.isEmpty());
	}
	
}
