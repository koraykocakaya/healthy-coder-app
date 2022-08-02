package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;

/**
 * 1. Bazi durumlarda testi anlik Disabled etmek isteyebiliriz, bunun icin meetoda @Disabled annotationu eklemek yeterli olacaktir
 * 2. Ek olarak sadece belli bir Operating systemda Disabled olamsi icin de @DisabledOnOs kullanilabilmektedir 
 * @author korayk
 */
public class Test08Disabled {

	@Test
	public void should_ReturnNull_When_CoderListEmpty() {
		
		// given
		List<Coder> coderList = new ArrayList<>();
		
		// when
		Coder result = BMICalculator.findCoderWithWorstBMI(coderList);
		
		// then
		assertNull(result);
	}
	
	@Test
	@DisabledOnOs(OS.LINUX)
	public void should_ReturnWorstBMI_When_CoderListNotEmpty() {
		
		// given
		Coder c1 = new Coder(1.74, 90.0);
		Coder c2 = new Coder(1.70, 100.0);
		Coder c3 = new Coder(1.85, 80.0);
		List<Coder> coderList = Arrays.asList(c1,c2,c3);
		
		// when
		Coder result = BMICalculator.findCoderWithWorstBMI(coderList);
		
		// then
		assertAll(
				() -> assertEquals(c2.getHeight(), result.getHeight()),
				() -> assertEquals(c2.getWeight(), result.getWeight())
		);
	}
	

}
