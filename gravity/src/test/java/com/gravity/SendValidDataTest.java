package com.gravity;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import com.gravity.pages.ResponseForm;
import com.gravity.pages.TestFormPage;


public class SendValidDataTest extends TestBaseSetup {
	private TestFormPage testForm;
	private ResponseForm responseForm;

	@BeforeClass
	private void instantiatePage() {
		testForm = new TestFormPage();
		responseForm = new ResponseForm();
	}

	@Test
	public void validData() {
		assertTrue(testForm.verifyAllFiledsAreEmpty());
		assertTrue(testForm.verifyAllCheckboxesAreUnmarked());

		testForm.enterEmail("test@ukr.net").enterAge("01.01.1984").enterName("Oksana").markCheckBoxExcellent()
				.markCheckBoxOther().enterMoodOtherReason("test reason").clickSubmit();

		assertFalse(testForm.isTestFormShown());
		assertTrue(responseForm.isResponseFormShown());
		assertTrue(responseForm.isConfirmationMessageShown());

		responseForm.clickSendOneMoreLink();

		assertFalse(responseForm.isResponseFormShown());
		assertTrue(testForm.isTestFormShown());
		assertTrue(testForm.verifyAllFiledsAreEmpty());
		assertTrue(testForm.verifyAllCheckboxesAreUnmarked());
	}
}
