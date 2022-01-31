package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Delete extends ConfigClass{

	//A method that will run before the 'validateNoRecords' test runs
	@BeforeClass
	public void delete() throws InterruptedException {

		//Check the record
		WebElement checkbox = driver.findElement(By.name(Locators.delete_checkbox));
		Thread.sleep(3000);
		checkbox.click();

		//Click the delete button
		WebElement delete_button = driver.findElement(By.id(Locators.delete_button));
		Thread.sleep(3000);
		delete_button.click();

		//Delete confirmation
		WebElement delete_confirmation_button = driver.findElement(By.id(Locators.delete_confirmation_button));
		Thread.sleep(3000);
		delete_confirmation_button.click();

	}
	
	//A method that validates if there's no record found when trying to search after deleting 
	@Test
	public void validateNoRecords() throws InterruptedException {
		
		test = extent.createTest("No records validation","Validating if we will get the 'No records found' message");
		
		Thread.sleep(3000);

		//Go to search box and search previously deleted record
		//SearchBox
		WebElement search_box = driver.findElement(By.id(Locators.search_box));
		search_box.clear();
		search_box.sendKeys(InstanceVariables.username);

		//Locates and clicks on the search button
		WebElement search_button = driver.findElement(By.id(Locators.search_button));
		Thread.sleep(3000);
		search_button.click();

		//Locates the no record found message
		WebElement no_record_message = driver.findElement(By.xpath(Locators.no_record_message));
		
		//A decision block that evaluates if the 'No records found message' is displayed or not
		if(no_record_message.isDisplayed()) {
			result = true;
			message = "No Records Found Message is displayed";
		}else {
			result = false;
			message = "No Records Found Message is not displayed";
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
		
	}

	
}
