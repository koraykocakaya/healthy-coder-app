package com.kk.healthycoder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 1. assertTimeout ile ilgili callun istenilen surede calistigi kontrol edilir, ancak o sureyi gecse bile metot calisacaktir
 * 2. assertTimeoutPreemptively ise ilgili surede bitmediginde cagriyi otomatik olarak abort edecektir
 * @author korayk
 */
public class Test11Timeout {

	
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
		assertTimeoutPreemptively(Duration.ofMillis(1000), executable);
	}
}
