package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends ConfigClass{
	
	//A data provider that contains the login credentials
	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod(){
		Object[][] login_credentials = new Object[1][2];
		login_credentials[0][0] = InstanceVariables.login_username;
		login_credentials[0][1] = InstanceVariables.login_password;

		return login_credentials;

	}

	//Login method
	@Test(dataProvider="data-provider",priority = 1)
	public void login(String username,String password) {

		//Input username
		WebElement username_input = driver.findElement(By.id(Locators.login_username));
		username_input.clear();
		username_input.sendKeys(username);
		
		//Input password
		WebElement password_input = driver.findElement(By.id(Locators.login_password));
		password_input.clear();
		password_input.sendKeys(password);
		
		//Click on the login button
		WebElement login_button = driver.findElement(By.id(Locators.login_button));
		login_button.click();

	}
	
	
	//A method that checks if the url contains the dashboard keyword
	@Test(priority = 2)
	public void validateUrl() {
		
		//Creates a test
		test = extent.createTest("Dashboard keyword validation","Validating if url contains the keyword " + InstanceVariables.keyword);
		
		//A decision block that checks if the url contains the dashboard keyword
		if(driver.getCurrentUrl().contains(InstanceVariables.keyword)) {	
			result = true;
			message = "Url contains the keyword " + InstanceVariables.keyword;
		}
		else {
			result = false;
			message = "Url does not contain the keyword " + InstanceVariables.keyword;
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
	
	}
	
	//A method that checks if the admin tab is displayed on the web page
	@Test(priority = 3)
	public void checkDisplay() {
		
		//Creates a test
		test = extent.createTest("Admin tab validation", "Validating if the admin tab displays");
		
		//Locates the admin tab 
		WebElement admin_tab = driver.findElement(By.id(Locators.admin_tab));
		
		
		//A decision block that checks if the tab is displayed
		if(admin_tab.isDisplayed()) {
			result = true;
			message = "Admin tab is displayed";
		}
		else {
			result = false;
			message = "Admin tab is not displayed";
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
		
	}
	
	
	//A method that checks if 'welcome admin' is displayed at the right panel of the app
	@Test(priority = 4)
	public void validateWelcomeAdmin() {
		test = extent.createTest("welcome admin validation","Welcome Admin should display at right panel");
		WebElement welcome_admin = driver.findElement(By.id(Locators.welcome_admin));
		
		if(welcome_admin.isDisplayed()) {
			result = true;
			message = "welcome admin is displayed";
		}
		else {
			result = false;
			message = "welcome admin is not displayed";
		}
		
		assertTrue(result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		test.pass(message);
		
	}
	
}
