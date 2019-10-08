package com.gravity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.gravity.pages.TestFormPage;

public class EmailFiledValidation extends TestBaseSetup {
	@DataProvider(name = "invalidEmail")
	public static String[] invalidEmails() {
		return new String[] { "test test@ukr.net", "test@ukr ain.net", "testukr.net", "@ukr.net", "test@ukr.",
				"test@ukr", "test@.ukr.net", "test()@ukr.net", "тест@укр.нет", "<script>alert('xxx');</script>" };
	}

	@DataProvider(name = "validEmail")
	public static String[] validEmails() {
		return new String[] { "test@ukr.net", "TEST@UKR.NET", "123test@ukr123.net", "1234567890@ukr.net",
				"test123@ukr123.net", "test-test@ukr-aine.net", "test_test@ukr.net", "test.test@ukr.test.net",
				"!#$%&*+/=?^_`{|}~@ukr.net", "   test@ukr.net   " };
	}

	private TestFormPage testForm;

	@BeforeClass
	private void instantiatePage() {
		testForm = new TestFormPage();
	}

	@Test(dataProvider = "invalidEmail")

	public void invalidEmail(String invalidEmail) {
		testForm.enterEmail(invalidEmail).clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertTrue(testForm.emailHasError());
		assertEquals("Укажите действительный адрес эл. почты", testForm.getEmailErrorMsg());
	}

	@Test(dataProvider = "validEmail")
	public void validEmail(String validEmail) {
		testForm.enterEmail(validEmail).clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());
	}

	@Test
	public void emptyEmail() {
		testForm.enterEmail(" ").clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertTrue(testForm.emailHasError());
		assertEquals("Это обязательный вопрос.", testForm.getEmailErrorMsg());
	}

}
