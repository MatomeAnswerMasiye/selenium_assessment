package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Logout extends ConfigClass{
	

	//A method that logs out of the application
	@BeforeClass
	public void logout() throws InterruptedException {

		//Click on welcome admin
		WebElement welcome_admin = driver.findElement(By.id(Locators.welcome_admin));
		Thread.sleep(3000);
		welcome_admin.click();

		//Click on logout
		Thread.sleep(3000);
		WebElement logout_button = driver.findElement(By.linkText("Logout"));
		logout_button.click();
		
	}
	
	//A method that checks if the url contains a desired keyword after logout
	@Test(priority = 1)
	public void validateUrl() {
		
		//Creates a tets
		test = extent.createTest("Url validation","Validating if the url contains " + InstanceVariables.post_logout_url_keyword + " after logout");

		//A decision block that evaluates if the url contains the desired keyword
		if(driver.getCurrentUrl().contains(InstanceVariables.post_logout_url_keyword)) {
			result = true;
			message = "Url contains 'login'";
		}
		else {
			result = false;
			message = "Url does not contain 'login'";
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
		
	}
	
	//A method that checks if the actual title matches the expected title after logout
	@Test(priority = 2)
	public void validateTitle() {
		
		//Creates a test
		test = extent.createTest("title validation after logout","Validating if the actual title matches the expected title after logout");
		
		//A decision block that evaluates if the actual title matches the expected title
		if(driver.getTitle().equals(InstanceVariables.expected_title)) {
			result = true;
			message = "Actual title matches expected title";
		}
		else {
			result = false;
			message = "Actual title does not match expected title";
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
			
	}

}
