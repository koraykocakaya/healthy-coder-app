package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 1. Bazi testlerin sadece bazı durumlarda calisitirilmasi gerekmektedir, 
 * 	Ornegin asagidaki ornekteki gibi bazı testler sadece Dev ortaminda calissin denilebilmektedir
 * 2. Bunun icin once assumeTrue ile bir kosul verilip bu kosul dogruysa testin calistirilmasi saglanabilir
 * 	Eger bu kosul false olursa test calistirilmayacaktir 
 * @author korayk
 */
public class Test0602Assuming {

	private DietPlanner dietPlanner;
	
	@BeforeEach
	void setUp() throws Exception {
		this.dietPlanner = new DietPlanner(40, 40, 20);
	}
	
	
	@Test
	void should_ExecuteOnDevEnv() {
		
		assumeTrue("dev".equals(System.getProperty("environment")), "Aborted test: not dev env");
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
