package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

/**
 * 1. @RepeatedTest annotationu ile bir testing kac defa calistirilacagi belirlenebilmektedir
 *  @BeforeEach'te RepetitionInfo parametresi gecersek her bir iteration icin bu parametreyi setleyecektir, 
 *  repeated olmayan test de varsa hata verecektir
 * @author korayk
 */
public class Test0601Repeated {
	
	private DietPlanner dietPlanner;
	
	@BeforeEach
	void setUp(RepetitionInfo repetitionInfo) throws Exception {
		this.dietPlanner = new DietPlanner(40, 40, 20);
		System.out.println(repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
		
	}

	@RepeatedTest (value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
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

}
