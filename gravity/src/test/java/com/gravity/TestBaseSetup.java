package com.gravity;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.gravity.drivers.WebDriverInstansiator;

public class TestBaseSetup {

	@BeforeClass
	public static void setUp() throws MalformedURLException {
		WebDriverInstansiator.setDriver();
	}

	@AfterClass
	public static void tearDown() {
		WebDriverInstansiator.getDriver().quit();
	}
}
