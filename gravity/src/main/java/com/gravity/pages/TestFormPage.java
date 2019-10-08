package com.gravity.pages;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TestFormPage extends AbstractPage {
	@FindBy(name = "emailAddress")
	private WebElement emailAddress;
	@FindBy(css = "input[type='date']")
	private WebElement age;
	@FindBy(name = "entry.260210294")
	private WebElement name;

	// mood checkBoxes
	@FindBy(css = "div[data-value='Excellent'] [role='checkbox']")
	private WebElement excellent_checkBox;
	@FindBy(css = "div[data-value='Good enough'] [role='checkbox']")
	private WebElement goodEnough_checkBox;
	@FindBy(css = "div[data-value='Could be better'] [role='checkbox']")
	private WebElement couldBeBetter_checkBox;
	@FindBy(css = "div[data-value='Very bad'] [role='checkbox']")
	private WebElement veryBad_checkBox;
	@FindBy(css = "div[data-value='__other_option__'] [role='checkbox']")
	private WebElement other_checkBox;
	@FindBy(css = "input.quantumWizTextinputSimpleinputInput")
	private WebElement otherReasonText;

	@FindBy(css = "div.freebirdFormviewerViewNavigationSubmitButton")
	private WebElement submitBtn;

	// error messages
	@FindBy(id = "i2")
	private WebElement emailAlert;
	@FindBy(id = "i.err.86059121")
	private WebElement ageAlert;
	@FindBy(id = "i.err.404367803")
	private WebElement nameAlert;
	@FindBy(id = "i.err.1001784558")
	private WebElement moodAlert;

	public TestFormPage enterEmail(String email) {
		emailAddress.clear();
		emailAddress.sendKeys(email);
		return this;
	}

	public TestFormPage enterName(String text) {
		name.clear();
		name.sendKeys(text);
		return this;
	}

	public TestFormPage enterAge(String name) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('autocomplete', 'on')", age);
		age.sendKeys(name);
		js.executeScript("arguments[0].setAttribute('autocomplete', 'off')", age);
		return this;
	}

	public TestFormPage markCheckBoxExcellent() {
		excellent_checkBox.click();
		return this;
	}

	public TestFormPage markCheckBoxOther() {
		other_checkBox.click();
		return this;
	}

	public TestFormPage enterMoodOtherReason(String text) {
		otherReasonText.sendKeys(text);
		return this;
	}

	public TestFormPage clickSubmit() {
		submitBtn.click();
		return this;
	}

	public boolean isTestFormShown() {
		return !driver.findElements(By.cssSelector("div.freebirdFormviewerViewFormContent")).isEmpty();
	}

	public boolean verifyAllFiledsAreEmpty() {
		Set<String> set = new HashSet<String>();
		set.add(emailAddress.getAttribute("data-initial-value"));
		set.add(age.getAttribute("data-initial-value"));
		set.add(name.getAttribute("data-initial-value"));
		set.add(otherReasonText.getAttribute("data-initial-value"));
		return (set.stream().filter(e -> !e.isEmpty()).collect(Collectors.toSet()).size()) == 0;
	}

	public boolean verifyAllCheckboxesAreUnmarked() {
		Set<String> set = new HashSet<String>();
		set.add(excellent_checkBox.getAttribute("aria-checked"));
		set.add(goodEnough_checkBox.getAttribute("aria-checked"));
		set.add(couldBeBetter_checkBox.getAttribute("aria-checked"));
		set.add(veryBad_checkBox.getAttribute("aria-checked"));
		set.add(other_checkBox.getAttribute("aria-checked"));
		return set.stream().noneMatch(e -> e.equals("true"));
	}

	public boolean emailHasError() {
		return Boolean.parseBoolean(emailAddress.getAttribute("aria-invalid"));
	}

	public String getEmailErrorMsg() {
		return emailAlert.getText();
	}

	public boolean ageHasError() {
		return driver.findElement(By.cssSelector("div[data-item-id='86059121']")).getAttribute("class")
				.contains("HasError");
	}

	public String getAgeErrorMsg() {
		return ageAlert.getText();
	}

	public boolean nameHasError() {
		return Boolean.parseBoolean(name.getAttribute("aria-invalid"));
	}

	public String getNameErrorMsg() {
		return nameAlert.getText();
	}

	public boolean moodHasError() {
		return driver.findElement(By.cssSelector("div[data-item-id='1001784558']")).getAttribute("class")
				.contains("HasError");
	}

	public String getMoodErrorMsg() {
		return moodAlert.getText();
	}

}
