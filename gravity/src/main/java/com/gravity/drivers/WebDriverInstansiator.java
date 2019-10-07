package com.gravity.drivers;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

public class WebDriverInstansiator {
	private static InheritableThreadLocal<WebDriver> webDriver = new InheritableThreadLocal<WebDriver>();

	public static void setDriver() throws MalformedURLException {
		webDriver.set(DriverFactory.setUpDriver());
	}

	public static WebDriver getDriver() {
		return webDriver.get();
	}
}
