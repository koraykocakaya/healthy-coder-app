package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * 1. Her bir test oncesi DietPlanner objesini tekrar initialize ediyoruz, testlerin birbirini etkilememesi adina
 * 2. Ozellikle Db connection gibi costly islemler varsa bu islemin bir kere yapilmasi adina BeforeAll'da yapmak saglikli olacaktir
 * 3. @RepeatedTest kullanarak ayni testing kac defa calistirilacagi belirtilebilmektedir, ayri testler gibi dusunuldugu icin her biri oncesinde BeforeEach cagrilacaktir
 * 4. assumeTrue gibi varsayimlarla bazi testlerin sadece dev ortamda calismasi gibi kontroller ekelenebilmektedir, assumption false olursa test calistirilmayacaktir
 *  
 * @author korayk
 */
class DietPlannerTest {

	private DietPlanner dietPlanner;
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("Before All unit tests");
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("After All unit tests");
	}
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.dietPlanner = new DietPlanner(40, 40, 20);
	}

	@RepeatedTest (value = 5)
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
	void should_ExecuteOnDevEnv() {
		
		
		assumeTrue("dev".equals(System.getProperty("environment")));
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
