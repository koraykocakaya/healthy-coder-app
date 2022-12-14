package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 1. Bazi durumlarda testi anlik Disabled etmek isteyebiliriz, bunun icin meetoda @Disabled annotationu eklemek yeterli olacaktir
 * 	Yorum ekleyerek neden disabled edildigi belirtilebilmektedir
 * 2. Disabled annotationi tum class icin de kullanilabilmektedir
 * @author korayk
 */
public class Test08Disabled {

	@Test
	@Disabled("findCoderWithWorstBMI not implemented waiting for Jira-1234")
	public void should_ReturnNull_When_CoderListEmpty() {
		
		// given
		List<Coder> coderList = new ArrayList<>();
		
		// when
		Coder result = BMICalculator.findCoderWithWorstBMI(coderList);
		
		// then
		assertNull(result);
	}

	

}
