package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClickOnAdd extends ConfigClass{
	
	
	//A method that clicks on the add button
	@BeforeClass
	public void clickAddButton() {
		
		//Get the add button and click on it
		WebElement add_button = driver.findElement(By.id(Locators.add_button));
		add_button.click();
		
	}
	
	//A method that validates if the url contains specified keyword
	@Test(priority = 1)
	public void validateUrlPath() {
		
		//Creates a test
		test = extent.createTest("saveSystemUser url validation","Validating if url contains saveSystemUser");

		//A decision block that evaluates if the url contains the desired keyword
		if(driver.getCurrentUrl().contains(InstanceVariables.add_url_path)) {
			result = true;
			message = "Url contains saveSystemUser";
		}
		else {
			result = false;
			message = "Url does not contain saveSystemUser";
		}
		
		Assert.assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
	
	}
	
	//A method that checks if a h1 text is displayed
	@Test(priority = 2)
	public void checkDisplay() {
		
		//Creates a test
		test = extent.createTest("H1 display validation", "Validating if add user h1 is displayed");
		
		//Locates the h1 text
		WebElement add_user_h1 = driver.findElement(By.id(Locators.add_user_h1));

		//A decision block that evaluates if the h1 text is displayed
		if(add_user_h1.isDisplayed()) {
			
			result = true;
			message = "the add user h1 is displayed";
			
		}
		
		else {
			result = false;
			message = "the add user h1 is not displayed";
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
			
	}
	
}
