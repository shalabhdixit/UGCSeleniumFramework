package com.ugc.scripts;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

//import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.accelerators.ActionEngine;
import com.cigniti.utilities.Data_Provider;
//import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_LoginPage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class UploadFannieMae_NewQuote extends ActionEngine {
	@Test(dataProvider = "get_TestData")
	public void Upload_Fannie_Mae_New_Quote (String borrowerLastName,
			String lenderLoanNumber, String baseLoanAmount,
			String amortizationTerm, String amortizationType,
			String interestRate, String ltv, String cltv, String loanPurpose,
			String buyDown, String affordableHousing,
			String corporateRelocation, String auSystem, String auDecision,
			String originationType, String propertyZipCode,
			String propertyType, String occupancy, String hazardInsurance,
			String realEstateTaxes, String hoaFloodOther,
			String occupySubjectProperty, String selfEmployed,
			String monthlyIncome, String otherMonthlyPayment,
			String creditScore, String priorBankruptcy, String miCoverage,
			String miPaymentType, String miPaymentOption,
			String financedPremium, String upFrontPremium, String postPay, String quoteType) throws Throwable{
		
		try {
			// Login to MIGNG application
			Robot robot = new Robot();
			StringSelection stringSelection=null;
			if (MIGNG_logIn()) {
				SuccessReport("Login MIGuide NG",	"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			// Create New Quote
			if (CreateNewQuoteUpload(robot,stringSelection)) {
				SuccessReport("Create New QuoteUpload",	"New QuoteUpload verified successfully");
			} else {
				failureReport("Create New QuoteUpload", "Unable to Create New QuoteUpload");
			}
			if (CreateNewQuoteUpload1(creditScore,robot,stringSelection)) {
				SuccessReport("Create New QuoteUpload",
						"New QuoteUpload created successfully");
			} else {
				failureReport("Create New QuoteUpload", "Unable to Create New QuoteUpload");
			}
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			
			if (MIGNG_logout()) {
				SuccessReport("Logout MIGuide NG",
						"Successfully logged out from MIGuide NG");
			} else {
				failureReport("Logout MIGuide NG", "Unable to log out");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean CreateNewQuoteUpload(Robot robot,StringSelection stringSelection) throws Throwable {
		try {
			Thread.sleep(2000);
			click(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			click(MIGNG_NewQuote.MIGNG_PreFillFile, "Pre-fill details with a Fannie Mae file ");
			Thread.sleep(3000);
			JSClick(MIGNG_NewQuote.MIGNG_CloseButton, "CloseButton");
			Thread.sleep(3000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			click(MIGNG_NewQuote.MIGNG_PreFillFile, "Pre-fill details with a Fannie Mae file ");
			Thread.sleep(2000);
			JSClick(MIGNG_NewQuote.MIGNG_ChooseFile, "ChooseFile");
			   stringSelection = new StringSelection(new File("Files/" + "lptest1.dat").getAbsolutePath());
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
				robot.delay(3000);
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_TAB);
	            robot.keyRelease(KeyEvent.VK_TAB);
	            Thread.sleep(500);
	            robot.keyPress(KeyEvent.VK_TAB);
	            robot.keyRelease(KeyEvent.VK_TAB);
	            robot.delay(3000);
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            Thread.sleep(2000);
			JSClick(MIGNG_NewQuote.MIGNG_UploadButton, "UploadButton");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_alartMessage, "Your file processed");
			Thread.sleep(1000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean CreateNewQuoteUpload1(String creditScore,Robot robot,StringSelection stringSelection) throws Throwable {
		try {
			Thread.sleep(2000);
			click(MIGNG_NewQuote.MIGNG_PreFillFile, "Pre-fill details with a Fannie Mae file ");
			JSClick(MIGNG_NewQuote.MIGNG_ChooseFile, "ChooseFile");
		    stringSelection = new StringSelection(new File("Files/" + "ZIP_Kentucky.FNM").getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		  robot.delay(3000);
		  robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
       robot.delay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
      
			JSClick(MIGNG_NewQuote.MIGNG_UploadButton, "UploadButton");
			Thread.sleep(2000);
			type(MIGNG_NewQuote.MIGNG_creditScore,creditScore, "creditScore" );
			JSClick(MIGNG_NewQuote.MIGNG_getQuote, "GetQuoteButton");
			Thread.sleep(1500);
			if(isElementPresent(MIGNG_NewQuote.MIGNG_spinner, "Spinner")){
				SuccessReport("Quote in progress spinner", "Quote in progress verified");
				waitForInVisibilityOfElement(MIGNG_NewQuote.MIGNG_spinner, "Spinner");
			}
			
			waitForVisibilityOfElement(MIGNG_NewQuote.MIGNG_orderFullFile,	"Order Full-File");
			if (isElementPresent(MIGNG_NewQuote.MIGNG_orderFullFile, "Order Full-File")) {
				SuccessReport("Verify presence of Order Full-File", "Order Full-File is present");
			} else {
				failureReport("Verify presence of Order Full-File", "Order Full-File is not present");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

	public boolean MIGNG_logIn() throws Throwable {
		boolean flag = true;
		try {
				MIG_logIn("MIGNG_userName", "MIGNG_passWord");
				Thread.sleep(3000);
	
				// Verify if Home Page appeared successfully
				if (MIGNG_verifyHomePage()) {
					SuccessReport("Verify MIGNG HomePage", 	"MIGNG HomePage loaded successfully");
				} else {
					failureReport("Verify MIGNG HomePage", "MIGNG HomePage failed to load successfully");
				}
				Thread.sleep(2000);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	public boolean MIG_logIn(String userName, String passWord) throws Throwable {
		String MIGNG_userName = configProps.getProperty(userName);
		String MIGNG_passWord = configProps.getProperty(passWord);

		boolean flag = true;
//		Thread.sleep(3000);
//		launchUrl(configProps.getProperty("MIGNG_MRQA"));
		launchUrl(configProps.getProperty("MIGNG_FixUAT"));
		Thread.sleep(3000);

		try {
			// Wait for the availability of UserName field on MIGNG login Page
			waitForVisibilityOfElement(MIGNG_LoginPage.MIGNG_userName,	"Username field");
			type(MIGNG_LoginPage.MIGNG_userName, MIGNG_userName, "Username field");

			// Wait for the availability of Password field on MIGNG login Page
			waitForVisibilityOfElement(MIGNG_LoginPage.MIGNG_password,	"Password field");
			type(MIGNG_LoginPage.MIGNG_password, MIGNG_passWord, "Password field");

			// Wait for the availability of Login button on MIGNG login Page
			waitForVisibilityOfElement(MIGNG_LoginPage.MIGNG_login, "Login button");
			click(MIGNG_LoginPage.MIGNG_login, "Login button");

			Thread.sleep(3000);
			//	wait for Pipeline to load
//			waitForInVisibilityOfElement(MIGNG_HomePage.MIGNG_emptySearch, "Pipeline loaded");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public boolean MIGNG_verifyHomePage() throws Throwable {
		try {
//			Thread.sleep(3000);

			if (isElementPresent(MIGNG_HomePage.MIGNG_pipeline, "HomePage")) {
				SuccessReport("Verfiy HomePage", "HomePage loaded successfully");
			} else {
				failureReport("Verfiy HomePage", "HomePage failed to load");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline")) {
				SuccessReport("Verfiy Pipeline on Header",
						"Pipeline is available on the Header");
			} else {
				failureReport("Verfiy Pipeline on Header",
						"Pipeline is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote")) {
				SuccessReport("Verfiy New Quote on Header",
						"New Quote is available on the Header");
			} else {
				failureReport("Verfiy New Quote on Header",
						"New Quote is not available on the Header");
			}

			if (getText(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-File")
					.contains("New Full-File")) {
				SuccessReport("Verfiy New Full-File Application on Header",
						"New Full-File Application is available on the Header");
			} else {
				failureReport("Verfiy New Full-File Application on Header",
						"New Full-File Application is not available on the Header");
			}

			if (getText(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated")
					.contains("Delegated")) {
				SuccessReport("Verfiy Delegated (RapidLink) on Header",
						"Delegated (RapidLink) is available on the Header");
			} else {
				failureReport("Verfiy Delegated (RapidLink) on Header",
						"Delegated (RapidLink) is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerReports, "Reports")) {
				SuccessReport("Verfiy Reports on Header",
						"Reports is available on the Header");
			} else {
				failureReport("Verfiy Reports on Header",
						"Reports is not available on the Header");
			}

			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineData, "Pipeline")) {
				SuccessReport("Verfiy Pipeline", "Pipeline data is displayed");
			} else {
				failureReport("Verfiy Pipeline",
						"Pipeline data is not displayed");
			}

			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineQuote,
					"Quote and/or Application")) {
				SuccessReport("Verfiy Quote and/or Application",
						"Quote and/or Application is displayed in Pipeline table");
			} else {
				failureReport("Verfiy Quote and/or Application",
						"Quote and/or Application is displayed in Pipeline table");
			}

//			Thread.sleep(3000);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean MIGNG_logout() throws Throwable {
		boolean flag = true;

		try {
			// Wait for the availability of Sign out button on MIGNG login Page
			waitForVisibilityOfElement(MIGNG_HomePage.MIGNG_logout,
					"Sign out button");
			click(MIGNG_HomePage.MIGNG_logout, "Sign out button");

			Thread.sleep(3000);
			// Wait for the availability of UserName field on MIGNG login Page
			boolean flag1 = waitForVisibilityOfElement(
					MIGNG_LoginPage.MIGNG_userName, "Username field");

			// Wait for the availability of Password field on MIGNG login Page
			boolean flag2 = waitForVisibilityOfElement(
					MIGNG_LoginPage.MIGNG_password, "Password field");

			// Wait for the availability of Login button on MIGNG login Page
			boolean flag3 = waitForVisibilityOfElement(
					MIGNG_LoginPage.MIGNG_login, "Login button");
			flag = flag1 && flag2 && flag3;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("UploadFNM");
	}
}
