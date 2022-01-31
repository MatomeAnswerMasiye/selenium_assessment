package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Search extends ConfigClass{


	//A method that searches for a user using the username
	@BeforeClass
	public void searchForUser() throws InterruptedException {

		Thread.sleep(3000);

		//SearchBox Input
		WebElement search_box = ConfigClass.driver.findElement(By.id(Locators.search_box));
		search_box.clear();
		search_box.sendKeys(InstanceVariables.username);

		//Locates the search button
		WebElement search_button = ConfigClass.driver.findElement(By.id(Locators.search_button));
		Thread.sleep(3000);
		search_button.click();
	}

	//A method that validates if the returned username matches the expected username
	@Test(priority = 1)
	public void validateUsername() {

		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("Record username validation","Validating if the returned username under system users matches the expected username");

		//Locates the username webElement on the record table
		WebElement record_username = ConfigClass.driver.findElement(By.xpath(Locators.record_username));

		//A decision block that evaluates if the returned username matches the desired username
		if(record_username.getText().equalsIgnoreCase(InstanceVariables.username)) {
			ConfigClass.result = true;
			ConfigClass.message = "Username matches expected username";
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "Username does not match the expected username";
		}

		assertTrue(ConfigClass.result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(ConfigClass.message);

	}

	//A method that checks if the returned employee name matches the expected employee name
	@Test(priority = 2)
	public void employeeName() {

		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("Record employee name validation","Validating if the returned employee name under system is " 
				+ InstanceVariables.expected_record_employeeName);

		//Locates the employee name webElement on the record table
		WebElement record_employeeName = ConfigClass.driver.findElement(By.xpath(Locators.record_employeeName));

		//A decision block that evaluates if the returned emp name matches the desired emp name
		if(record_employeeName.getText().equalsIgnoreCase(InstanceVariables.expected_record_employeeName)) {
			ConfigClass.result = true;
			ConfigClass.message = "Employee name matches the expected employee name";
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "Employee name does not match the expected employee name";
		}

		assertTrue(ConfigClass.result);

		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(ConfigClass.message);

	}

	//A method that checks if the returned status matches the expected status
	@Test(priority = 3)
	public void validateStatus() {

		//Creates test
		ConfigClass.test = ConfigClass.extent.createTest("Record status validation","Validating if the status under system users is " 
				+ InstanceVariables.desired_selected_status);

		//Locates the status web element on the record table
		WebElement record_status = ConfigClass.driver.findElement(By.xpath(Locators.record_status));

		//A decision block that evaluates if the return status matches the desired status
		if(record_status.getText().equalsIgnoreCase(InstanceVariables.desired_selected_status)) {
			ConfigClass.result = true;
			ConfigClass.message = "status matches the expected status";
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "status does not match the expected status";
		}

		assertTrue(ConfigClass.result);

		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(ConfigClass.message);
	}

}
