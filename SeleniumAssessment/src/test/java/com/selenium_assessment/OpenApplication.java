package com.selenium_assessment;


import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class OpenApplication extends ConfigClass{
	
	//A method that opens the link and validate the page title
	@Test
	public void openUrl(){
		
		//Creates a test
		ConfigClass.test = ConfigClass.extent.createTest("Open url and validate title","Opens application with provided url and validates the page title");
		
		//navigate to url
		ConfigClass.driver.get(ConfigClass.property.getProperty("url"));
		
		//A decision block that compares the expected title to the current title
		if(ConfigClass.driver.getTitle().equals(InstanceVariables.expected_title)) {
			ConfigClass.result = true;
			ConfigClass.message = "Actual title matches the expected title";
		}
		else {
			ConfigClass.result = false;
			ConfigClass.message = "Actual title does not match the expected title";
		}
		
		assertTrue(ConfigClass.result);
		
		//Setting the initial status that will be re-evaluated by the after method in the config class
		ConfigClass.test.pass(ConfigClass.message);
			
	}
	
}
