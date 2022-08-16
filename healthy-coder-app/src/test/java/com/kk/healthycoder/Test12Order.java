package com.kk.healthycoder;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 1. Noramlde unit testlerin ayri calisip bir siraya bagli olmamasi gerekmektedir, 
 *  ancak birinin yarattigi datayi digeri kullaniyorsa gibi durumlar icin kullanilabilmektedir
 * 2. Direkt classa Order Anootatin verip annotationlar metod bazli verebiliriz
 * 3. Buna ek olarak otomatik metot adina gore verebilir veya kendi custom order metodumuzu da yazabiliriz
 * @author korayk
 */
@TestMethodOrder(OrderAnnotation.class)
public class Test12Order {
	
	@Test
	@Order(1)
	void firstCall() {
		System.out.println("First call");
	}
	
	@Test
	@Order (2)
	void secondCall() {
		System.out.println("Second call");
	}
	
	@Test
	@Order(3)
	void thirdCall() {
		System.out.println("Third call");
	}
	

}
