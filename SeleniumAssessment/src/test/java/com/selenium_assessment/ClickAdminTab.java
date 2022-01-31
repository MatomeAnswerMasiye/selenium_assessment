package com.selenium_assessment;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ClickAdminTab extends ConfigClass{
	
	//A method that clicks on the admin tab
	@BeforeClass
	public void clickAdminTab() {
		
		//Get admin tab and click on it
		WebElement admin_tab = ConfigClass.driver.findElement(By.id(Locators.admin_tab));
		admin_tab.click();
		
	}
	
	//A method that checks if the url contains 'admin/viewSystemUsers'
	@Test(priority = 1)
	public void validateUrl() {
		
		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("admin/viewSystemUsers url validation","Validating if url contains " + InstanceVariables.admin_url_path);
		
		//A decision block that validates if the url contains admin/viewSystemUsers	
		if(ConfigClass.driver.getCurrentUrl().contains(InstanceVariables.admin_url_path)) {
			ConfigClass.result = true;
			ConfigClass.message = "Url contains " + InstanceVariables.admin_url_path;
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "Url does not contain " + InstanceVariables.admin_url_path;
		}
		
		assertTrue(ConfigClass.result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(ConfigClass.message);
	
	}
	
	//A method that checks that desired buttons are displayed
	@Test(priority = 2)
	public void checkDisplay() {
		
		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("Buttons display validation", "Validating if the add, delete, search, reset buttons are be displayed");
		
		//A hashmap to store the desired the buttons in key-value pairs
		HashMap<String,WebElement> buttons = new HashMap<String,WebElement>();
		buttons.put("add",  ConfigClass.driver.findElement(By.id(Locators.add_button)));
		buttons.put("delete",  ConfigClass.driver.findElement(By.id(Locators.delete_button)));
		buttons.put("search", ConfigClass.driver.findElement(By.id(Locators.search_button)));
		buttons.put("reset", ConfigClass.driver.findElement(By.id(Locators.reset_button)));
		
		//Iterates through each button and check if it's displayed
		for(String button : buttons.keySet()) {
			if(buttons.get(button).isDisplayed()) {
				ConfigClass.result = true;
				ConfigClass.message = button + " button is displayed";
			}
			else {
				ConfigClass.result = false;
				ConfigClass.message = button + "button is not displayed";
			}
			
			assertTrue(ConfigClass.result);
			
			//Setting the initial status that will be re-evaluated by the after method in the config class
			ConfigClass.test.pass(ConfigClass.message);
			
		}
				
	}
	
}
