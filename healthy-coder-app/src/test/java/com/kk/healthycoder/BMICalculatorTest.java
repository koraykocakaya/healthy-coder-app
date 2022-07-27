package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 1. Test classin adi class+Test olarak setlnedi
 * 2. Fail olmamasi success anlamina gelir, yani kontrol yoksa da basarili olacaktir
 * 3. Metot isimlerinin bilgilendirici olmasi kritik
 * 4. Test yazarken given, when ve then diye bolmek anlam acisindan saglikli olacaktir
 * 5. assertAll kullanarak ilgili assertlerin ayri ayri calismasi saglandi, boylece biri hatali olsa digerinin sonucu kontrol edilebildi
 * 6. ParameterizedTest ile ayni testin farkli degerler ile birden cok defa calismasi sagalanabilmektedir
 * 7. assertTimeout ile bir metodun calisma suresi uzerinden de test yazilabilmektedir
 * 8. @Nested ile inner class yaratip onunla ilgili metotlari tek bir yerde toplayabiliriz, boylece test calistirildiginda detaylar daha anlasilir olacaktir
 * 9. @DisabledOnOs ile hangi OS'ta testi disabled edecegimiz verilebilmektedir 
 * @author korayk
 */
class BMICalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

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
		
		@Test
		void should_ReturnFalse_When_DietNotRecommended() {
			// given
			double height = 1.80;
			double weight = 74.0;
			
			// when
			boolean result = BMICalculator.isDietRecommended(weight, height); 
					
			// then
			assertFalse(result);
		}
		
		@Test
		void should_ThrowArithmeticException_When_HeightIsZero() {
			// given
			double height = 0;
			double weight = 74.0;
			
			// when
			Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

			// then
			assertThrows(ArithmeticException.class, executable);
		}
		
		@ParameterizedTest (name = "height = {0}, weight = {1}")
		@CsvSource ({"1.74,90", "1.80,120"})
		public void isDietRecommended_Parameterized(double height, double weight) {
			
			// given
			
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
