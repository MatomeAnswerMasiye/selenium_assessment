/*
 * @Author : Matome Answer Masiye
 * @Date : 2022/01/30
 * @Description : A test script that validates functionalities of a web application(OrangeHRM demo)
 */

package com.selenium_assessment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

//A class that configures everything needed in the other classes/tests
public class ConfigClass {

	//Variables
	static public Logger logger;
	static public WebDriver driver;
	static ExtentReports extent;
	static ExtentTest test;
	static Properties property;
	static File srcFile;
	static String message;
	static boolean result;
	

	//A method that runs once before all the tests
	@BeforeSuite
	public void setUp() throws Exception{

		//Web driver configuration
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		//Extent reports configuration
		extent = new ExtentReports();
		ExtentSparkReporter spark_reporter = new ExtentSparkReporter("./src/test/resources/reporter.html");
		extent.attachReporter(spark_reporter);

		//Log4j configuration
		logger = LogManager.getLogger(OpenApplication.class);

		//Properties configuration 
		FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
		property = new Properties();
		property.load(fis);

	}
	
	//A method that runs after every test to re-evaluate the set status(pass) in the test cases and report accordingly
	@AfterMethod
	public void evaluateStatus(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.fail(message);
			logger.info(message);
			
			srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(srcFile, new File("./src/test/resources/image.png"));
			
		}
	}

	//A method that runs once after all the tests
	@AfterSuite
	public void wrapUp() throws InterruptedException {
		Thread.sleep(5000);
		extent.flush();
		driver.close();
	}

}
