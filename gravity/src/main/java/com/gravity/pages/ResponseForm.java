package com.gravity.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import com.google.common.base.Predicate;

public class ResponseForm extends AbstractPage {
	@FindBy(css = "div.freebirdFormviewerViewResponseLinksContainer a")
	private WebElement sendOneMoreLink;
	@FindBy(css = "div.freebirdFormviewerViewResponseConfirmationMessage")
	private WebElement responseConfirmationMessage;

	public void clickSendOneMoreLink() {
		sendOneMoreLink.click();
		new WebDriverWait(driver, 4).until(driver -> !isResponseFormShown());
	}

	public boolean isConfirmationMessageShown() {
		return responseConfirmationMessage.isDisplayed();
	}

	public boolean isResponseFormShown() {
		return !driver.findElements(By.cssSelector("div.freebirdFormviewerViewResponseConfirmContentContainer"))
				.isEmpty();
	}

}
