package com.kk.healthycoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.kk.model.RunOnlyDevWindows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

/**
 * 1. Junitte conditional testler de kullanilabilmektedir, asagidaki ornekteki gibi su Operating systemlarda Disabled olsun gibi 
 * 2. Benzer sekilde Jre uzerinden de kosul verilebilmektdir, boylece Java-8 ortam icin sealed gibi yeni versiyonlarda olan yapilari calistirmayacagimizi anlayabiliriz
 * 3. System property (veya environment variable) uzerindne de match edecek sekilde kosul yazilabilmektedir
 * 4. Script based conditionlar 5.5 itibariyle Deprecated olmus durumda
 * 5. Buradaki @RunOnlyDevWindows gibi kendi annotationumuzu olusturarak buna uygun testlerin calistiracak sekilde kullanabiliriz
 * @author korayk
 */
public class Test10Conditional {

	@Test
	@DisabledOnOs({ OS.WINDOWS, OS.LINUX, OS.SOLARIS })
	public void should_ReturnWorstBMI_When_CoderListNotEmpty() {

		// given
		Coder c1 = new Coder(1.74, 90.0);
		Coder c2 = new Coder(1.70, 100.0);
		Coder c3 = new Coder(1.85, 80.0);
		List<Coder> coderList = Arrays.asList(c1, c2, c3);

		// when
		Coder result = BMICalculator.findCoderWithWorstBMI(coderList);

		// then
		assertAll(() -> assertEquals(c2.getHeight(), result.getHeight()),
				() -> assertEquals(c2.getWeight(), result.getWeight()));
	}

	@Test
	@EnabledForJreRange(min = JRE.JAVA_17, max = JRE.JAVA_18)
	void runOnlyNewJres() {
		System.out.println("Jre is newer than Java 17");
	}
	
	@Test
	@DisabledIfEnvironmentVariable( named = "environment", matches = "*prod*")
	void runOnlyDevEnv() {
		System.out.println("Dont Run on prod env");
	}
	
	@RunOnlyDevWindows
	void runOnlyWindowsDev() {
		System.out.println("Custom annotatino work with windows and dev");
	}
	
	
}
