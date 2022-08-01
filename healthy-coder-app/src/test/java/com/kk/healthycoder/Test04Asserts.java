package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * 1. Normalde sirayla assert ile kontrol edersek ilk hata alandan sonra digerlerini kontrol etmeden cikacaktir
 * 2. Bunun yerine asagidaki gibi @assertAll kullanarak birden cok asserti kontrol edip hangilerinin hata aldigini saptayabiliriz
 * 3. @assertTimeout yardimiyla ilgili metodun ne kadar surede tamamlanmasi gerektigini de kontrol edebiliriz
 * @author korayk
 */
public class Test04Asserts {
	
	private DietPlanner dietPlanner;
	
	@BeforeEach
	void setUp() throws Exception {
		this.dietPlanner = new DietPlanner(40, 40, 20);
	}

	
	@Test
	void should_ReturnCorrectDietPlan_When_CorrectCoder() {
		
		// given
		Coder c1 = new Coder(1.74, 100.0, 30, Gender.MALE);
		DietPlan expected = new DietPlan(2535, 254, 113, 127);
		
		// when
		DietPlan result = dietPlanner.calculateDiet(c1);
		
		// then
		assertAll(
				() -> assertEquals(expected.getCalories(),result.getCalories()),
				() -> assertEquals(expected.getProtein(),result.getProtein()),
				() -> assertEquals(expected.getFat(),result.getFat()),
				() -> assertEquals(expected.getCarbohydrate(),result.getCarbohydrate())
		);
		
	}
	
	@Test
	public void should_ReturnWorstBMI_When_CoderListHas100000Elements() {
		
		// given
		List<Coder> coderList = new ArrayList<Coder>();
		for(int i=1; i<=100000;i++) {
			coderList.add(new Coder(i, i));
		}
		
		// when
		Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coderList);
		
		// then
		assertTimeout(Duration.ofMillis(1000), executable);
	}
}
