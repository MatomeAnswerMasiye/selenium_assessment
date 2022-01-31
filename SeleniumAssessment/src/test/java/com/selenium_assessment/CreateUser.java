package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUser extends ConfigClass{

	//A data provider that contains the data used to create a user
	@DataProvider(name = "create-user")
	public Object[][] createUserCred(){
		Object[][] create_user_credentials = new Object[1][4];

		create_user_credentials[0][0] = InstanceVariables.employee_name;
		create_user_credentials[0][1] = InstanceVariables.username;
		create_user_credentials[0][2] = InstanceVariables.password;
		create_user_credentials[0][3] = InstanceVariables.confirm_password;

		return create_user_credentials;
	}

	//A method that creates a user
	@Test(priority = 1,dataProvider= "create-user")
	public void createUser(String emp_name,String uname,String pword,String cpword) throws InterruptedException {

		//Select user role
		Select select = new Select(ConfigClass.driver.findElement(By.id(Locators.user_role)));
		select.selectByVisibleText(InstanceVariables.user_role);

		//Input employee name
		WebElement employee_name = ConfigClass.driver.findElement(By.id(Locators.employee_name));
		employee_name.clear();
		employee_name.sendKeys(emp_name);

		//Input username
		WebElement username = ConfigClass.driver.findElement(By.id(Locators.username));
		username.clear();
		username.sendKeys(uname);

		//Input password
		WebElement password = ConfigClass.driver.findElement(By.id(Locators.password));
		password.clear();
		password.sendKeys(pword);

		//Input confirm password
		WebElement confirm_password = ConfigClass.driver.findElement(By.id(Locators.confirm_password));
		confirm_password.clear();
		confirm_password.sendKeys(cpword);

		//Save button
		WebElement save_button = ConfigClass.driver.findElement(By.name(Locators.save_button));
		Thread.sleep(3000);
		save_button.click();

	}

	//A method that validates if the status dropdown has been selected to enabled
	@Test(priority = 2)
	public void validateStatusDropDown(){

		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("Status dropdown validation","Verifying if status dropdown is selected to " + InstanceVariables.desired_selected_status);

		//Locates the select on the webpage
		Select status_select = new Select(ConfigClass.driver.findElement(By.id(Locators.status_dropdown)));

		//A decision block that evaluates if the status has been selected to enabled
		if(status_select.getFirstSelectedOption().getText().equalsIgnoreCase(InstanceVariables.desired_selected_status)) {
			ConfigClass.result = true;
			ConfigClass.message = "Status dropdown is selected to " + InstanceVariables.desired_selected_status;
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "Status dropdown is not selected to " + InstanceVariables.desired_selected_status;
		}

		assertTrue(ConfigClass.result);

		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(ConfigClass.message);

	}

	//A method that checks if a user was created by searching through the record
	@Test(priority = 3)
	public void validateUserCreated() throws InterruptedException {

		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("Create user validation","Validating if the user was created by searching through the record");

		Thread.sleep(2000);

		//Locates the search box and provides input
		WebElement search_box = ConfigClass.driver.findElement(By.id(Locators.search_box));
		search_box.clear();
		search_box.sendKeys(InstanceVariables.username);
		search_box.submit();

		//Locates the record username on the table
		WebElement record_uname = ConfigClass.driver.findElement(By.xpath(Locators.record_username));

		//A decision block that evaluates if a user was created or not
		if(record_uname.getText().equalsIgnoreCase(InstanceVariables.username)) {
			ConfigClass.result = true;
			ConfigClass.message = "User was created";
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "User was not created";
		}


		assertTrue(ConfigClass.result);

		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(message);

	}

}
