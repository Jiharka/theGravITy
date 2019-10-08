package com.gravity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.gravity.pages.TestFormPage;


public class NameFiledValidation extends TestBaseSetup {

	@DataProvider(name = "validName")
	public static String[] validNamess() {
		return new String[] { "testname", "1234567890", "!@#$%^&*()", "TESTNAME", "TeSTnamE", "test name first", "a",
				"thisIs_MaxAllowed", "Мое Имя" };
	}

	private TestFormPage testForm;

	@BeforeClass
	private void instantiatePage() {
		testForm = new TestFormPage();
	}

	public void invalidEmail() {
		testForm.enterName("thisIs_MaxAllowed+").clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertTrue(testForm.nameHasError());
		assertEquals("Текст превышает 20 символов.", testForm.getNameErrorMsg());
	}

	@Test(dataProvider = "validName")
	public void validEmail(String validName) {
		testForm.enterName(validName).clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertFalse(testForm.nameHasError());
		assertEquals("", testForm.getNameErrorMsg());
	}

	@Test
	public void emptyName() {
		testForm.enterName(" ").clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertTrue(testForm.nameHasError());
		assertEquals("Это обязательный вопрос.", testForm.getNameErrorMsg());
	}

}
