package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import com.kk.model.User;
import com.kk.model.UserAggregator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 1. @ParameterizedTest annotation yardimiyla metoda parametre gecilebilmektedir
 * 	En basit ornegiyle @ValueSource annotationi yardimiyla farkli degler gecilebilmektedir
 * 2. Bununla birlikte @CsvSource'ta oldugu gibi birden cok parametre ile kontrol edilebilmektedir, 
 * 	bu sekilde kullandigimizda her bir value icin testi tekrar calistiracaktir
 * 3. @CsvSource icin virgul ile parametreler verilebilmektedir, 
 * 4. Ayrica yorum olmasi acisindan @ParameterizedTest name verilerek testin hangi inputlarla calistirildigi goruntulenebilmektedir
 * 5. @CsvSource gibi direkt Csv filedan (@CsvFileSource) alabilecegimiz yontemler de bulunmaktadir
 * 6. Parametre olarak null veya Empty gecmek icin @NullSource ve @EmptySource annotationlari kullanmamiz gerekmektedir 
 *  (Bunlar diger sourcelarla birlikte de kullanilabilmektedir)
 * 7. Tek parametre alan metotlarda direkt @ValueSource ile birlikte @EnumSource ile de verilebilmektedir
 * 8. @CsvSource icin delimeter parametresi ile input degerlerini nasil ayiracagimiz belirtilebilmektedir
 * 9. Ayrica Source icin @MethodSource annotationu ile metotun return ettikleri kontrol edecek sekilde donulebilmektedir
 * 10. Aslinda her bir parametre testte ArgumentAccessor parametresi uzerinden islem yapilmaktadir, 
 *  conversion ile vs. ugrasmamak adina direkt accessor uzerinden veriler uzerinde islem yapilabilmektedir 
 * 11. Ozellikle complex objeler icin Acccessor ile ugrasmamak adina Custom Aggregator tanimlayarak direkt parametre olarak model gecebiliriz
 * @author korayk
 */
public class Test05Parameterized {

	@ParameterizedTest (name="#{index}- Test with Argument={arguments}")
	@ValueSource (ints = {1,2,4,8,6})
	void testSimple(int value) {
		System.out.println(value);
	}
	
	
	@ParameterizedTest (name = "height = {0}, weight = {1}")
	@CsvSource ({"1.74,90", "1.80,120"})
	public void isDietRecommended_Parameterized(double height, double weight) {
		
		// given parameter
		
		
		// when
		boolean result = BMICalculator.isDietRecommended(weight, height); 
				
		// then
		assertTrue(result);
	}
	
	@ParameterizedTest (name = "height = {0}, weight = {1}")
	@CsvSource (value = {"1.74:90", "1.80:120"}, delimiter = ':' )
	public void isDietRecommended_Parameterized_withDelimeter(double height, double weight) {
		
		// given parameter
		
		
		// when
		boolean result = BMICalculator.isDietRecommended(weight, height); 
				
		// then
		assertTrue(result);
	}
	
	@ParameterizedTest
	@MethodSource ("listStringForCheck")
	public void isNotEmpty_ForString(String input) {
		assertFalse(input.isEmpty());
	}
	
	private static Stream<String> listStringForCheck() {
		return Stream.of(" ", "asd");
	}
	
	@ParameterizedTest
	@CsvSource({"30,koray", "20, mehmet"})
	void testArgumentAccessor(ArgumentsAccessor accessor){
		
		Integer age = accessor.getInteger(0);
		String name = accessor.getString(1);
		System.out.println("Age is: " + age + ", name is: " + name);
	}
	
	@ParameterizedTest
	@CsvSource({"Koray,Kocakaya,30", "Mehmet,Koca,25"})
	void testCustomAggregatorAccessor(@AggregateWith(UserAggregator.class) User user){
		
		System.out.println("Age is: " + user.getAge() + ", name is: " + user.getName() + ", surname is: " + user.getSurname());
	}
}
