package com.kk.healthycoder;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * 1. @BeforeAll ve @AfterAll annotationlari tum testlerden once ve sonra calismaktadir, bu annotation ile isaretlenen metotlarin static olmasi gerekmektedir
 * 	static yapmamak icin classi @TestInstance(TestInstance.Lifecycle.PER_CLASS) ile isaretlememiz gerekecektir
 * 2. DB connection acma ve kapama gibi 1 defa yapacagimiz islemler icin uygulamak saglikli olacaktir
 * 3. @BeforeEach ve @AfterEach annotationlari ise her bir unit test metodundan once ve sonra calisacaktir
 * 4. Mesela asagidaki ornekte her bir testten once DietPlanner objesi diger testleri etkilememek adina re-initialize edildi
 * @author korayk
 */
public class Test02Annotations {

	
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
		System.out.println("Before each unit tests");
		this.dietPlanner = new DietPlanner(40, 40, 20);
	}
	
	@AfterEach
	void close() throws Exception{
		System.out.println("After each unit tests");
		System.out.println(dietPlanner.toString());
	}
}
