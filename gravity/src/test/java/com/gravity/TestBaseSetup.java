package com.gravity;

import java.net.MalformedURLException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.gravity.drivers.WebDriverInstansiator;

public class TestBaseSetup {

	@BeforeClass
	public static void setUp() throws MalformedURLException {
		WebDriverInstansiator.setDriver();
	}

	@AfterClass
	public static void tearDown() {
		WebDriverInstansiator.tearDown();
	}
}
