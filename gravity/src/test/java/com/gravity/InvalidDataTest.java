package com.gravity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gravity.pages.TestFormPage;
import static org.testng.Assert.*;

/**
 * Unit test for simple App.
 */

public class InvalidDataTest extends TestBaseSetup {
	private TestFormPage testForm;

	@BeforeClass
	private void instantiatePage() {
		testForm = new TestFormPage();
	}

	@BeforeMethod
	public void beforeTestActions() {
		testForm.refreshPage();
	}

	@Test
	public void allEmptyFiledsSend() {
		testForm.clickSubmit();
		assertTrue(testForm.isTestFormShown());
		assertTrue(testForm.emailHasError());
		assertEquals("Это обязательный вопрос.", testForm.getEmailErrorMsg());
		assertTrue(testForm.ageHasError());
		assertEquals("Это обязательный вопрос.", testForm.getAgeErrorMsg());
		assertTrue(testForm.nameHasError());
		assertEquals("Это обязательный вопрос.", testForm.getNameErrorMsg());
		assertTrue(testForm.moodHasError());
		assertEquals("Это обязательный вопрос.", testForm.getMoodErrorMsg());
	}

	@Test
	public void emailValidAndOtherFiledsEmptySend() {
		testForm.clickSubmit();
		testForm.enterEmail("test@ukr.net");
		testForm.clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());

		assertTrue(testForm.ageHasError());
		assertEquals("Это обязательный вопрос.", testForm.getAgeErrorMsg());
		assertTrue(testForm.nameHasError());
		assertEquals("Это обязательный вопрос.", testForm.getNameErrorMsg());
		assertTrue(testForm.moodHasError());
		assertEquals("Это обязательный вопрос.", testForm.getMoodErrorMsg());
	}

	@Test
	public void emailAndAgeValidButOtherFiledsEmptySend() {
		testForm.clickSubmit();
		testForm.enterEmail("test@ukr.net");
		testForm.enterAge("01.01.2019");
		testForm.clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());
		assertFalse(testForm.ageHasError());
		assertEquals("", testForm.getAgeErrorMsg());

		assertTrue(testForm.nameHasError());
		assertEquals("Это обязательный вопрос.", testForm.getNameErrorMsg());
		assertTrue(testForm.moodHasError());
		assertEquals("Это обязательный вопрос.", testForm.getMoodErrorMsg());
	}

	@Test
	public void emailAgeNameValidButMoodEmptySend() {
		testForm.clickSubmit();
		testForm.enterEmail("test@ukr.net");
		testForm.enterAge("01.01.2019");
		testForm.enterName("Test Name");
		testForm.clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());
		assertFalse(testForm.ageHasError());
		assertEquals("", testForm.getAgeErrorMsg());
		assertFalse(testForm.nameHasError());
		assertEquals("", testForm.getNameErrorMsg());

		assertTrue(testForm.moodHasError());
		assertEquals("Это обязательный вопрос.", testForm.getMoodErrorMsg());
	}

	@Test
	public void emptyMoodReasonSend() {
		testForm.clickSubmit();
		testForm.enterEmail("test@ukr.net");
		testForm.enterAge("01.01.2019");
		testForm.enterName("Test Name");
		testForm.markCheckBoxOther();
		testForm.clickSubmit();

		assertTrue(testForm.isTestFormShown());
		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());
		assertFalse(testForm.ageHasError());
		assertEquals("", testForm.getAgeErrorMsg());
		assertFalse(testForm.nameHasError());
		assertEquals("", testForm.getNameErrorMsg());

		assertTrue(testForm.moodHasError());
		assertEquals("Это обязательный вопрос.", testForm.getMoodErrorMsg());
	}

	@Test
	public void checkboxOtherAndExcellentSend() {
		testForm.enterEmail("test@ukr.net");
		testForm.enterAge("01.01.2019");
		testForm.enterName("Test Name");
		testForm.markCheckBoxOther();
		testForm.clickSubmit();

		assertTrue(testForm.isTestFormShown());
		testForm.markCheckBoxExcellent();
		testForm.clickSubmit();

		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());
		assertFalse(testForm.ageHasError());
		assertEquals("", testForm.getAgeErrorMsg());
		assertFalse(testForm.nameHasError());
		assertEquals("", testForm.getNameErrorMsg());

		assertTrue(testForm.moodHasError());
		assertEquals("Это обязательный вопрос.", testForm.getMoodErrorMsg());

		testForm.markCheckBoxOther();

		assertFalse(testForm.emailHasError());
		assertEquals("", testForm.getEmailErrorMsg());
		assertFalse(testForm.ageHasError());
		assertEquals("", testForm.getAgeErrorMsg());
		assertFalse(testForm.nameHasError());
		assertEquals("", testForm.getNameErrorMsg());
		assertFalse(testForm.moodHasError());
		assertEquals("", testForm.getMoodErrorMsg());
	}

}
