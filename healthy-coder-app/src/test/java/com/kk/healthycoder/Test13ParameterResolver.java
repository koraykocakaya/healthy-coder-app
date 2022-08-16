package com.kk.healthycoder;

import com.kk.model.FakeDBConnection;
import com.kk.model.FakeDbParameterResolver;
import com.kk.model.FakeDbParameterResolver.FakeDB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * 1. Default lifecycle her bir metot oldugu icin her bir test calismadan once constructor cagrilacaktir
 * 2. ParameterResolver Extensions API'ini parametreleri runtime'da dinamik olarak belirtmek icin kullanmaktadir
 * 3. Buradaki TestInfo'yu implement eden TestInfoParameterResolver bir parameterResolver'dir ve test classi ile ilgili detay bilgileri icermektedir
 * 4. TestInfo'yu Test metotlarina veya @BeforeEach vs gecerek detay bilgileri alabiliriz, resolver bu parametreyi doldurmaktadir
 * 5. TestInfo benzer sekilde repeatedTestlerde de kullandigimiz RepetitionInfo icin de resolver bulunmaktadir, 
 *  bu sayede repetition ile ilgili detay bilgiye erisilebilmektedir
 * 6. TestReporter da testle ilgili additional bilgileri publsh etmek icin kullanilabilmektedir (log gibi kullanilabilmekte)
 * 7. Kendi CustomResolver'imizi de tanimlayabiliriz, 
 *  Buradaki FakeDbParameterResolver tanimlandigi gibi ParameterResolver imeplement eden bir class yardimiyla ne sekilde kullanilacagi da belirtilebilmektedir
 * 8. Bu ornek ozelinde tipinin FakeDBConnection olacagi ve @FakeDB annotation kullanilacagini belirtmis olduk
 * 
 * @author korayk
 */
@ExtendWith(FakeDbParameterResolver.class)
public class Test13ParameterResolver {

	public Test13ParameterResolver(TestInfo info) {
//		System.out.println(info.getDisplayName()+ " called");
	}
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Beforeall called");
	}
	
	@BeforeEach
    void setUp(TestInfo testInfo, RepetitionInfo repetitionInfo, TestReporter testReporter) {
        int currentRepetition = repetitionInfo.getCurrentRepetition();
        int totalRepetitions = repetitionInfo.getTotalRepetitions();
        String methodName = testInfo.getTestMethod().get().getName();
        
        String msg = "About to execute repetition " + currentRepetition + " of " + totalRepetitions +  " for " + methodName;
        System.out.println(msg);
        testReporter.publishEntry("Publish: " + msg);
    }
	  
	@RepeatedTest(3)
	void test_Add(RepetitionInfo repetitionInfo) {
		System.out.println("start test_Add() : "+repetitionInfo.getCurrentRepetition());
		Assertions.assertEquals(5, 3+2);
	}
	
	@RepeatedTest(1)
	void injectFakeDbTest(@FakeDB FakeDBConnection connection) {
	  connection.getAllUserNames().forEach(System.out::println);
	  Assertions.assertTrue(connection.getAllUserNames().contains("koray"));
	}
	
}
