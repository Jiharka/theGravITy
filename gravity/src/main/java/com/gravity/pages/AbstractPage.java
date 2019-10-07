package com.gravity.pages;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.gravity.drivers.WebDriverInstansiator;

public class AbstractPage {
	protected WebDriver driver;

	protected AbstractPage() {
		this.driver = WebDriverInstansiator.getDriver();
		PageFactory.initElements(driver, this);
	}

	public void refreshPage() {
		driver.navigate().refresh();
		try {
			driver.switchTo().alert().accept();
		} catch (NoAlertPresentException e) {
		}
	}
}
