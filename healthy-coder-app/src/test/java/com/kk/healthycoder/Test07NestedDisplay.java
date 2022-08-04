package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * 1. Farklı metotlari ya da farkli testleri gruplama ihhtiyacimiz olabilir
 * 2. Bunun icin bir Inner class yaratip bunun @Nested ile isaretlersek inner class adlarina gore testler gruplanacaktir 
 * 3. Bununla birlikte classlara veya metotlara @DisplayName annotationu ile testi calistirdigimizda ne sekilde gorunecegini belirtebilmekteyiz
 * 4. Ek olarak tum sinif icin DisplayNameGenerator uzerinden de verilebilmektedir standart bir displayName verilebilmektedir
 * @author korayk
 */
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Test07NestedDisplay {

	
	@Nested
	class IsRecommendedTest{
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

	}
	
	@Nested
	@DisplayName ("findCoderWorstBMI method testing cases")
	class findCoderWorstBMITest{
		
		
		@Test
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
	
	@Nested
	class getBMIScoresTest{
		
		@Test
		public void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
			
			// given
			Coder c1 = new Coder(1.74, 90.0);
			Coder c2 = new Coder(1.70, 100.0);
			Coder c3 = new Coder(1.85, 80.0);
			List<Coder> coderList = Arrays.asList(c1,c2,c3);
			double[] bmiScores = new double[]{29.73, 34.60, 23.37};
			
			// when
			double[] result = BMICalculator.getBMIScores(coderList);
			
			// then
			assertArrayEquals(bmiScores, result);
		}
	}
}
