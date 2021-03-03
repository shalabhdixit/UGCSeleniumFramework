package com.cigniti.support;



import java.io.File;

import org.testng.annotations.AfterTest;
//import org.testng.annotations.AfterClass;
//import org.openqa.selenium.By;
//import org.testng.annotations.Test;
//import org.openqa.selenium.
//import java.util.concurrent.TimeUnit;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.BeforeClass;
//import com.ugc.businessLibrary.BusinessFunctions;





import com.cigniti.accelerators.ActionEngine;
import com.jacob.com.LibraryLoader;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;
import atu.alm.wrapper.IDefect;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.ITestCaseRun;
import atu.alm.wrapper.ITestSet;
import atu.alm.wrapper.enums.DefectPriority;
import atu.alm.wrapper.enums.DefectSeverity;
import atu.alm.wrapper.enums.DefectStatus;

public class SeleniumALMIntegration extends ActionEngine {

	public static String pathTestSet = "Root\\QA Test Sets\\0. Cigniti POC\\MIGNG_RegressionTest_UFT";
	public static String testSet = "MIGNG - Automated - Pipeline_FIXUAT";
	public static String test = "[1]Search by UG ID";
	public static int testID = 72155;
	
	@AfterTest
	public void login() throws InterruptedException, ALMServiceException {
//		// Open Wordpress App
//		driver.get("http://demo.opensourcecms.com/wordpress/wp-login.php");
//		// Enter UserName
//		Thread.sleep(3000);
//		driver.findElement(By.id("user_login")).clear();
//		driver.findElement(By.id("user_login")).sendKeys("admin");
//		// Enter Password
//		driver.findElement(By.id("user_pass")).clear();
//		driver.findElement(By.id("user_pass")).sendKeys("demo123");
//		// Click on Submit button
//		driver.findElement(By.id("wp-submit")).submit();
		updateResults();
		System.out.println("Done Updating Results");
	}

	public static void updateResults() throws ALMServiceException {
		ALMServiceWrapper wrapper = new ALMServiceWrapper("http://genqcp001/qcbin");
		wrapper.connect("agarwar", "Krishna12!", "RELEASES", "UGC");
		wrapper.updateResult(pathTestSet, testSet, testID, test, StatusAs.PASSED);
		wrapper.close();
	}

	public static void main(String[] args) throws ALMServiceException {
		File dllFile = new File("lib\\jacob-1.18-x64.dll");
//		File dllOtaClientFile = new File("C:\\Program Files (x86)\\Common Files\\Mercury Interactive\\TDAPIClient\\OTAClient.dll");
		System.setProperty("jacob.dll.path", dllFile.getAbsolutePath());
//		System.setProperty("OTAClient.dll.path", dllOtaClientFile.getAbsolutePath());
		LibraryLoader.loadJacobLibrary();
		updateResults();
		System.out.println("Done Updating Results");
//		createTestCaseRunAndTestCaseExecutionSteps();
//		createDefect();
//		createAttachmentForTestSet();
//		System.out.println("Done!!");
	}

	/*
	 * Example For Creating an Attachment for a TestSet
	 */
	public static void createAttachmentForTestSet() throws ALMServiceException {
		ALMServiceWrapper wrapper = new ALMServiceWrapper("http://genqcp001/qcbin");
		wrapper.connect("agarwar", "Krishna12!", "RELEASES", "UGC");
		ITestSet testSet = wrapper.getTestSet("SampleTestSetFolder\\SubTestSetFolder1", "TestSet1", 2);
		wrapper.newAttachment("D:\\Data.xlsx", "My Attachment Description", testSet);
		wrapper.close();
	}

	/*
	 * Example For Creating a new Defect and Attaching a File
	 */
	public static void createDefect() throws ALMServiceException {
		ALMServiceWrapper wrapper = new ALMServiceWrapper("http://genqcp001/qcbin");
		wrapper.connect("agarwar", "Krishna12!", "RELEASES", "UGC");
		IDefect defect = wrapper.newDefect();
		defect.isReproducible(true);
		defect.setAssignedTo("admin");
		defect.setDetectedBy("admin");
		defect.setDescription("My Defect Description");
		defect.setDetectionDate("12/1/2013");
		defect.setPriority(DefectPriority.HIGH);
		defect.setSeverity(DefectSeverity.MEDIUM);
		defect.setStatus(DefectStatus.OPEN);
		defect.setSummary("My Defect/Bug Summary");
		System.out.println(defect.getDefectID());
		wrapper.newAttachment("D:\\Data.xlsx", "My Attachment Description",
				defect);
		defect.save();
		wrapper.close();
	}

	public static void createTestCaseRunAndTestCaseExecutionSteps() throws ALMServiceException {
		ALMServiceWrapper wrapper = new ALMServiceWrapper("http://genqcp001/qcbin");
		wrapper.connect("agarwar", "Krishna12!", "RELEASES", "UGC");

		// Update Test Case Result and Attach a File
		ITestCase loginTest = wrapper.updateResult(
				"SampleTestSetFolder\\SubTestSetFolder1", "TestSet1", 2, "Login", StatusAs.PASSED);
		wrapper.newAttachment("D:\\Data.xlsx", "My Attachment Description",	loginTest);

		// Update Test Case Result and Attach a File
		ITestCase logoutTest = wrapper.updateResult(
				"SampleTestSetFolder\\SubTestSetFolder1", "TestSet1", 2, "Logout", StatusAs.NOT_COMPLETED);
		wrapper.newAttachment("D:\\Data.xlsx", "My Attachment Description",	logoutTest);

		// Create a new Run, Add the Steps for this Run and Attach a File
		ITestCaseRun loginRun = wrapper.createNewRun(loginTest, "Run 1", StatusAs.PASSED);
		wrapper.addStep(loginRun, "Enter username", StatusAs.PASSED, "Enters username", "enter", "enter");
		wrapper.addStep(loginRun, "Enter password", StatusAs.PASSED, "Enters password", "enter", "enter");
		wrapper.addStep(loginRun, "Click Login", StatusAs.PASSED, "Enters username", "", "");
		wrapper.newAttachment("D:\\Data.xlsx", "My Attachment Description", loginRun);

		// Create a new Run, Add the Steps for this Run and Attach a File
		ITestCaseRun logoutRun = wrapper.createNewRun(logoutTest, "Run 2",	StatusAs.PASSED);
		wrapper.addStep(logoutRun, "Enter username", StatusAs.PASSED,	"Enters username", "enter", "enter");
		wrapper.addStep(logoutRun, "Enter password", StatusAs.PASSED,	"Enters password", "enter", "enter");
		wrapper.addStep(logoutRun, "Click Login", StatusAs.PASSED,	"Enters username", "", "");
		wrapper.newAttachment("D:\\Data.xlsx", "My Attachment Description", logoutRun);
		wrapper.close();
	}
}
