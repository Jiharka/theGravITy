package com.gravity;

import org.junit.Assert;
import org.junit.Test;

import com.gravity.pages.ResponseForm;
import com.gravity.pages.TestFormPage;

/**
 * Unit test for simple App.
 */
public class SendValidDataTest extends TestBaseSetup {
	TestFormPage testForm = new TestFormPage();
	ResponseForm responseForm = new ResponseForm();

	@Test
	public void validData() {
		Assert.assertTrue(testForm.verifyAllFiledsAreEmpty());
		Assert.assertTrue(testForm.verifyAllCheckboxesAreUnmarked());

		testForm.enterEmail("test@ukr.net").enterAge("01.01.1984").enterName("Oksana").markCheckBoxExcellent()
				.markCheckBoxOther().enterMoodOtherReason("test reason").clickSubmit();

		Assert.assertFalse(testForm.isTestFormShown());
		Assert.assertTrue(responseForm.isResponseFormShown());
		Assert.assertTrue(responseForm.isConfirmationMessageShown());

		responseForm.clickSendOneMoreLink();

		Assert.assertFalse(responseForm.isResponseFormShown());
		Assert.assertTrue(testForm.isTestFormShown());
		Assert.assertTrue(testForm.verifyAllFiledsAreEmpty());
		Assert.assertTrue(testForm.verifyAllCheckboxesAreUnmarked());
	}
}
