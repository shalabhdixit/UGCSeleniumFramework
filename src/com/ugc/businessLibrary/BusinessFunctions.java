package com.ugc.businessLibrary;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
//import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.openqa.selenium.remote.LocalFileDetector;
//import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.Select;

import com.cigniti.accelerators.ActionEngine;
import com.cigniti.utilities.UploadFileRobot;
import com.ugc.objectRepository.MIGNG_Documentum;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_LoginPage;
import com.ugc.objectRepository.MIGNG_NewApplication;
import com.ugc.objectRepository.MIGNG_NewQuote;
import com.ugc.objectRepository.MIGNG_PDFValidation;
import com.ugc.objectRepository.MIGNG_RAPidLink;
import com.ugc.objectRepository.MIGNG_RateRunner;
import com.ugc.objectRepository.MIGNG_Validation;

public class BusinessFunctions extends ActionEngine {

	String faculty_role;
	public static String classNumber;

	public static String randno = RandomNumberGeneration(8500);
	public static String randNum = RandomNumberGeneration(8500);

	public void webdevUrlLogin() throws Throwable {
		launchUrl(configProps.getProperty("webdev_url"));
	}

	public void webDavContentverifcation() throws Throwable {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		int linkcount = links.size();

		System.out.println("Links Count is : " + linkcount);
		if (linkcount > 2) {
			SuccessReport("WebDev", "Web Dev Contains the content");
		} else {
			failureReport("WebDev", "Web Dev does not Contains the any content");
		}
	}

	public boolean logInMIG(String userName, String passWord) throws Throwable {
		String MIGNG_userName = configProps.getProperty(userName);
		String MIGNG_passWord = configProps.getProperty(passWord);

		boolean flag = true;
//		Thread.sleep(3000);
		String migngURLVariable = configProps.getProperty("varMIGNG_URL");
		//		launchUrl(configProps.getProperty("MIGNG_MRQA"));
//		launchUrl(configProps.getProperty("MIGNG_FixUAT"));
		launchUrl(configProps.getProperty(migngURLVariable));
		Thread.sleep(3000);

		try {
			// Wait for the availability of UserName field on MIGNG login Page
			type(MIGNG_LoginPage.MIGNG_userName, MIGNG_userName, "Username field");
			// Wait for the availability of Password field on MIGNG login Page
			type(MIGNG_LoginPage.MIGNG_password, MIGNG_passWord, "Password field");

			// Wait for the availability of Login button on MIGNG login Page
			click(MIGNG_LoginPage.MIGNG_login, "Login button");

			Thread.sleep(3000);
			//	wait for Pipeline to load
//			waitForInVisibilityOfElement(MIGNG_HomePage.MIGNG_emptySearch, "Pipeline loaded");
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			throw new Exception();
		}
		return flag;
	}

	
	public boolean logInMIGNG() throws Throwable {
		boolean flag = true;
		try {
				logInMIG("MIGNG_userName", "MIGNG_passWord");
				Thread.sleep(3000);
	
				// Verify if Home Page appeared successfully
				if (verifyHomePageMIGNG()) {
					SuccessReport("Verify MIGNG HomePage", 	"MIGNG HomePage loaded successfully");
				} else {
					failureReport("Verify MIGNG HomePage", "MIGNG HomePage failed to load successfully");
				}
				Thread.sleep(2000);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			throw new Exception();
		}
		return flag;
	}

	public boolean logInMIGNGQuapper() throws Throwable {
		boolean flag = true;
		try {
				logInMIG("cert_finder_Uname", "cert_finder_Password");
				Thread.sleep(3000);
				// Verify if Home Page appeared successfully
				if (verifyPortfolioPage()) {
					SuccessReport("Verify MIGNG HomePage", 	"MIGNG HomePage loaded successfully");
				} else {
					failureReport("Verify MIGNG HomePage", "MIGNG HomePage failed to load successfully");
				}
				Thread.sleep(2000);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			throw new Exception();
		}
		return flag;
	}

	public boolean verifyHomePageRR() throws Throwable {
		try {
				Thread.sleep(3000);
				if (isElementPresent(MIGNG_RateRunner.MIGNG_rrMiCompare, "Rate Runner - MI Compare")){
//						&& isElementPresent(MIGNG_RateRunner.MIGNG_rateRunnerLogo, "Rate Runner Logo")) {
					SuccessReport("Verfiy HomePage", "HomePage loaded successfully");
				} else {
					failureReport("Verfiy HomePage", "HomePage failed to load");
				}
				Thread.sleep(3000);
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void validatePDFUnknown(String quoteNumber) throws Throwable {
		try{
			String pdfValue = "";
			// extract values from PDF
			pdfValue = getText(MIGNG_PDFValidation.quoteNumber,	"PDF Quote Number");
			if (pdfValue.equalsIgnoreCase(quoteNumber)) {
				SuccessReport("PDF: Validate Quote Number", "PDF: Quote Number validated successfully. " + quoteNumber);
			} else {
				failureReport("PDF: Validate Quote Number",	"PDF: Quote Number does not match." + pdfValue);
			}

			pdfValue = getText(MIGNG_PDFValidation.affordableHousing, "PDF Affordable Housing");
			if (pdfValue.equalsIgnoreCase("Unknown")) {
				SuccessReport("PDF: Validate Affordable Housing", "PDF: Affordable Housing validated successfully. " + pdfValue);
			} else {
				failureReport("PDF: Validate Affordable Housing", "PDF: Affordable Housing does not match. " + pdfValue);
			}
			
			pdfValue = getText(MIGNG_PDFValidation.originationType,	"PDF Origination Type");
			if (pdfValue.equalsIgnoreCase("Unknown")) {
				SuccessReport("PDF: Validate Origination Type", "PDF: Origination Type validated successfully. " + pdfValue);
			} else {
				failureReport("PDF: Validate Origination Type",	"PDF: Origination Type does not match." + pdfValue);
			}
			
			pdfValue = getText(MIGNG_PDFValidation.propertyType, "PDF Property Type");
			if (pdfValue.equalsIgnoreCase("Unknown")) {
				SuccessReport("PDF: Validate Property Type", "PDF: Property Type validated successfully. " + pdfValue);
			} else {
				failureReport("PDF: Validate Property Type", "PDF: Property Type does not match." + pdfValue);
			}
			
			pdfValue = getText(MIGNG_PDFValidation.selfEmployed, "PDF Self Employed");
			if (pdfValue.equalsIgnoreCase("Unknown")) {
				SuccessReport("PDF: Validate Self Employed", "PDF: Self Employed validated successfully. " + pdfValue);
			} else {
				failureReport("PDF: Validate Self Employed", "PDF: Self Employed does not match." + pdfValue);
			}
			
			pdfValue = getText(MIGNG_PDFValidation.priorBankruptcy,	"PDF Prior Bankruptcy");
			if (pdfValue.equalsIgnoreCase("Unknown")) {
				SuccessReport("PDF: Validate Prior Bankruptcy", "PDF: Prior Bankruptcy validated successfully. " + pdfValue);
			} else {
				failureReport("PDF: Validate Prior Bankruptcy", "PDF: Prior Bankruptcy does not match." + pdfValue);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public boolean logoutMIG(By locator) throws Throwable {
		boolean flag = true;
		try {
			// Wait for the availability of Sign out button on MIGNG login Page
			click(locator, "Sign out button");
			Thread.sleep(3000);
			// Wait for the availability of UserName field on MIGNG login Page
			boolean flag1 = waitForVisibilityOfElement(MIGNG_LoginPage.MIGNG_userName, "Username field");
			// Wait for the availability of Password field on MIGNG login Page
			boolean flag2 = waitForVisibilityOfElement(MIGNG_LoginPage.MIGNG_password, "Password field");
			// Wait for the availability of Login button on MIGNG login Page
			boolean flag3 = waitForVisibilityOfElement(MIGNG_LoginPage.MIGNG_login, "Login button");
			flag = flag1 && flag2 && flag3;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public void logoutMIGNG() throws Throwable {
//		boolean flag = true;
		try {
//				flag = MIG_logout(MIGNG_HomePage.MIGNG_logout);
				if (logoutMIG(MIGNG_HomePage.MIGNG_logout)) {
					SuccessReport("Logout MIGuide NG",	"Successfully logged out from MIGuide NG");
				} else {
					failureReport("Logout MIGuide NG", "Unable to log out");
				}
		} catch (Exception e) {
//			flag = false;
			e.printStackTrace();
		}
//		return flag;
	}
	
	
	public void waitForQuoteToComplete() throws Throwable{
		try{
			if(isElementPresent(MIGNG_NewQuote.MIGNG_spinner, "Spinner")){
				SuccessReport("Quote in progress spinner", "Quote in progress verified");
				waitForInVisibilityOfElement(MIGNG_NewQuote.MIGNG_spinner, "Spinner");
			}else{
				failureReport("Quote in progress spinner", "Quote in progress could not be verified");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public boolean verifyHomePageMIGNG() throws Throwable {
		try {
			if (isElementPresent(MIGNG_HomePage.MIGNG_pipeline, "HomePage")) {
				SuccessReport("Verfiy HomePage", "HomePage loaded successfully");
			} else {
				failureReport("Verfiy HomePage", "HomePage failed to load");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline")) {
				SuccessReport("Verfiy Pipeline on Header",	"Pipeline is available on the Header");
			} else {
				failureReport("Verfiy Pipeline on Header",	"Pipeline is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote")) {
				SuccessReport("Verfiy New Quote on Header",	"New Quote is available on the Header");
			} else {
				failureReport("Verfiy New Quote on Header",	"New Quote is not available on the Header");
			}

			if (getText(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-File").contains("New Full-File")) {
				SuccessReport("Verfiy New Full-File Application on Header",	"New Full-File Application is available on the Header");
			} else {
				failureReport("Verfiy New Full-File Application on Header",	"New Full-File Application is not available on the Header");
			}

			if (getText(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated").contains("Delegated")) {
				SuccessReport("Verfiy Delegated (RapidLink) on Header", "Delegated (RapidLink) is available on the Header");
			} else {
				failureReport("Verfiy Delegated (RapidLink) on Header",	"Delegated (RapidLink) is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerReports, "Reports")) {
				SuccessReport("Verfiy Reports on Header", "Reports is available on the Header");
			} else {
				failureReport("Verfiy Reports on Header", "Reports is not available on the Header");
			}

			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineData, "Pipeline")) {
				SuccessReport("Verfiy Pipeline", "Pipeline data is displayed");
			} else {
				failureReport("Verfiy Pipeline", "Pipeline data is not displayed");
			}

			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineQuote, "Quote and/or Application")) {
				SuccessReport("Verfiy Quote and/or Application", "Quote and/or Application is displayed in Pipeline table");
			} else {
				failureReport("Verfiy Quote and/or Application", "Quote and/or Application is displayed in Pipeline table");
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createNewApplication(String SecureQuoteNumber,
			String BorrowerFirstName, String BorrowerLastName,
			String AffordableHousing, String CorporateRelocation,
			String OriginationType, String MICoverage, String miPaymentType,
			String MIPaymentOption, String FinancedPremium,
			String UpfrontPremium, String PostPay, String ThisSubmission,
			String SpecialHandlingInstructions, String Email, String Phone,
			String Fax, String UploadDocument, String IsNegativeTest)
			throws Throwable {
		try {
			// Thread.sleep(3000);
			click(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-file Application");

			Thread.sleep(2000);
			assertText(MIGNG_HomePage.MIGNG_applicationHeader, "New Full-file Application");

			fillNewApplicationForm(SecureQuoteNumber, BorrowerFirstName,
					BorrowerLastName, AffordableHousing, CorporateRelocation,
					OriginationType, MICoverage, miPaymentType,
					MIPaymentOption, FinancedPremium, UpfrontPremium, PostPay,
					ThisSubmission, SpecialHandlingInstructions, Email, Phone,
					Fax);

			if (!UploadDocument.equals("")) {
				String[] files = UploadDocument.split(";");
				String[] test = IsNegativeTest.split(";");
				int idx = 0;
				for (int i = 0; i < files.length; i++) {

					// call upload document method
					if (test[i].equals("N")) {
						idx = idx + 1;
						uploadDocument(files[i], idx);
					}

					// Click on Submit button
					click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");

					// Validate message after submitting application
					validateSubmitApplication(test[i]);
				}
			} else {
				// Click on Submit button
				click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");
				// Validate message after submitting application
				validateSubmitApplication(IsNegativeTest);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createNewQuote(String borrowerLastName,
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
			String financedPremium, String upFrontPremium, String postPay,
			String quoteType) throws Throwable {
		try {

			click(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");

			if(isElementPresent(MIGNG_NewQuote.MIGNG_quoteNumber, "Quote Number")){
				String text = driver.findElement(MIGNG_NewQuote.MIGNG_quoteNumber).getText();
				if(text.contains("New Quote")){
					SuccessReport("Quote Number Not Displayed before Get Quote", "Validation successful");
				}else{
					failureReport("Quote Number Not Displayed before Get Quote", "Validation failed");
				}
			}
			
			fillNewQuoteForm(borrowerLastName, lenderLoanNumber,
					baseLoanAmount, amortizationTerm, amortizationType,
					interestRate, ltv, cltv, loanPurpose, buyDown,
					affordableHousing, corporateRelocation, auSystem,
					auDecision, originationType, propertyZipCode, propertyType,
					occupancy, hazardInsurance, realEstateTaxes, hoaFloodOther,
					occupySubjectProperty, selfEmployed, monthlyIncome,
					otherMonthlyPayment, creditScore, priorBankruptcy,
					miCoverage, miPaymentType, miPaymentOption,
					financedPremium, upFrontPremium, postPay, quoteType);

			// Click on Get Quote button
			click(MIGNG_NewQuote.MIGNG_getQuote, "Get Quote");
			
			// Validate message after submitting application
			validateSubmitQuote(quoteType);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean createNewQuoteUpload(String creditScore) throws Throwable {
		try {

			click(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			click(MIGNG_NewQuote.MIGNG_PreFillFile, "Pre-fill details with a Fannie Mae file ");
			JSClick(MIGNG_NewQuote.MIGNG_CloseButton, "CloseButton");
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			click(MIGNG_NewQuote.MIGNG_PreFillFile, "Pre-fill details with a Fannie Mae file ");
			JSClick(MIGNG_NewQuote.MIGNG_ChooseFile, "ChooseFile");
			if (MIGNG_UploadFanni()) {
				SuccessReport("Create New Application", "New Application Uploaded successfully");
			} else {
				failureReport("Logout MIGuide NG", "Unable to Upload File");
			}
			JSClick(MIGNG_NewQuote.MIGNG_UploadButton, "UploadButton");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_alartMessage, "Your file processed");
			Thread.sleep(1000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");
			click(MIGNG_NewQuote.MIGNG_PreFillFile, "Pre-fill details with a Fannie Mae file ");
			click(MIGNG_NewQuote.MIGNG_ChooseFile, "ChooseFile");
			if (MIGNG_UploadFanni1()) {
				SuccessReport("Create New Application", "New Application Uploaded successfully");
			} else {
				failureReport("Logout MIGuide NG", "Unable to Upload File");
			}
			JSClick(MIGNG_NewQuote.MIGNG_UploadButton, "UploadButton");
			Thread.sleep(2000);
			type(MIGNG_NewQuote.MIGNG_creditScore, "creditScore", creditScore);
			JSClick(MIGNG_NewQuote.MIGNG_getQuote, "GetQuoteButton");
			Thread.sleep(500);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean MIGNG_UploadFanni() throws Throwable {
		try {
			Robot robot = new Robot();
			StringSelection stringSelection=null;
			Thread.sleep(1000);
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean MIGNG_UploadFanni1() throws Throwable {
		try {
			StringSelection stringSelection=null;
			Thread.sleep(1000);
			    stringSelection = new StringSelection(new File("Files/" + "ZIP_Kentucky.FNM").getAbsolutePath());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			Robot robot = new Robot();
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
            robot.delay(3000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void validateSubmitQuote(String quoteType, String typeValidation) throws Throwable {
		try{
			// Validate message after submitting application
			validateSubmitQuote(quoteType);
			if(typeValidation.equalsIgnoreCase("FULL")){
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	public boolean validateSubmitQuote(String quoteType) throws Throwable {
		String quotePaymentType = "";
		String quotePaymentOption = "";
		String quoteNumber = "";
		try {

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
			
			Thread.sleep(500);
			quoteNumber = driver.findElement(MIGNG_NewQuote.MIGNG_quoteNumber).getText().trim();
			Thread.sleep(500);
			if (!quoteNumber.equals("")) {
				SuccessReport("Capture Quote Number", "Quote Number : " + quoteNumber);
			} else {
				failureReport("Capture Quote Number", "Quote Number could not found. " + quoteNumber);
			}

			if (isElementPresent(MIGNG_NewQuote.MIGNG_printPDF,
					"Print PDF link")) {
				SuccessReport("Verify presence of Print PDF link",
						"Print PDF link is present");
			} else {
				failureReport("Verify presence of Print PDF link",
						"Print PDF link is not present");
			}

			// Verify Payment Comparison grid is displayed
			if (isElementPresent(MIGNG_NewQuote.MIGNG_gridPaymentComparison,
					"Payment Comparison grid")) {
				SuccessReport("Verify Payment Comparison grid is displayed",
						"Payment Comparison grid is displayed");
			} else {
				failureReport("Verify Payment Comparison grid is displayed",
						"Payment Comparison grid is not displayed");
			}

			// Verify Payment Comparison grid Options is displayed
			int optPayment = driver.findElements(
					MIGNG_NewQuote.MIGNG_optPaymentComparison).size();
			if (optPayment > 0) {
				SuccessReport("Verify Payment Comparison grid Options",
						"Payment Comparison grid has : " + optPayment
								+ " values.");
			} else {
				failureReport("Verify Payment Comparison grid Options",
						"Payment Comparison grid options could not be found");
			}

			switch (quoteType) {
			case "BPA":
			case "BPM":
			case "BPSNR":
			case "BPSR":
			case "BPS":
				quotePaymentType = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentType,
						"Quote Payment Type");
				if (!quotePaymentType.equals("")
						&& quotePaymentType.contains("Borrower-Paid")) {
					SuccessReport("Capture Quote Payment Type",
							"Quote Payment Type : " + quotePaymentType);
				} else {
					failureReport("Capture Quote Payment Type",
							"Quote Payment Type could not found");
				}
				break;
			case "LPA":
			case "LPM":
			case "LPS":
				quotePaymentType = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentType,
						"Quote Payment Type");
				if (!quotePaymentType.equals("")
						&& quotePaymentType.contains("Lender-Paid")) {
					SuccessReport("Capture Quote Payment Type",
							"Quote Payment Type : " + quotePaymentType);
				} else {
					failureReport("Capture Quote Payment Type",
							"Quote Payment Type could not found");
				}
				break;
			}

			switch (quoteType) {
			case "BPA":
			case "LPA":
				quotePaymentOption = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentOption,
						"Quote Payment Option");
				if (!quotePaymentOption.equals("")
						&& quotePaymentOption.contains("Annual")) {
					SuccessReport("Capture Quote Payment Option",
							"Quote Payment Option : " + quotePaymentOption);
				} else {
					failureReport("Capture Quote Payment Option",
							"Quote Payment Option could not found."
									+ quotePaymentOption);
				}
				break;
			case "BPM":
			case "LPM":
				quotePaymentOption = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentOption,
						"Quote Payment Option");
				if (!quotePaymentOption.equals("")
						&& quotePaymentOption.contains("Monthly")) {
					SuccessReport("Capture Quote Payment Option",
							"Quote Payment Option : " + quotePaymentOption);
				} else {
					failureReport("Capture Quote Payment Option",
							"Quote Payment Option could not found."
									+ quotePaymentOption);
				}
				break;
			case "BPSNR":
				quotePaymentOption = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentOption,
						"Quote Payment Option");
				if (!quotePaymentOption.equals("")
						&& quotePaymentOption.contains("Single Non-Refundable")) {
					SuccessReport("Capture Quote Payment Option",
							"Quote Payment Option : " + quotePaymentOption);
				} else {
					failureReport("Capture Quote Payment Option",
							"Quote Payment Option could not found."
									+ quotePaymentOption);
				}
				break;
			case "BPSR":
				quotePaymentOption = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentOption,
						"Quote Payment Option");
				if (!quotePaymentOption.equals("")
						&& quotePaymentOption.contains("Single Refundable")) {
					SuccessReport("Capture Quote Payment Option",
							"Quote Payment Option : " + quotePaymentOption);
				} else {
					failureReport("Capture Quote Payment Option",
							"Quote Payment Option could not found."
									+ quotePaymentOption);
				}
				break;
			case "BPS":
				quotePaymentOption = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentOption,
						"Quote Payment Option");
				if (!quotePaymentOption.equals("")
						&& quotePaymentOption.contains("Split Premium Monthly")) {
					SuccessReport("Capture Quote Payment Option",
							"Quote Payment Option : " + quotePaymentOption);
				} else {
					failureReport("Capture Quote Payment Option",
							"Quote Payment Option could not be found."
									+ quotePaymentOption);
				}
				break;
			case "LPS":
				quotePaymentOption = getText(
						MIGNG_NewQuote.MIGNG_quotePaymentOption,
						"Quote Payment Option");
				if (!quotePaymentOption.equals("")
						&& quotePaymentOption.contains("Single")) {
					SuccessReport("Capture Quote Payment Option", "Quote Payment Option : " + quotePaymentOption);
				} else {
					failureReport("Capture Quote Payment Option", "Quote Payment Option could not be found."
									+ quotePaymentOption);
				}
				break;
			}

			if(!quoteType.equalsIgnoreCase("")){
				String quotePremium = getText(MIGNG_NewQuote.MIGNG_quotePremium, "Quote Premium");
				if (!quotePremium.equals("")) {
					SuccessReport("Capture Quote Premium", "Quote Premium : " + quotePremium);
				} else {
					failureReport("Capture Quote Premium",	"Quote Premium could not be found");
				}

				String premiumOption = getText(MIGNG_NewQuote.MIGNG_quotePremiumOption, "Premium Option");
				if (!premiumOption.equals("")) {
					SuccessReport("Capture Premium Option", "Premium Option : "	+ premiumOption);
				} else {
					failureReport("Capture Premium Option",	"Premium Option could not found");
				}
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// index should be zero (0) if the document passed is duplicate
	public boolean uploadDocument(String UploadDocument, int idx)
			throws Throwable {
		try {
			boolean duplicateFile = false;
			// click on Add documents
			if (!UploadDocument.equals("")) {
//				browser = configProps.getProperty("browserType");
				// System.out.println(browser);
				if (browser.equalsIgnoreCase("firefox")) {
					// JSClick(MIGNG_NewApplication.MIGNG_fileUpload,
					// "Add Documents");
					click(MIGNG_NewApplication.MIGNG_fileUploadff,
							"Add Documents");
				} else if (browser.equalsIgnoreCase("chrome")) {
					click(MIGNG_NewApplication.MIGNG_fileUploadcc,
							"Add Documents");
				} else if (browser.equalsIgnoreCase("ie")) {
					click(MIGNG_NewApplication.MIGNG_fileUploadcc,
							"Add Documents");
				}

				// upload documents
				File fileToBeUploaded = new File("Files/" + UploadDocument);
				Thread.sleep(2000);
				UploadFileRobot.uploadFile(fileToBeUploaded.getAbsolutePath());
				Thread.sleep(2000);
				
				if (idx == 0) {
					duplicateFile = true;
				}
				// Find the no of rows of data in the table
				idx = driver.findElements(
						By.xpath("//tr[@class='received-doc-row']")).size();

				// Verify whether document uploaded is the same
				By uploadedDoc = By.xpath("//tr[@class='received-doc-row'][" + idx + "]/td[1]");
				mouseover(uploadedDoc, UploadDocument);
				assertText(uploadedDoc, UploadDocument);

				// Verify if the upload status is updated
				By uploadedDocStatus = By
						.xpath("//tr[@class='received-doc-row'][" + idx + "]/td[2]");

				if (UploadDocument.toLowerCase().contains("big")) {
					assertText(uploadedDocStatus, MIGNG_Validation.msgBigFileUpload);
				} else if (duplicateFile) {
					assertText(uploadedDocStatus,
							MIGNG_Validation.msgDuplicateUpload);
				} else {
					assertText(uploadedDocStatus, "Uploaded");
				}

				// Remove Document
				By removeDoc = By.xpath("//tr[@class='received-doc-row']["
						+ idx + "]/td[3]/div/a[@name='removeLink']");
				assertText(removeDoc, "Remove");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeUploadedDocument() throws Throwable {
		try {
			int noOfDocs = driver.findElements(
					By.xpath("//tr[@class='received-doc-row']")).size();
			for (int i = noOfDocs; i > 0; i--) {
				// Verify whether document uploaded is the same
				By uploadedDoc = By.xpath("//tr[@class='received-doc-row'][" + i + "]/td[1]");
				// System.out.println(getText(uploadedDoc, ""));
				mouseover(uploadedDoc, driver.findElement(uploadedDoc).getText());
				assertText(uploadedDoc, driver.findElement(uploadedDoc).getText());

				// Verify if the upload status is updated
				By uploadedDocStatus = By.xpath("//tr[@class='received-doc-row'][" + i	+ "]/td[2]");
				assertText(uploadedDocStatus, "Uploaded");

				// Remove Document
				By removeDoc = By.xpath("//tr[@class='received-doc-row'][" + i	+ "]/td[3]/div/a[@name='removeLink']");
				assertText(removeDoc, "Remove");
				click(removeDoc, "Remove");
				// JSClick(removeDoc, "Remove");
				Thread.sleep(3000);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeDocument(String UploadDocument, Integer idx)
			throws Throwable {
		try {
			// click on Add documents
			if (!UploadDocument.equals("")) {
				// Verify whether document uploaded is the same
				By uploadedDoc = By.xpath("//tr[@class='received-doc-row'][" + idx + "]/td[1]");
				System.out.println(getText(uploadedDoc, UploadDocument));
				assertText(uploadedDoc, UploadDocument);

				// Verify if the upload status is updated
				By uploadedDocStatus = By.xpath("//tr[@class='received-doc-row'][" + idx + "]/td[2]");
				assertText(uploadedDocStatus, "Uploaded");

				// Remove Document
				By removeDoc = By.xpath("//tr[@class='received-doc-row']["	+ idx + "]/td[3]/div/a[@name='removeLink']");
				assertText(removeDoc, "Remove");
				click(removeDoc, UploadDocument);
				// JSClick(removeDoc, UploadDocument);
				Thread.sleep(3000);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean validateSubmitApplication(String IsNegativeTest)
			throws Throwable {
		try {
			switch (IsNegativeTest) {
			case "N": {
				// Wait for the availability of Pipeline table
				waitForVisibilityOfElement(MIGNG_HomePage.MIGNG_pipeline, "Pipeline page");

				// verify success text
				String successText = getText(MIGNG_NewApplication.MIGNG_successText, "Success Message");
				if (!successText.equals("")
						&& successText.contains(MIGNG_Validation.msgSuccessApplicationSubmitted)) {
					SuccessReport("Create New Application",	"Success Message : " + successText);
					String applicationNo = successText.replaceAll("[^0-9]", "");
					SuccessReport("Create New Application", "Application No : "	+ applicationNo);
				} else {
					failureReport("Create New Application", "Step Failed");
				}
				break;
			}
			case "Y": {
				String errorText = getText(MIGNG_NewApplication.MIGNG_errorText, "Error Message");
				if (!errorText.equals("")
						&& errorText.contains(MIGNG_Validation.msgErrorNoDocumentUpload)) {
					SuccessReport("Negative Test", "Error Message : " + errorText);
				} else {
					failureReport("Negative Test", "Step Failed");
				}
				break;
			}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean openExistingApplication() throws Throwable {
		try {
			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineData, "Pipeline")) {
				SuccessReport("Verfiy Pipeline", "Pipeline data is displayed");
			} else {
				failureReport("Verfiy Pipeline", "Pipeline data is not displayed");
			}

			int rowCount = (driver.findElements(MIGNG_HomePage.MIGNG_pipelineData)).size();
			for (int i = 1; i <= rowCount; i++) {
				String appType = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[4]"))).getText();
				if (appType.equals("Application")) {
					WebElement appId = driver.findElement(By
							.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[3]"));
					appId.click();
					break;
				}
			}

			// Wait for the availability of Borrower First Name field
			waitForVisibilityOfElement(MIGNG_NewApplication.MIGNG_borrowerFirstName, "Borrower First Name");

			// Validate 'The user verifies that a text "Max file size: 100MB"
			// (uneditable) is displayed on the document upload section.'
			String msgDocUploadLimit = getText(MIGNG_NewApplication.MIGNG_docUploadValidation, "");
			if (!msgDocUploadLimit.equals("")
					&& msgDocUploadLimit.contains(MIGNG_Validation.msgDocumentUpload)) {
				SuccessReport("Document Upload Limit", "Success Message : "	+ msgDocUploadLimit);
			} else {
				failureReport("Document Upload Limit", "Message could not be extracted");
			}
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean openExistingApplication(String applicationNo)
			throws Throwable {
		try {
			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineData, "Pipeline")) {
				SuccessReport("Verfiy Pipeline", "Pipeline data is displayed");
			} else {
				failureReport("Verfiy Pipeline", "Pipeline data is not displayed");
			}

			int rowCount = driver.findElements(MIGNG_HomePage.MIGNG_pipelineData).size();
			for (int i = 1; i <= rowCount; i++) {
				String appNo = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[3]"))).getText().trim();
				String appType = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[4]"))).getText().trim();
				if (appType.equalsIgnoreCase("Application") && appNo.contains(applicationNo)) {
					WebElement appId = driver.findElement(By
							.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[3]"));
					appId.click();
					break;
				}else{
					System.out.println("appNo: " + appNo + " ; appType: " + appType);
				}
			}

			// Wait for the availability of Borrower First Name field
			waitForVisibilityOfElement(MIGNG_NewApplication.MIGNG_borrowerFirstName, "Borrower First Name");

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean openExistingQuote(String quoteNo) throws Throwable {
		try {
			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineData, "Pipeline")) {
				SuccessReport("Verfiy Pipeline", "Pipeline data is displayed");
			} else {
				failureReport("Verfiy Pipeline", "Pipeline data is not displayed");
			}
			
			type(MIGNG_HomePage.MIGNG_searchField, quoteNo, "Enter Quote Number");
			click(MIGNG_HomePage.MIGNG_searchButton, "Search Quote");
			Thread.sleep(3000);

			int rowCount = (driver.findElements(MIGNG_HomePage.MIGNG_pipelineData)).size();
			for (int i = 1; i <= rowCount; i++) {
				String appNo = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i + "]/td[3]"))).getText().trim();
				String appType = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i + "]/td[4]"))).getText().trim();
				if (appType.equals("Quote") && appNo.contains(quoteNo)) {
					WebElement appId = driver.findElement(By
							.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[3]"));
					appId.click();
					break;
				}
			}

			// Wait for the availability of Borrower First Name field
			waitForVisibilityOfElement(MIGNG_NewApplication.MIGNG_borrowerLastName, "Borrower Last Name");

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean openExistingQuote() throws Throwable {
		try {
			if (isElementPresent(MIGNG_HomePage.MIGNG_pipelineData, "Pipeline")) {
				SuccessReport("Verfiy Pipeline", "Pipeline data is displayed");
			} else {
				failureReport("Verfiy Pipeline", "Pipeline data is not displayed");
			}

			int rowCount = (driver.findElements(MIGNG_HomePage.MIGNG_pipelineData)).size();
			for (int i = 1; i <= rowCount; i++) {
				String appType = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[4]"))).getText();
				String appStatus = (driver.findElement(By
						.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[5]"))).getText();
				if (appType.equals("Quote") && appStatus.contains("Successful")) {
					WebElement appId = driver.findElement(By
							.xpath("//table[@id='entities']/tbody/tr[" + i	+ "]/td[3]"));
					appId.click();
					break;
				}
			}

			// Wait for the availability of Borrower First Name field
			waitForVisibilityOfElement(MIGNG_NewApplication.MIGNG_borrowerLastName, "Borrower Last Name");

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	
	public boolean createNewApplicationWithMultipleDocs(
			String SecureQuoteNumber, String BorrowerFirstName,
			String BorrowerLastName, String AffordableHousing,
			String CorporateRelocation, String OriginationType,
			String MICoverage, String miPaymentType, String MIPaymentOption,
			String FinancedPremium, String UpfrontPremium, String PostPay,
			String ThisSubmission, String SpecialHandlingInstructions,
			String Email, String Phone, String Fax, String UploadDocument,
			String IsNegativeTest) throws Throwable {
		try {
			// Thread.sleep(3000);
			click(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-file Application");
			Thread.sleep(2000);
			assertText(MIGNG_HomePage.MIGNG_applicationHeader,	"New Full-file Application");

			fillNewApplicationForm(SecureQuoteNumber, BorrowerFirstName,
					BorrowerLastName, AffordableHousing, CorporateRelocation,
					OriginationType, MICoverage, miPaymentType,
					MIPaymentOption, FinancedPremium, UpfrontPremium, PostPay,
					ThisSubmission, SpecialHandlingInstructions, Email, Phone, Fax);

			if (!UploadDocument.equals("")) {
				String[] files = UploadDocument.split(";");
				// String[] test = IsNegativeTest.split(";");
				for (int i = 0; i < files.length; i++) {
					uploadDocument(files[i], i + 1);
				}
				// Click on Submit button
				click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");
				// Validate message after submitting application
				validateSubmitApplication(IsNegativeTest);
			} else {
				// Click on Submit button
				click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");
				// Validate message after submitting application
				validateSubmitApplication(IsNegativeTest);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean fillNewApplicationForm(String SecureQuoteNumber,
			String BorrowerFirstName, String BorrowerLastName,
			String AffordableHousing, String CorporateRelocation,
			String OriginationType, String MICoverage, String miPaymentType,
			String MIPaymentOption, String FinancedPremium,
			String UpfrontPremium, String PostPay, String ThisSubmission,
			String SpecialHandlingInstructions, String Email, String Phone,
			String Fax) throws Throwable {
		try {
			// Enter data for Secure Quote Number
			type(MIGNG_NewApplication.MIGNG_secureQuoteNumber,
					SecureQuoteNumber, "Secure Quote Number");

			// Enter data for Borrower First Name
			type(MIGNG_NewApplication.MIGNG_borrowerFirstName,
					BorrowerFirstName, "Borrower First Name");

			// Enter data for Borrower Last Name
			type(MIGNG_NewApplication.MIGNG_borrowerLastName, BorrowerLastName,
					"Borrower Last Name");

			Thread.sleep(3000);
			switch (AffordableHousing) {
			case "YES":
				click(MIGNG_NewApplication.MIGNG_affordableHousingYes,
						"Affordable Housing : Yes");
				break;
			case "NO":
				click(MIGNG_NewApplication.MIGNG_affordableHousingNo,
						"Affordable Housing : No");
				break;
			}

			switch (CorporateRelocation) {
			case "YES":
				click(MIGNG_NewApplication.MIGNG_corporateRelocationYes,
						"Corporate Relocation Yes");
				break;
			case "NO":
				click(MIGNG_NewApplication.MIGNG_corporateRelocationNo,
						"Corporate Relocation No");
				break;
			}

			// Select Origination Type
			switch (OriginationType) {
			case "Retail":
				selectByValue(MIGNG_NewApplication.MIGNG_originationType,
						"NONE", "Origination Type");
				break;
			case "TPO - Broker":
				selectByValue(MIGNG_NewApplication.MIGNG_originationType, "0",
						"Origination Type");
				break;
			case "TPO - Community Bank":
				selectByValue(MIGNG_NewApplication.MIGNG_originationType, "2",
						"Origination Type");
				break;
			case "TPO - Correspondent":
				selectByValue(MIGNG_NewApplication.MIGNG_originationType, "1",
						"Origination Type");
				break;
			case "TPO - Credit Union":
				selectByValue(MIGNG_NewApplication.MIGNG_originationType, "3",
						"Origination Type");
				break;
			}

			// Enter value for MI Coverage
			type(MIGNG_NewApplication.MIGNG_miCoverage, MICoverage,
					"MI Coverage");

			switch (miPaymentType) {
			case "BP":
				click(MIGNG_NewApplication.MIGNG_miPaymentTypeBP,
						"Payment Type : Borrower-Paid");
				break;
			case "LP":
				click(MIGNG_NewApplication.MIGNG_miPaymentTypeLP,
						"Payment Type : Lender-Paid");
				break;
			}

			// Select Payment Option
			switch (MIPaymentOption) {
			case "LPS":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"LPS", "Payment Option");
				break;
			case "LPM":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"LPM", "Payment Option");
				break;
			case "LPA":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"LPA", "Payment Option");
				break;
			// case "Annual":
			case "BPA":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"BPA", "Payment Option");
				break;
			// case "Monthly":
			case "BPM":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"BPM", "Payment Option");
				break;
			case "Single Refundable":
			case "BPSL":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"BPSL", "Payment Option");
				break;
			case "Single Non-Refundable":
			case "BPSN":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"BPSN", "Payment Option");
				break;
			case "Split Premium Monthly":
			case "BPSM":
				selectByValue(MIGNG_NewApplication.MIGNG_miPaymentOption,
						"BPSM", "Payment Option");
				break;
			}

			// Select value for Financed Premium
			if (!(FinancedPremium.equals(""))) {
				switch (FinancedPremium) {
				case "YES":
					click(MIGNG_NewApplication.MIGNG_financedPremiumYes,
							"Financed Premium");
					break;
				case "NO":
					click(MIGNG_NewApplication.MIGNG_financedPremiumNo,
							"Financed Premium");
					break;
				}
			}

			// Select value for Upfront Premium
			if (!(UpfrontPremium.equals(""))) {
				selectByIndex(MIGNG_NewApplication.MIGNG_upfrontPremium,
						Integer.parseInt(UpfrontPremium), "Upfront Premium");
			}

			// Select value for Post Pay
			if (!(PostPay.equals(""))) {
				switch (PostPay) {
				case "YES":
					click(MIGNG_NewApplication.MIGNG_postPayYes,
							"PostPay : Yes");
					break;
				case "NO":
					click(MIGNG_NewApplication.MIGNG_postPayNo, "PostPay : NO");
					break;
				}
			}

			// Select value for 'This submission is for'
			if (!(ThisSubmission.equals(""))) {
				switch (ThisSubmission) {
				case "MI":
					click(MIGNG_NewApplication.MIGNG_thisSubmissionIsForMI,
							"MI");
					break;
				case "MIC":
					click(MIGNG_NewApplication.MIGNG_thisSubmissionIsForMIC,
							"MI and Compliance ");
					break;
				}
			}

			// Enter Information for Special Handling Instructions
			type(MIGNG_NewApplication.MIGNG_specialHandlingInstructions,
					SpecialHandlingInstructions,
					"Special Handling Instructions");
			// Enter value for Email field
			type(MIGNG_NewApplication.MIGNG_eMail, Email, "Email");
			// Enter value for Phone field
			type(MIGNG_NewApplication.MIGNG_phone, Phone, "Phone");
			// Enter value for Fax field
			type(MIGNG_NewApplication.MIGNG_fax, Fax, "Fax");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean MIGNG_Rapid_NewApplication(String First, String Last,
			String SSN, String DOB, String NoYears, String Mths,
			String StreetAddress, String CITY, String state, String zip,
			String Monthly_Income, String Gender, String Ethnicity,
			String OtherMonthlyPayments, String Race, String CreditScore,
			String CreditRepository, String CreditScoreDate,
			String CreditKeyFactor, String PStreetAddress, String PCity,
			String PState, String PZIP, String PCounty, String OccupancyStatus,
			String PropertyType, String AppraisedValue, String SalesPrice,
			String LenderLoanNumber, String LoanPurposeType,
			String AmortizationType, String InitialNoteRate, String QualifyingRate, String OriginalLoanAmount,
			String AmortizationTerm, String InterestOnlyPeriod, String BalloonTerm, String Subordinate,
			String EstimatedCashtoClose, String LiquidAssets, String Hazardins,
			String Coverage, int refund) throws Throwable {
		boolean flag = true;
		try {
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_First, First, "FirstName ");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Last, Last, "LastName ");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_SSN, SSN, "SSN");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Dob, DOB, "DOB");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_No_Yrs, NoYears, "NoYears");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Mths, Mths, "Months");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_StreetAddress, StreetAddress,	"StreetAddress");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_City, CITY, "CITY");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_State)).selectByVisibleText(state);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Zip, zip, "zip");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Monthly_Income,Monthly_Income, "Monthly_Income");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_OtherMonthlyPayments,	OtherMonthlyPayments, "OtherMonthlyPayments");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_OccupayProp, "OccupayProp");
			
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Gender)).selectByVisibleText(Gender);
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Ethnicity)).selectByValue(Ethnicity);
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Race)).selectByValue(Race);
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditRepository)).selectByVisibleText(CreditRepository);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_bankruptcy, "bankruptcy");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditScore, CreditScore,	"CreditScore");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditScoreDate, CreditScoreDate, "CreditScoreDate");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditKeyFactor)).selectByVisibleText(CreditKeyFactor);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PStreetAddress, PStreetAddress, "PStreetAddress");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PCity, PCity, "City");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PState)).selectByVisibleText(PState);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PZIP, PZIP, "PZIP");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PCounty, PCounty, "County");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PropertyType)).selectByVisibleText(PropertyType);
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_POccupancyStatus)).selectByVisibleText(OccupancyStatus);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_AppraisedValue,AppraisedValue, "AppraisedValue");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_SalesPrice, SalesPrice,"SalesPrice");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_LenderLoanNumber,LenderLoanNumber, "LenderLoanNumber");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_AmortizationType)).selectByVisibleText(AmortizationType);
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_LoanPurposeType)).selectByVisibleText(LoanPurposeType);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_InitialNoteRate,InitialNoteRate, "InitialNoteRate");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_QualifyingRate,QualifyingRate, "QualifyingRate");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_OriginalLoanAmount,OriginalLoanAmount, "OriginalLoanAmount");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_AmortizationTerm,	AmortizationTerm, "AmortizationTerm");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_InterestOnlyPeriod,InterestOnlyPeriod, "InterestOnlyPeriod");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_BalloonTerm, BalloonTerm,	"BalloonTerm");

			if (Subordinate.equalsIgnoreCase("None")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateNone,"Subordinate None");
			} else if (Subordinate.equalsIgnoreCase("New")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateNew,"Subordinate New");
			} else if (Subordinate.equalsIgnoreCase("None")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateExisting,"Subordinate Existing");
			}
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_EstimatedCashtoClose,	EstimatedCashtoClose, "EstimatedCashtoClose");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_LiquidAssets, LiquidAssets,"LiquidAssets");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Hazardins, Hazardins,	"Hazardins");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Coverage, Coverage, "Coverage");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_prefundd)).selectByIndex(refund);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_LPResults, "LPResults");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_LPResults, "LPResults");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Continue, "Continue");
			Thread.sleep(3000);
			// driver.switchTo().frame("MainFrame");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_agree, "Agree");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_agree, "Agree");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_submit, "Submit");
			Thread.sleep(3000);
			driver.navigate().back();
//			driver.navigate().back();
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}


	public boolean RAPidLinkPreference() throws Throwable {
		boolean flag = true;
		try {
			String msgSuccess = "You have successfully saved your preferences. Please click on the MI Guide Menu to return to the MI Guide home page.";
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication, "NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_verifyPreference, "User Preferences");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_relocationLoan, "Realocate");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_affordable, "Affortable");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_nontraCredit, "Credit");

			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_amortizationType)).selectByIndex(1);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_premiumMonthly, "MonthlyPremimus");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_extendedLevelBorrowerPaidMonthly, "Extended");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_save, "Save");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_successSaveVerify, msgSuccess);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_premiumAnnual, "Annual Premimus");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_lenderPaid, "Lender");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_save, "Save");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_successSaveVerify, msgSuccess);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			
				Thread.sleep(2000);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean MIGNG_Preferen()throws Throwable{
		try{
			Thread.sleep(1000);
			if (isElementPresent(MIGNG_RAPidLink.MIGNG_RAPidLink_Myaccount, "Myaccount")) {
				SuccessReport("Verify My Account is present", "My Account is present is present");
			} else {
				failureReport("Verify My Account is present", "My Account is present is not present");
			}
			WebElement element=driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("window.scrollBy(0,250)", element);
		    Thread.sleep(1000);
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink, "RAPid Link (Order MI)");
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DnewLoan, "New Loan");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_verifyPreference, "User Preferences");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_relocationLoan, "Realocate");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_affordable, "Affortable");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_nontraCredit, "Credit");

			String msgSuccess = "You have successfully saved your preferences. Please click on the MI Guide Menu to return to the MI Guide home page.";

			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_amortizationType)).selectByIndex(1);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_premiumMonthly, "MonthlyPremimus");
//			type(MIGNG_RAPidLink.MIGNG_RAPidLink_pCoverage, "30", "Coverage");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_pPostPay, "PostPay");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_extendedLevelBorrowerPaidMonthly, "Extended Level Borrower-Paid Monthly");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_save, "Save");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_successSaveVerify, msgSuccess);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			

			click(MIGNG_RAPidLink.MIGNG_RAPidLink_premiumSingle, "SinglePremimus");
//			type(MIGNG_RAPidLink.MIGNG_RAPidLink_pCoverage, "20", "Coverage");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_pPostPay, "PostPay");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_pSplit, "Splite");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_borrowerPaidSingle, "Borrower-Paid Singles");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_save, "Save");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_successSaveVerify, msgSuccess);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean MIGNG_PreferenllNavigate()throws Throwable{
		try{
			Thread.sleep(1000);
			String winHandleBefore = driver.getWindowHandle();
			if (isElementPresent(MIGNG_RAPidLink.MIGNG_RAPidLink_Myaccount, "Myaccount")) {
				SuccessReport("Verify My Account is present", "My Account is present is present");
			} else {
				failureReport("Verify My Account is present", "My Account is present is not present");
			}
			
			WebElement element=driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("window.scrollBy(0,250)", element);
		    Thread.sleep(1000);
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink, "RAPid Link (Order MI)");
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DnewLoan, "New Loan");
			driver.switchTo().frame("MainFrame");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_verifyPreference, "User Preferences");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_VisitUGC,"Visit UGC");
			driver.switchTo().window(winHandleBefore);
			driver.close();
			driver.switchTo().frame("MainFrame");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean MIGNG_Preferenll()throws Throwable{
		try{
			Thread.sleep(1000);
			if (isElementPresent(MIGNG_RAPidLink.MIGNG_RAPidLink_Myaccount, "Myaccount")) {
				SuccessReport("Verify My Account is present", "My Account is present is present");
			} else {
				failureReport("Verify My Account is present", "My Account is present is not present");
			}
			
			WebElement element=driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("window.scrollBy(0,250)", element);
		    Thread.sleep(1000);
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink, "RAPid Link (Order MI)");
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DnewLoan, "New Loan");
			driver.switchTo().frame("MainFrame");
			Thread.sleep(5000);
			
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_verifyPreference, "User Preferences");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_relocationLoan, "Realocate");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_affordable, "Affortable");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_nontraCredit, "Credit");

			String msgSuccess = "You have successfully saved your preferences. Please click on the MI Guide Menu to return to the MI Guide home page.";
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_amortizationType)).selectByIndex(1);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_premiumMonthly, "MonthlyPremimus");
//			type(MIGNG_RAPidLink.MIGNG_RAPidLink_pCoverage, "30", "Coverage");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_pPostPay, "PostPay");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_extendedLevelBorrowerPaidMonthly, "Extended");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_save, "Save");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_successSaveVerify, msgSuccess);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_premiumSingle, "SinglePremimus");
//			type(MIGNG_RAPidLink.MIGNG_RAPidLink_pCoverage, "20", "Coverage");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_pPostPay, "PostPay");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_pSplit, "Splite");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_borrowerPaidSingle, "Borrower-Paid Single");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_save, "Save");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_successSaveVerify, msgSuccess);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean MIGNG_Rapid_NewApplicationupload(
			String First, String Last, String SSN, String DOB, String NoYears, String Mths,
			String StreetAddress, String CITY, String state, String zip,
			String Monthly_Income, String Gender, String Ethnicity,
			String OtherMonthlyPayments, String Race, String CreditScore,
			String CreditRepository, String CreditScoreDate,
			String CreditKeyFactor, String PStreetAddress, String PCity,
			String PState, String PZIP, String PCounty, String OccupancyStatus,
			String PropertyType, String AppraisedValue, String SalesPrice,
			String LenderLoanNumber, String LoanPurposeType,
			String AmortizationType, String InitialNoteRate, String QualifyingRate, String OriginalLoanAmount,
			String AmortizationTerm, String InterestOnlyPeriod, String BalloonTerm, String Subordinate,
			String EstimatedCashtoClose, String LiquidAssets, String Hazardins,
			String Coverage) throws Throwable {
		boolean flag = true;
		try {
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_First, First, "FirstName ");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Last, Last, "LastName ");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_SSN, SSN, "SSN");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Dob, DOB, "DOB");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_No_Yrs, NoYears, "NoYears");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Mths, Mths, "Months");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_StreetAddress, StreetAddress,	"StreetAddress");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_City, CITY, "CITY");
			WebElement stateelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_State);
			Select statese = new Select(stateelement);
			statese.selectByVisibleText(state);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Zip, zip, "zip");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Monthly_Income,Monthly_Income, "Monthly_Income");
			WebElement genders = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Gender);
			Select gense = new Select(genders);
			gense.selectByVisibleText(Gender);

			WebElement Ethnicityele = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Ethnicity);
			Select Ethnicityse = new Select(Ethnicityele);
			Ethnicityse.selectByValue(Ethnicity);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_OtherMonthlyPayments,	OtherMonthlyPayments, "OtherMonthlyPayments");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_bankruptcy, "bankruptcy");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditScore, CreditScore,	"CreditScore");

			Select creditsel = new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditRepository));
			creditsel.selectByVisibleText(CreditRepository);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditScoreDate, CreditScoreDate, "CreditScoreDate");

			Select CreditKeysel = new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditKeyFactor));
			CreditKeysel.selectByVisibleText(CreditKeyFactor);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_nontradcredit, "nontradcredit");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PStreetAddress, PStreetAddress, "PStreetAddress");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PCity, PCity, "City");

			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PState)).selectByVisibleText(PState);
			
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PZIP, PZIP, "PZIP");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PCounty, PCounty, "County");

			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PropertyType)).selectByVisibleText(PropertyType);

			/*WebElement occuelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_POccupancyStatus);
			Select occuse = new Select(occuelement);
			occuse.selectByIndex(2);;*/

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_AppraisedValue, AppraisedValue, "AppraisedValue");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_SalesPrice, SalesPrice,"SalesPrice");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_LenderLoanNumber,	LenderLoanNumber, "LenderLoanNumber");

			WebElement amorelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_AmortizationType);
			Select amorse = new Select(amorelement);
			amorse.selectByVisibleText(AmortizationType);

			WebElement loanelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_LoanPurposeType);
			Select loanse = new Select(loanelement);
			loanse.selectByVisibleText(LoanPurposeType);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_InitialNoteRate,	InitialNoteRate, "InitialNoteRate");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_QualifyingRate,QualifyingRate, "QualifyingRate");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_OriginalLoanAmount,OriginalLoanAmount, "OriginalLoanAmount");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_AmortizationTerm,	AmortizationTerm, "AmortizationTerm");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_InterestOnlyPeriod, InterestOnlyPeriod, "InterestOnlyPeriod");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_BalloonTerm, BalloonTerm,	"BalloonTerm");
			if (Subordinate.equalsIgnoreCase("None")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateNone,"Subordinate None");
			} else if (Subordinate.equalsIgnoreCase("New")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateNew,"Subordinate New");
			} else if (Subordinate.equalsIgnoreCase("None")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateExisting,"Subordinate Existing");
			}
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_EstimatedCashtoClose,	EstimatedCashtoClose, "EstimatedCashtoClose");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_LiquidAssets, LiquidAssets,"LiquidAssets");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Hazardins, Hazardins,	"Hazardins");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Coverage, Coverage, "Coverage");
			WebElement refund= driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_prefundd);
			Select refundse = new Select(refund);
			refundse.selectByIndex(1);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_LPResults, "LPResults");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_LPResults, "LPResults");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Continue, "Continue");
			Thread.sleep(3000);
//			driver.switchTo().frame("MainFrame");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_agree, "Agree");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_agree, "Agree");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_submit, "Submit");
			Thread.sleep(3000);
			if(browser.equalsIgnoreCase("firefox")){
				waitForVisibilityOfElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PdfVerify1,"Verify Pdf");
				Thread.sleep(3000);
				waitForVisibilityOfElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PdfVerify2,"Verify Pdf");
				Thread.sleep(3000);
			}
//			driver.navigate().back();
			driver.navigate().back();
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean MIGNG_PaymentQuote(String quoteNumber) throws Throwable {
		try {

			Thread.sleep(1000);
			if (isElementPresent(MIGNG_RAPidLink.MIGNG_RAPidLink_Myaccount, "Myaccount")) {
				SuccessReport("Verify My Account is present", "My Account is present is present");
			} else {
				failureReport("Verify My Account is present", "My Account is present is not present");
			}

			WebElement element = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,250)", element);
			Thread.sleep(1000);
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink, "RAPid Link (Order MI)");
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_RRRunner, "Rate Runner - MI Compare");
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_RRgetQuote, "Get a quote");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RateRunnerVerify, "RateRunnerVerify");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_RRExistingQuoteNum,quoteNumber,"quoteNumber");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_RRFindMyQuote,"Find Quote");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RRPaymentComparisonVerify, "RateRunnerVerify");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_RRmodify,"Modify Quote");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean MIGNG_Rapid_NewApplicationupload1(
			String First, String Last, String SSN, String DOB, String NoYears, String Mths,
			String StreetAddress, String CITY, String state, String zip,
			String Monthly_Income, String Gender, String Ethnicity,
			String OtherMonthlyPayments, String Race, String CreditScore,
			String CreditRepository, String CreditScoreDate,
			String CreditKeyFactor, String PStreetAddress, String PCity,
			String PState, String PZIP, String PCounty, String OccupancyStatus,
			String PropertyType, String AppraisedValue, String SalesPrice,
			String LenderLoanNumber, String LoanPurposeType, String AmortizationType, String InitialNoteRate,
			String QualifyingRate, String OriginalLoanAmount, String AmortizationTerm, String InterestOnlyPeriod,
			String BalloonTerm, String Subordinate,
			String EstimatedCashtoClose, String LiquidAssets, String Hazardins,
			String Coverage) throws Throwable {
		boolean flag = true;
		try {
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_First, First, "FirstName ");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Last, Last, "LastName ");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_SSN, SSN, "SSN");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Dob, DOB, "DOB");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_No_Yrs, NoYears, "NoYears");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Mths, Mths, "Months");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_StreetAddress, StreetAddress, "StreetAddress");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_City, CITY, "CITY");
			WebElement stateelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_State);
			Select statese = new Select(stateelement);
			statese.selectByVisibleText(state);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Zip, zip, "zip");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Monthly_Income, Monthly_Income, "Monthly_Income");
			WebElement genders = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Gender);
			Select gense = new Select(genders);
			gense.selectByVisibleText(Gender);

			WebElement Ethnicityele = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Ethnicity);
			Select Ethnicityse = new Select(Ethnicityele);
			Ethnicityse.selectByValue(Ethnicity);
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_Race)).selectByValue(Race);
			
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_OccupayProp, "OCcupency prop");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_OtherMonthlyPayments,
					OtherMonthlyPayments, "OtherMonthlyPayments");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_bankruptcy, "bankruptcy");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditScore, CreditScore,	"CreditScore");

			Select creditsel = new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditRepository));
			creditsel.selectByVisibleText(CreditRepository);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditScoreDate, CreditScoreDate, "CreditScoreDate");

			Select CreditKeysel = new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_CreditKeyFactor));
			CreditKeysel.selectByVisibleText(CreditKeyFactor);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_nontradcredit, "nontradcredit");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PStreetAddress, PStreetAddress, "PStreetAddress");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PCity, PCity, "City");

			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PState)).selectByVisibleText(PState);
			
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PZIP, PZIP, "PZIP");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_PCounty, PCounty, "County");

			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PropertyType)).selectByVisibleText(PropertyType);

			WebElement occuelement = driver
					.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_POccupancyStatus);
			Select occuse = new Select(occuelement);
			occuse.selectByIndex(2);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_AppraisedValue,
					AppraisedValue, "AppraisedValue");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_SalesPrice, SalesPrice,
					"SalesPrice");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_LenderLoanNumber,
					LenderLoanNumber, "LenderLoanNumber");

			WebElement amorelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_AmortizationType);
			Select amorse = new Select(amorelement);
			amorse.selectByVisibleText(AmortizationType);

			WebElement loanelement = driver
					.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_LoanPurposeType);
			Select loanse = new Select(loanelement);
			loanse.selectByVisibleText(LoanPurposeType);

			type(MIGNG_RAPidLink.MIGNG_RAPidLink_InitialNoteRate,
					InitialNoteRate, "InitialNoteRate");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_QualifyingRate,
					QualifyingRate, "QualifyingRate");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_OriginalLoanAmount,
					OriginalLoanAmount, "OriginalLoanAmount");
			Thread.sleep(1000);
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_AmortizationTerm,
					AmortizationTerm, "AmortizationTerm");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_InterestOnlyPeriod,
					InterestOnlyPeriod, "InterestOnlyPeriod");
			if (Subordinate.equalsIgnoreCase("None")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateNone,"Subordinate None");
			} else if (Subordinate.equalsIgnoreCase("New")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateNew,"Subordinate New");
			} else if (Subordinate.equalsIgnoreCase("None")) {
				click(MIGNG_RAPidLink.MIGNG_RAPidLink_SubordinateExisting,"Subordinate Existing");
			}
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_EstimatedCashtoClose,
					EstimatedCashtoClose, "EstimatedCashtoClose");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_LiquidAssets, LiquidAssets,
					"LiquidAssets");
			Thread.sleep(1000);
			
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Hazardins, Hazardins,
					"Hazardins");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Affordable, "Affordable");
			//click(MIGNG_RAPidLink.MIGNG_RAPidLink_MonthlyPrem,"Monthly Premium");
			type(MIGNG_RAPidLink.MIGNG_RAPidLink_Coverage, Coverage, "Coverage");
//			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PostPay,"Monthly Premium");
			WebElement refund= driver
					.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_prefundd);
			Select refundse = new Select(refund);
			refundse.selectByIndex(1);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_LPResults, "LPResults");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_LPResults, "LPResults");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Continue, "Continue");
			Thread.sleep(3000);
//			driver.switchTo().frame("MainFrame");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_agree, "Agree");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_agree, "Agree");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_submit, "Submit");
			Thread.sleep(3000);
			/*waitForVisibilityOfElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PdfVerify1,"Verify Pdf");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PdfVerify1,"Verify Pdf");
			Thread.sleep(3000);
			waitForVisibilityOfElement(MIGNG_RAPidLink.MIGNG_RAPidLink_PdfVerify2,"Verify Pdf");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PdfVerify2,"Verify Pdf");
			driver.navigate().back();*/
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_errorVerify, "Error Verify");
			driver.navigate().back();
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean Rapid_Delegated() throws Throwable {
		try {
			Thread.sleep(1000);
			String winHandleBefore = driver.getWindowHandle();
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Resubmit, "Resubmit");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Select Loan");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Upload, "Upload");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			Thread.sleep(1000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			Thread.sleep(1000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_VisitUGC, "VisitUGC");
			
			driver.switchTo().window(winHandleBefore);
			driver.switchTo().frame("MainFrame");
			Thread.sleep(3000);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean Rapid_Delegated_ALL() throws Throwable {
		try {
			Thread.sleep(1000);
			String winHandleBefore = driver.getWindowHandle();
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			Thread.sleep(1000);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PipeResubmit, "Resubmit");
			Thread.sleep(1000);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Select Loan");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PipeUpload, "Upload");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Upload Loan");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Pipeline, "Pipeline");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			Thread.sleep(1000);
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_CertFinder, "CertFinder");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_CertFinderVerify, "UG Certificate Number:");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_MiGuideHome, "MiGuideHome");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_PipelineVerify, "Pipeline");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Preferences");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "User Preferences");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_VisitUGC, "VisitUGC");
			driver.switchTo().window(winHandleBefore);
			driver.switchTo().frame("MainFrame");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean MIGNG_toDelegatedUpload(Robot robot,StringSelection stringSelection) throws Throwable {
		try {
			Thread.sleep(1000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PipeUpload, "Upload");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Upload Loan");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			new Select(driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_TypeOfFile)).selectByIndex(0);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Browse, "Browse");
			robot.delay(3000);
			    stringSelection = new StringSelection(new File("Files/" + "NewVandalay.FNM").getAbsolutePath());
//				stringSelection = new StringSelection("S:\\Enterprise Testing\\Automation Testing\\Cigniti POC\\workspace\\UGCSelenium\\Files\\NewVandalay.FNM");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			robot.delay(3000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_upcontinue, "Continue");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean MIGNG_toDelegatedUpload1(Robot robot,StringSelection stringSelection) throws Throwable {
		try {
			Thread.sleep(1000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_PipeUpload, "Upload");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Upload Verified");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			WebElement loanelement = driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_TypeOfFile);
			Select TypeOfFilese = new Select(loanelement);
			TypeOfFilese.selectByIndex(1);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Browse, "Browse");
			robot.delay(3000);
			    stringSelection = new StringSelection(new File("Files/" + "lptest1.dat").getAbsolutePath());
//				stringSelection = new StringSelection("S:\\Enterprise Testing\\Automation Testing\\Cigniti POC\\workspace\\UGCSelenium\\Files\\lptest1.dat");
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
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_upcontinue, "Continue");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean MIGNG_Rapid_SignOut() throws Throwable {
		try {
			Thread.sleep(1000);
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Delegated, "Delegated");
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_NewApplication,"NewApplication");
			driver.switchTo().frame("MainFrame");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RapidVerify, "RAPidLink");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void verifyRapidLinkMyAccount() throws Throwable{
		try{
			if (isElementPresent(MIGNG_RAPidLink.MIGNG_RAPidLink_Myaccount, "Myaccount")) {
				SuccessReport("Verify My Account is present", "My Account is present");
			} else {
				failureReport("Verify My Account is present", "My Account is not present");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean verifyRlMyAccount() throws Throwable {
		try {
			Thread.sleep(1000);
			verifyRapidLinkMyAccount();
			
			WebElement element=driver.findElement(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		    jse.executeScript("window.scrollBy(0,250)", element);
		    Thread.sleep(1000);
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DRapidlink, "RAPid Link (Order MI)");
			JSClick(MIGNG_RAPidLink.MIGNG_RAPidLink_DnewLoan, "New Loan");
			driver.switchTo().frame("MainFrame");
			verifyRapidLinkMyAccount();
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Resubmit, "Resubmit Existing Loan");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Verifyall, "Resubmit verified");
			verifyRapidLinkMyAccount();
			
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Upload, "Upload Loan Data");
			verifyRapidLinkMyAccount();

			click(MIGNG_RAPidLink.MIGNG_RAPidLink_Preferences, "Set My Preferences");
			verifyRapidLinkMyAccount();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verifyRRMyAccount() throws Throwable {
		try {
			Thread.sleep(1000);
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_Myaccount, "MyAccount");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_RRRunner, "Rate Runner - MI Compare");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_GetQUote, "GetQuoite");
			assertText(MIGNG_RAPidLink.MIGNG_RAPidLink_RRgetQuoteVerify, "GetQUoteVerify");
			click(MIGNG_RAPidLink.MIGNG_RAPidLink_MiGuide, "MiHome");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean fillNewQuoteForm(String borrowerLastName,
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
			String financedPremium, String upFrontPremium, String postPay,
			String quoteType) throws Throwable {

		try {
			// Enter data for Borrower Last Name
			type(MIGNG_NewQuote.MIGNG_borrowerLastName, borrowerLastName, "Borrower Last Name");

			// Enter data for Lender Loan Number
			type(MIGNG_NewQuote.MIGNG_lenderLoanNumber, lenderLoanNumber, "Lender Loan Number");

			// Enter data for Base Loan Amount
			type(MIGNG_NewQuote.MIGNG_baseLoanAmount, baseLoanAmount, "Base Loan Amount");

			// Enter data for Amortization Term
			type(MIGNG_NewQuote.MIGNG_amortizationTerm, amortizationTerm, "Amortization Term");

			// Enter data for Amortization Type
			if (!amortizationType.equals("")) {
				switch (amortizationType) {
				case "Fixed":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "1", "Amortization Type");
					break;
				case "1-Year ARM":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "3", "Amortization Type");
					break;
				case "2-Year ARM":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "4", "Amortization Type");
					break;
				case "3-Year ARM":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "5", "Amortization Type");
					break;
				case "5-Year ARM":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "6", "Amortization Type");
					break;
				case "Greater than 5-year ARM":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "7", "Amortization Type");
					break;
				case "Less than 1-Year ARM":
					selectByValue(MIGNG_NewQuote.MIGNG_amortizationType, "2", "Amortization Type");
					break;
				}
			}

			// Enter data for Interest Rate
			type(MIGNG_NewQuote.MIGNG_interestRate, interestRate,	"Interest Rate");

			// Enter data for LTV
			type(MIGNG_NewQuote.MIGNG_ltv, ltv, "LTV");

			// Enter data for CLTV
			type(MIGNG_NewQuote.MIGNG_cltv, cltv, "CLTV");

			// Enter data for Loan Purpose
			if (!loanPurpose.equals("")) {
				switch (loanPurpose) {
				case "Purchase":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "1", "Loan Purpose : Purchase");
					break;
				case "Rate/Term Refinance":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "2", "Loan Purpose : Rate/Term Refinance");
					break;
				case "Cash-Out Refinance":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "3", "Loan Purpose : Cash-Out Refinance");
					break;
				case "Renovation Purchase":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "4", "Loan Purpose : Renovation Purchase");
					break;
				case "Construction to Permanent Purchase":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "5",
							"Loan Purpose : Construction to Permanent Purchase");
					break;
				case "Renovation Rate/Term Refinance":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "6",
							"Loan Purpose : Renovation Rate/Term Refinance");
					break;
				case "Construction to Permanent Rate/Term Refinance":
					selectByValue(MIGNG_NewQuote.MIGNG_loanPurpose, "7",
							"Loan Purpose : Construction to Permanent Rate/Term Refinance");
					break;
				}
			}

			// Enter data for Buy Down
			if (!buyDown.equals("")) {
				switch (buyDown) {
				case "None":
					selectByValue(MIGNG_NewQuote.MIGNG_buyDown, "1", "Buy Down : None");
					break;
				case "1-0":
					selectByValue(MIGNG_NewQuote.MIGNG_buyDown, "1", "Buy Down : 1-0");
					break;
				case "2-1":
					selectByValue(MIGNG_NewQuote.MIGNG_buyDown, "1", "Buy Down : 2-1");
					break;
				case "3-2-1":
					selectByValue(MIGNG_NewQuote.MIGNG_buyDown, "1", "Buy Down : 3-2-1");
					break;
				}
			}

			// select value for Affordable Housing
			if (!affordableHousing.equals("")) {
				switch (affordableHousing) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_affordableHousingYes, "Affordable Housing : Yes");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_affordableHousingNo,	"Affordable Housing : No");
					break;
				case "U":
				case "Unknown":
					click(MIGNG_NewQuote.MIGNG_affordableHousingUnknown, "Affordable Housing : Unknown");
					break;
				}
			}

			// select value for Corporate Relocation
			if (!corporateRelocation.equals("")) {
				switch (corporateRelocation) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_corporateRelocationYes,	"Corporate Relocation Yes");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_corporateRelocationNo, "Corporate Relocation No");
					break;
				}
			}

			// Enter data for AU System
			if (!auSystem.equals("")) {
				switch (auSystem) {
				case "None":
					// selectByValue(MIGNG_NewQuote.MIGNG_auSystem, "NA", "AU System : NA");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_auSystem, "None",	"AU System : None");
					break;
				case "Desktop Underwriter":
					// selectByValue(MIGNG_NewQuote.MIGNG_auSystem, "DU", "AU System : Desktop Underwriter");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_auSystem,	"Desktop Underwriter", "AU System : Desktop Underwriter");
					break;
				case "Loan Prospector":
					// selectByValue(MIGNG_NewQuote.MIGNG_auSystem, "LP", "AU System : Loan Prospector");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_auSystem, "Loan Prospector", "AU System : Loan Prospector");
					break;
				}
			}

			// Enter data for AU Decision
			if (!auDecision.equalsIgnoreCase("")) {
				switch (auDecision) {
				case "Approve/Eligible":
					// selectByValue(MIGNG_NewQuote.MIGNG_duDecision, "AE", "AU Decision : Approve/Eligible");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_duDecision, "Approve/Eligible", "AU Decision : Approve/Eligible");
					break;
				case "Approve/Ineligible":
					// selectByValue(MIGNG_NewQuote.MIGNG_duDecision, "AI", "AU Decision : Approve/Ineligible");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_duDecision, "Approve/Ineligible", "AU Decision : Approve/Ineligible");
					break;
				case "Refer with Caution":
					// selectByValue(MIGNG_NewQuote.MIGNG_duDecision, "RW", "AU Decision : Refer with Caution");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_duDecision, "Refer with Caution",	"AU Decision : Refer with Caution");
					break;
				case "Out of Scope":
					// selectByValue(MIGNG_NewQuote.MIGNG_duDecision, "RO", "AU Decision : Out of Scope");
					selectByVisibleText(MIGNG_NewQuote.MIGNG_duDecision, "Out of Scope", "AU Decision : Out of Scope");
					break;
				}
			}

			// Select Origination Type
			if (!originationType.equalsIgnoreCase("")) {
				switch (originationType) {
				case "U":
				case "Unknown":
					selectByValue(MIGNG_NewQuote.MIGNG_originationType, "U", "Origination Type : Unknown");
					break;
				case "Retail":
					selectByValue(MIGNG_NewQuote.MIGNG_originationType, "NONE", "Origination Type : Retail");
					break;
				case "TPO - Broker":
					selectByValue(MIGNG_NewQuote.MIGNG_originationType, "0", "Origination Type : TPO - Broker");
					break;
				case "TPO - Community Bank":
					selectByValue(MIGNG_NewQuote.MIGNG_originationType, "2", "Origination Type : TPO - Community Bank");
					break;
				case "TPO - Correspondent":
					selectByValue(MIGNG_NewQuote.MIGNG_originationType, "1", "Origination Type : TPO - Correspondent");
					break;
				case "TPO - Credit Union":
					selectByValue(MIGNG_NewQuote.MIGNG_originationType, "3", "Origination Type : TPO - Credit Union");
					break;
				}
			}

			// Enter value for Property Zip Code
			type(MIGNG_NewQuote.MIGNG_propertyZipCode, propertyZipCode, "Property Zip Code");

			// Enter data for Property Type
			if (!propertyType.equalsIgnoreCase("")) {
				switch (propertyType) {
				case "Unknown":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "U", "Property Type : Unknown");
					break;
				case "Single Family Detached":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "1", "Property Type : Single Family Detached");
					break;
				case "Single Family Attached":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "2", "Property Type : Single Family Attached");
					break;
				case "Detached PUD":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "3", "Property Type : Detached PUD");
					break;
				case "Attached PUD":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "4", "Property Type : Attached PUD");
					break;
				case "Condo":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "5", "Property Type : Condo");
					break;
				case "Co-op":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "11", "Property Type : Co-op");
					break;
				case "2 Unit":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "6", "Property Type : 2 Unit");
					break;
				case "3 Unit":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "7", "Property Type : 3 Unit");
					break;
				case "4 Unit":
					selectByValue(MIGNG_NewQuote.MIGNG_propertyType, "8", "Property Type : 4 Unit");
					break;
				}
			}

			// Enter data for Occupancy
			if (!occupancy.equals("")) {
				switch (occupancy) {
				case "Primary":
					selectByValue(MIGNG_NewQuote.MIGNG_occupancy, "1", "Occupancy : Primary");
					break;
				case "Investment":
					selectByValue(MIGNG_NewQuote.MIGNG_occupancy, "2", "Occupancy : Investment");
					break;
				case "Second Home":
					selectByValue(MIGNG_NewQuote.MIGNG_occupancy, "3", "Occupancy : Second Home");
					break;
				}
			}

			// Enter value for Hazard Insurance
			type(MIGNG_NewQuote.MIGNG_hazardInsurance, hazardInsurance, "Hazard Insurance");

			// Enter value for Real Estate Taxes
			type(MIGNG_NewQuote.MIGNG_realEstateTaxes, realEstateTaxes, "Real Estate Taxes");

			// Enter value for HOA Flood Other
			type(MIGNG_NewQuote.MIGNG_hoaFloodOther, hoaFloodOther, "HOA Flood Other");

			// select value for Occupy Subject property
			if (!occupySubjectProperty.equals("")) {
				switch (occupySubjectProperty) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_occupySubjectPropertyYes, "Occupy Subject property Yes");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_occupySubjectPropertyNo, "Occupy Subject property No");
					break;
				}
			}

			// select value for Self Employed
			if (!selfEmployed.equals("")) {
				switch (selfEmployed) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_selfEmployedYes, "Self Employed : Yes");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_selfEmployedNo, "Self Employed : No");
					break;
				case "U":
				case "Unknown":
					click(MIGNG_NewQuote.MIGNG_selfEmployedUnknown, "Self Employed : Unknown");
					break;
				}
			}

			// Enter value for Monthly Income
			type(MIGNG_NewQuote.MIGNG_monthlyIncome, monthlyIncome, "Monthly Income");

			// Enter value for Other Monthly Payments
			type(MIGNG_NewQuote.MIGNG_otherMonthlyPayment, otherMonthlyPayment, "Other Monthly Payments");
			Thread.sleep(2000);

			// Enter value for Credit Score
			type(MIGNG_NewQuote.MIGNG_creditScore, creditScore, "Credit Score");

			// select value for Prior Bankruptcy
			if (!priorBankruptcy.equals("")) {
				switch (priorBankruptcy) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_priorBankruptcyYes, "Prior Bankruptcy : Yes");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_priorBankruptcyNo, "Prior Bankruptcy : No");
					break;
				case "U":
				case "Unknown":
					click(MIGNG_NewQuote.MIGNG_priorBankruptcyUnknown, "Prior Bankruptcy : Unknown");
					break;
				}
			}

			// // Click on Add Co-Borrower
			// click(MIGNG_NewQuote.MIGNG_addBorrower, "Add Co-Borrower");

			// Enter value for MI Coverage
			type(MIGNG_NewQuote.MIGNG_miCoverage, miCoverage, "MI Coverage");

			if (!miPaymentType.equals("")) {
				switch (miPaymentType) {
				case "BP":
					click(MIGNG_NewQuote.MIGNG_miPaymentTypeBP, "Payment Type : Borrower-Paid");
					break;
				case "LP":
					click(MIGNG_NewQuote.MIGNG_miPaymentTypeLP, "Payment Type : Lender-Paid");
					break;
				}
			}

			// Select Payment Option
			if (!miPaymentOption.equals("")) {
				switch (miPaymentOption) {
				case "LPS":
				case "Single":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "LPS", "Payment Option");
					break;
				case "LPM":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "LPM", "Payment Option");
					break;
				case "LPA":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "LPA", "Payment Option");
					break;
				// case "Annual":
				case "BPA":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "BPA", "Payment Option");
					break;
				// case "Monthly":
				case "BPM":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "BPM", "Payment Option");
					break;
				case "Single Refundable":
				case "BPSL":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "BPSL", "Payment Option");
					break;
				case "Single Non-Refundable":
				case "BPSN":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "BPSN", "Payment Option");
					break;
				case "Split Premium Monthly":
				case "BPSM":
					selectByValue(MIGNG_NewQuote.MIGNG_miPaymentOption, "BPSM", "Payment Option");
					break;
				}
			}

			// Select value for Financed Premium
			if (!(financedPremium.equals(""))) {
				switch (financedPremium) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_financedPremiumYes, "Financed Premium");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_financedPremiumNo, "Financed Premium");
					break;
				}
			}

			// Select value for Upfront Premium
			if (!(upFrontPremium.equals(""))) {
				selectByIndex(MIGNG_NewQuote.MIGNG_upfrontPremium,
						Integer.parseInt(upFrontPremium), "Upfront Premium");
			}

			// Select value for Post Pay
			if (!(postPay.equals(""))) {
				switch (postPay) {
				case "YES":
					click(MIGNG_NewQuote.MIGNG_postPayYes, "PostPay : Yes");
					break;
				case "NO":
					click(MIGNG_NewQuote.MIGNG_postPayNo, "PostPay : NO");
					break;
				}
			}

			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}

	}

	public boolean validateQuotePDF(String quoteNumber,
			String borrowerLastName, String lenderLoanNumber,
			String baseLoanAmount, String amortizationTerm,
			String amortizationType, String interestRate, String ltv,
			String cltv, String loanPurpose, String buyDown,
			String affordableHousing, String corporateRelocation,
			String auSystem, String auDecision, String originationType,
			String propertyZipCode, String propertyType, String occupancy,
			String hazardInsurance, String realEstateTaxes,
			String hoaFloodOther, String occupySubjectProperty,
			String selfEmployed, String monthlyIncome,
			String otherMonthlyPayment, String creditScore,
			String priorBankruptcy, String miCoverage, String miPaymentType,
			String miPaymentOption, String financedPremium,
			String upFrontPremium, String postPay, String borrowerLastName2,
			String lenderLoanNumber2, String baseLoanAmount2,
			String amortizationTerm2, String amortizationType2,
			String interestRate2, String ltv2, String cltv2,
			String loanPurpose2, String buyDown2, String affordableHousing2,
			String corporateRelocation2, String auSystem2, String auDecision2,
			String originationType2, String propertyZipCode2,
			String propertyType2, String occupancy2, String hazardInsurance2,
			String realEstateTaxes2, String hoaFloodOther2,
			String occupySubjectProperty2, String selfEmployed2,
			String monthlyIncome2, String otherMonthlyPayment2,
			String creditScore2, String priorBankruptcy2, String miCoverage2,
			String miPaymentType2, String miPaymentOption2,
			String financedPremium2, String upFrontPremium2, String postPay2)
			throws Throwable {

		String pdfValue = "";
		NumberFormat df2 = NumberFormat.getNumberInstance();
		df2.setMinimumFractionDigits(2);
		NumberFormat df3 = NumberFormat.getNumberInstance();
		df3.setMinimumFractionDigits(3);
		try {

			pdfValue = getText(MIGNG_PDFValidation.quoteNumber,	"PDF Quote Number");
			if (pdfValue.equals(quoteNumber)) {
				SuccessReport("PDF: Validate Quote Number",	"PDF: Quote Number validated successfully");
			} else {
				failureReport("PDF: Validate Quote Number",	"PDF: Quote Number does not match.");
			}
			
			// extract values from PDF
			getPDFValue(MIGNG_PDFValidation.summaryProductType, "Product Type");
			getPDFValue(MIGNG_PDFValidation.summaryMICoverage, "MI Coverage");
			getPDFValue(MIGNG_PDFValidation.summaryPremium, "Summary Premium");
			getPDFValue(MIGNG_PDFValidation.summaryBaseLoanAmount, "Base Loan Amount");
			getPDFValue(MIGNG_PDFValidation.summaryInsuredLoanAmount, "Insured Loan Amount");
			comparePDFValue(MIGNG_PDFValidation.summaryBorrowerLastName, borrowerLastName, borrowerLastName2, "Borrower Last Name");

			// Validations
			if (!baseLoanAmount.equals("")) {
				baseLoanAmount = NumberFormat
						.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(baseLoanAmount)).toString();
			}
			if (!baseLoanAmount2.equals("")) {
				baseLoanAmount2 = NumberFormat
						.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(baseLoanAmount2)).toString();
			}
			if (!monthlyIncome.equals("")) {
				monthlyIncome = NumberFormat
						.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(monthlyIncome)).toString();
			}
			if (!monthlyIncome2.equals("")) {
				monthlyIncome2 = NumberFormat
						.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(monthlyIncome2)).toString();
			}
			if (!otherMonthlyPayment.equals("")) {
				otherMonthlyPayment = NumberFormat
						.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(otherMonthlyPayment))
						.toString();
			}
			if (!otherMonthlyPayment2.equals("")) {
				otherMonthlyPayment2 = NumberFormat
						.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(otherMonthlyPayment2))
						.toString();
			}
			
			if (!hazardInsurance.equals("")) {
				hazardInsurance = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(hazardInsurance)).toString();
			}
			if (!hazardInsurance2.equals("")) {
				hazardInsurance2 = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(hazardInsurance2)).toString();
			}

			if (!realEstateTaxes.equals("")) {
				realEstateTaxes = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(realEstateTaxes)).toString();
			}
			if (!realEstateTaxes2.equals("")) {
				realEstateTaxes2 = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(realEstateTaxes2)).toString();
			}

			if (!hoaFloodOther.equals("")) {
				hoaFloodOther = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(hoaFloodOther)).toString();
			}
			if (!hoaFloodOther2.equals("")) {
				hoaFloodOther2 = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
						.format(Double.parseDouble(hoaFloodOther2)).toString();
			}
			
			if (!ltv.equals("")) {
				ltv = df2.format(Double.parseDouble(ltv)).toString() + "%";
			}
			if (!ltv2.equals("")) {
				ltv2 = df2.format(Double.parseDouble(ltv2)).toString() + "%";
			}
			if (!cltv.equals("")) {
				cltv = df2.format(Double.parseDouble(cltv)).toString() + "%";
			}
			if (!cltv2.equals("")) {
				cltv2 = df2.format(Double.parseDouble(cltv2)).toString() + "%";
			}
			if (!interestRate.equals("")) {
				interestRate = df3.format(Double.parseDouble(interestRate)).toString() + "%";
			}
			if (!interestRate2.equals("")) {
				interestRate2 = df3.format(Double.parseDouble(interestRate2)).toString() + "%";
			}
			if (!amortizationTerm.equals("")) {
				amortizationTerm = amortizationTerm + " Months";
			}
			if (!amortizationTerm2.equals("")) {
				amortizationTerm2 = amortizationTerm2 + " Months";
			}
			if (!miCoverage.equals("")) {
				miCoverage = miCoverage + "%";
			}
			if (!miCoverage2.equals("")) {
				miCoverage2 = miCoverage2 + "%";
			}
			if(miPaymentOption.equalsIgnoreCase("BPA") || miPaymentOption.equalsIgnoreCase("LPA")){
				miPaymentOption = "Annual";
			}else if(miPaymentOption.equalsIgnoreCase("BPM") || miPaymentOption.equalsIgnoreCase("LPM")){
				miPaymentOption = "Monthly";
			}
			if(miPaymentOption2.equalsIgnoreCase("BPA") || miPaymentOption2.equalsIgnoreCase("LPA")){
				miPaymentOption2 = "Annual";
			}else if(miPaymentOption2.equalsIgnoreCase("BPM") || miPaymentOption2.equalsIgnoreCase("LPM")){
				miPaymentOption2 = "Monthly";
			}

			comparePDFValue(MIGNG_PDFValidation.baseLoanAmount, baseLoanAmount, baseLoanAmount2, "Base Loan Amount");
			comparePDFValue(MIGNG_PDFValidation.loanPurpose, loanPurpose, loanPurpose2, "Loan Purpose");
			comparePDFValue(MIGNG_PDFValidation.amortizationTerm,amortizationTerm, amortizationTerm2, "Amortization Term");
			comparePDFValue(MIGNG_PDFValidation.amortizationType,amortizationType, amortizationType2, "Amortization Type");
			comparePDFValue(MIGNG_PDFValidation.ltv, ltv, ltv2, "LTV");
			comparePDFValue(MIGNG_PDFValidation.cltv, cltv, cltv2, "CLTV");
			comparePDFValue(MIGNG_PDFValidation.interestRate, interestRate,	interestRate2, "Interest Rate");
			comparePDFValue(MIGNG_PDFValidation.buyDown, buyDown, buyDown2,	"Buy Down");
			comparePDFValue(MIGNG_PDFValidation.corporateRelocation,corporateRelocation, corporateRelocation2,	"Corporate Relocation");
			comparePDFValue(MIGNG_PDFValidation.auSystem, auSystem, auSystem2,	"AU System");
			comparePDFValue(MIGNG_PDFValidation.originationType,originationType, originationType2, "Origination Type");
			// comparePDFValue(MIGNG_PDFValidation.auDecision, auDecision, auDecision2, "AU Decision");
			comparePDFValue(MIGNG_PDFValidation.affordableHousing,affordableHousing, affordableHousing2, "Affordable Housing");
			// comparePDFValue(MIGNG_PDFValidation.housingFinanceAgency, housingFinanceAgency, housingFinanceAgency2, "Housing Finance Agency");
			comparePDFValue(MIGNG_PDFValidation.propertyZipCode, propertyZipCode, propertyZipCode2, "Property Zip Code");
			comparePDFValue(MIGNG_PDFValidation.propertyType, propertyType,	propertyType2, "Property Type");
			// comparePDFValue(MIGNG_PDFValidation.propertyState, propertyState, propertyState2, "Property State");
			comparePDFValue(MIGNG_PDFValidation.occupancy, occupancy,	occupancy2, "Occupancy");
			comparePDFValue(MIGNG_PDFValidation.hazardInsurance, hazardInsurance, hazardInsurance2, "Hazard Insurance");
			comparePDFValue(MIGNG_PDFValidation.selfEmployed, selfEmployed,	selfEmployed2, "Self Employed");
			comparePDFValue(MIGNG_PDFValidation.monthlyIncome, monthlyIncome,	monthlyIncome2, "Monthly Income");
			comparePDFValue(MIGNG_PDFValidation.otherMonthlyPayments, otherMonthlyPayment, otherMonthlyPayment2,"Other Monthly Payments");
			comparePDFValue(MIGNG_PDFValidation.realEstateTaxes,realEstateTaxes, realEstateTaxes2, "Real Estate Taxes");
			comparePDFValue(MIGNG_PDFValidation.hoaFloodOther, hoaFloodOther,	hoaFloodOther2, "HOA, Flood, Other");
			comparePDFValue(MIGNG_PDFValidation.creditScore, creditScore,	creditScore2, "Credit Score");
			comparePDFValue(MIGNG_PDFValidation.priorBankruptcy,priorBankruptcy, priorBankruptcy2, "Prior Bankruptcy");
			comparePDFValue(MIGNG_PDFValidation.miCoverage, miCoverage,	miCoverage2, "MI Coverage");
			comparePDFValue(MIGNG_PDFValidation.occupySubjectProperty,	occupySubjectProperty, occupySubjectProperty2,"Occupy Subject Property");
			comparePDFValue(MIGNG_PDFValidation.miPaymentType, miPaymentType,	miPaymentType2, "MI Payment Type");
			comparePDFValue(MIGNG_PDFValidation.miPaymentOption,miPaymentOption, miPaymentOption2, "MI Payment Option");

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void comparePDFValue(By by, String oldValue, String newValue,
			String locatorName) throws Throwable {
		try {
			String pdfValue = "";
			new Actions(driver).moveToElement(driver.findElement(by)).build().perform();
			if (driver.findElement(by).isDisplayed()) {
				pdfValue = driver.findElement(by).getText();
			}
			if (!newValue.equalsIgnoreCase("")) {
				if (pdfValue.equalsIgnoreCase(newValue)) {
					SuccessReport("PDF: Validate " + locatorName, "PDF: "
							+ locatorName + " validated successfully with new value");
				} else {
					failureReport("PDF: Validate " + locatorName, "PDF: "
							+ locatorName
							+ " does not match with new value. OldValue : "
							+ oldValue + " , New Value : " + newValue);
				}
			} else if (pdfValue.equalsIgnoreCase(oldValue)) {
				SuccessReport("PDF: Validate " + locatorName, "PDF: "
						+ locatorName + " validated successfully");
			} else {
				// failureReport("PDF: Validate " + locatorName, "PDF: " +
				// locatorName + " does not match. OldValue : " + oldValue +
				// " , New Value : " + newValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getPDFValue(By by, String locatorName) throws Throwable {
		try {
			String pdfValue = "";
			if (driver.findElement(by).isDisplayed()) {
				pdfValue = driver.findElement(by).getText();
			}
			if (!pdfValue.equalsIgnoreCase("")) {
				SuccessReport("PDF: Extract " + locatorName, "PDF: "
						+ locatorName + " - " + pdfValue);
			} else {
				failureReport("PDF: Extract " + locatorName, "PDF: "
						+ locatorName + " could not be extracted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateQuoteInfo_RR () throws Throwable{
		String rrQuoteNo = "";
		String rrName = "";
//		String rrLoanNo = "";
		String value = "";
		try{
			rrQuoteNo = getText(MIGNG_RateRunner.MIGNG_lblQuoteNo, "RR Quote Number");
			if(!rrQuoteNo.equals("")){
				SuccessReport("Validate Quote Number", "Quote Number validated successfully. " + rrQuoteNo);
			}else{
				failureReport("Validate Quote Number", "Quote Number could not be validated");
			}
				
			rrName = getText(MIGNG_RateRunner.MIGNG_lblName, "RR Name");
			if(!rrName.equals("")){
				SuccessReport("Validate Name", "Name validated successfully. " + rrName);
			}else{
				failureReport("Validate Name", "Name could not be validated");
			}

//			rrLoanNo = getText(MIGNG_RateRunner.MIGNG_lblLoan, "RR Loan #");
//			if(!rrLoanNo.equals("")){
//				SuccessReport("Validate Loan#", "Loan# validated successfully. " + rrLoanNo);
//			}else{
//				failureReport("Validate Loan#", "Loan# could not be validated");
//			}
			
			if(isElementPresent(MIGNG_RateRunner.MIGNG_optPaymentGrid, "Payment Grid")){
				SuccessReport("Payment Grid", "Payment Grid is present");
			}else{
				failureReport("Payment Grid", "Payment Grid is not present");
			}
			
			isElementPresent(MIGNG_RateRunner.MIGNG_paymentOpt1, "Payment Option 1");
			isElementPresent(MIGNG_RateRunner.MIGNG_paymentOpt2, "Payment Option 2");
			isElementPresent(MIGNG_RateRunner.MIGNG_paymentOpt3, "Payment Option 3");
			isElementPresent(MIGNG_RateRunner.MIGNG_paymentOpt4, "Payment Option 4");
			
			value = getText(MIGNG_RateRunner.MIGNG_lblpaymentOpt11, "Payment Option 1 : Product Name");
			if(!value.equals("")){
				SuccessReport("Validate Product Value", "Product Value validated successfully. " + value);
			}else{
				failureReport("Validate Product Value", "Product Value could not be validated");
			}

			value = getText(MIGNG_RateRunner.MIGNG_lblpaymentOpt12, "Payment Option 1 : Product Name");
			if(!value.equals("")){
				SuccessReport("Validate Product Value", "Product Value validated successfully. " + value);
			}else{
				failureReport("Validate Product Value", "Product Value could not be validated");
			}

			value = getText(MIGNG_RateRunner.MIGNG_lblpaymentOpt13, "Payment Option 1 : Product Name");
			if(!value.equals("")){
				SuccessReport("Validate Product Value", "Product Value validated successfully. " + value);
			}else{
				failureReport("Validate Product Value", "Product Value could not be validated");
			}

			value = getText(MIGNG_RateRunner.MIGNG_lblpaymentOpt14, "Payment Option 1 : Product Name");
			if(!value.equals("")){
				SuccessReport("Validate Product Value", "Product Value validated successfully. " + value);
			}else{
				failureReport("Validate Product Value", "Product Value could not be validated");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void copyDataFromPdfToClipboardAndValidateUnknown() throws Throwable{
		try{
				String contents = "";
//				String val = "";
//				System.out.println("I am in...");
	            Robot robot = new Robot();
	            robot.keyPress(KeyEvent.VK_UP);
	            robot.keyRelease(KeyEvent.VK_UP);
	            Thread.sleep(1000);
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            Thread.sleep(2000);
//	            System.out.println("I am in...2");
	            //	handle alert
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            Thread.sleep(1000);
	            SuccessReport("Validate PDF report generation", "PDF report generated successfully");
	            Thread.sleep(5000);
	            
//				browser = configProps.getProperty("browserType");
				if(browser.equalsIgnoreCase("firefox")){
//		            robot.keyPress(KeyEvent.VK_ALT);
//		            Thread.sleep(500);
//		            robot.keyPress(KeyEvent.VK_CONTROL);
//		            Thread.sleep(500);
//		            robot.keyPress(KeyEvent.VK_TAB);
//		            Thread.sleep(500);
//		            robot.keyRelease(KeyEvent.VK_TAB);
//		            Thread.sleep(500);
//		            robot.keyRelease(KeyEvent.VK_CONTROL);
//		            Thread.sleep(500);
//		            robot.keyRelease(KeyEvent.VK_ALT);
//		            Thread.sleep(500);
//		            robot.keyPress(KeyEvent.VK_ENTER);
//		            robot.keyRelease(KeyEvent.VK_ENTER);
		            Thread.sleep(5000);
		            
		            robot.keyPress(KeyEvent.VK_CONTROL);
		            Thread.sleep(500);
		            robot.keyPress(KeyEvent.VK_A);
		            Thread.sleep(500);
		            robot.keyRelease(KeyEvent.VK_A);
		            Thread.sleep(500);
		            robot.keyPress(KeyEvent.VK_C);
		            Thread.sleep(500);
		            robot.keyRelease(KeyEvent.VK_C);
		            robot.keyRelease(KeyEvent.VK_CONTROL);
		
		            contents = UploadFileRobot.getClipboardData();
		            
		            if(contents.contains("Origination Type: Unknown")){
		            	SuccessReport("Validate Origination Type", "Origination Type: Unknown");
		            }else{
		            	failureReport("Validate Origination Type", "Origination Type is not Unknown");
		            }
		
		            if(contents.contains("Affordable Housing: Unknown")){
		            	SuccessReport("Validate Affordable Housing", "Affordable Housing:: Unknown");
		            }else{
		            	failureReport("Validate Affordable Housing:", "Affordable Housing: is not Unknown");
		            }
		            
		            if(contents.contains("Property Type: Unknown")){
		            	SuccessReport("Validate Property Type", "Property Type: Unknown");
		            }else{
		            	failureReport("Validate Property Type", "Property Type is not Unknown");
		            }
		            
		            if(contents.contains("Self-Employed: Unknown")){
		            	SuccessReport("Validate Self-Employed", "Self-Employed: Unknown");
		            }else{
		            	failureReport("Validate Self-Employed", "Self-Employed is not Unknown");
		            }
		
		            if(contents.contains("Prior Bankruptcy: Unknown")){
		            	SuccessReport("Validate Prior Bankruptcy", "Prior Bankruptcy: Unknown");
		            }else{
		            	failureReport("Validate Prior Bankruptcy", "Prior Bankruptcy is not Unknown");
		            }
				}else{
					informationReport("Validate PDF report", "PDF report validation cannot be done on Chrome & IE");
				}


//	            val = contents.split("Origination Type:")[1].trim().split("AU Decision:")[0].trim();
//	            System.out.println("Origination Type: " + val);
//	            
//	            val = contents.split("Affordable Housing:")[1].trim().split("Housing Finance Agency:")[0].trim();
//	            System.out.println("Affordable Housing: " + val);
//	            
//	            val = contents.split("Property Type:")[1].trim().split("Property State:")[0].trim();
//	            System.out.println("Property Type: " + val);
//	            
//	            val = contents.split("Self-Employed:")[1].trim().split("Monthly Income:")[0].trim();
//	            System.out.println("Self-Employed: " + val);
//	
//	            val = contents.split("Prior Bankruptcy:")[1].trim().split("Mortgage Insurance Information:")[0].trim();
//	            System.out.println("Prior Bankruptcy: " + val);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void searchAndOpenQuote(String quoteNo) throws Throwable{
		String columnName1 = "";
		String columnName2 = "";
		try{
			//	wait for Pipeline to load
//			waitForInVisibilityOfElement(MIGNG_HomePage.MIGNG_emptySearch, "Pipeline loaded");

			if(isElementPresent(MIGNG_HomePage.MIGNG_searchField, "Search Field")){
				type(MIGNG_HomePage.MIGNG_searchField, quoteNo, "Enter Quote Number");
				Thread.sleep(1000);
				click(MIGNG_HomePage.MIGNG_searchButton, "Search Quote");
				Thread.sleep(2000);
				int rows = driver.findElements(By.xpath("//tbody/tr")).size();
				if(rows > 0){
					columnName1 = driver.findElement(By.xpath("//div[@id='pipeline']//tbody/tr/td[3]")).getText().trim();
					columnName2 = driver.findElement(By.xpath("//div[@id='pipeline']//tbody/tr/td[5]")).getText().trim();
					if (columnName1.equalsIgnoreCase(quoteNo) && columnName2.equalsIgnoreCase("Successful")) {
						SuccessReport("Check if Quote Found", "Quote found successfully");
					}
				}
				if(openExistingQuote(quoteNo)){
					SuccessReport("Verify if Quote Opened", "Quote is opened and details displayed successfully");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void searchAndOpenApplication(String applicationNo) throws Throwable{
		try{
			//	wait for Pipeline to load
//			waitForInVisibilityOfElement(MIGNG_HomePage.MIGNG_emptySearch, "Pipeline loaded");
			
			if(isElementPresent(MIGNG_HomePage.MIGNG_searchField, "Search Field")){
				type(MIGNG_HomePage.MIGNG_searchField, applicationNo, "Enter Application Number");
				Thread.sleep(1000);
				click(MIGNG_HomePage.MIGNG_searchButton, "Search Application");
				Thread.sleep(2000);
				int rows = driver.findElements(By.xpath("//tbody/tr")).size();
				if(rows > 0){
					SuccessReport("Check if Application Found", "Application found successfully");
					openExistingApplication(applicationNo);
					String status = getText(MIGNG_HomePage.MIGNG_applicationStatus, "Application Status");
					if(status.contains("Under Review")){
						SuccessReport("Verify Application Status", "Application Status is Under Review ");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean searchQuoteOrApplicationFromPipeline(String quoteNo, String applicationNo, String group, String operation) throws Throwable{
		boolean flag = false;
		String columnName1 = "";
		String searchKey = "";
		try{
			//	wait for Pipeline to load
//			waitForInVisibilityOfElement(MIGNG_HomePage.MIGNG_emptySearch, "Pipeline loaded");

			if(isElementPresent(MIGNG_HomePage.MIGNG_searchField, "Search Field")){
				if(!group.equals("")){
					selectByValue(MIGNG_HomePage.MIGNG_showMe, group, "Filter");
					Thread.sleep(2000);
				}

				if(!quoteNo.equals("")){
					searchKey = quoteNo;
					if(operation.equalsIgnoreCase("Search")){
						type(MIGNG_HomePage.MIGNG_searchField, quoteNo, "Enter Quote Number");
						click(MIGNG_HomePage.MIGNG_searchButton, "Search Quote");
						Thread.sleep(1000);
					}
				}else if(!applicationNo.equals("")){
					searchKey = applicationNo;
					if(operation.equalsIgnoreCase("Search")){
						type(MIGNG_HomePage.MIGNG_searchField, applicationNo, "Enter Application Number");
						click(MIGNG_HomePage.MIGNG_searchButton, "Search Application");
						Thread.sleep(1000);
					}
				}
				
				int rows = driver.findElements(By.xpath("//tbody/tr")).size();
				for (int i=1; i<=rows; i++) {
					columnName1 = driver.findElement(By.xpath("//div[@id='pipeline']//tbody/tr[" + i + "]/td[3]")).getText().trim();
					if (columnName1.equalsIgnoreCase(searchKey)) {
						SuccessReport("Check if Quote/Application Found", "Quote/Application found successfully");
						flag = true;
						break;
					}else{
//						System.out.println(columnName1 + " / " + searchKey);
					}
				}
			}
		}catch(Exception e){			
//			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	
	public boolean logInDocumentum() throws Throwable {
		boolean flag = true;
		try {
				String docURLVariable = configProps.getProperty("varDocumentum_URL");
				String Documentum_userName = configProps.getProperty("Documentum_userName");
				String Documentum_password = configProps.getProperty("Documentum_password");

				launchUrl(configProps.getProperty(docURLVariable));
//				launchUrl(configProps.getProperty("Documentum_URL"));
				Thread.sleep(3000);
				
				if(isAlertPresent()){
					Alert alert = driver.switchTo().alert();
					alert.accept();
				}

				// Wait for the availability of Documentum login Page
				waitForVisibilityOfElement(MIGNG_Documentum.documentum_loginScreen,	"Login Page");

				// Enter data in UserName field on Documentum login Page
				type(MIGNG_Documentum.documentum_Username, Documentum_userName, "Username field");
				// Enter data in Password field on Documentum login Page
				type(MIGNG_Documentum.documentum_Password, Documentum_password, "Password field");

				// Wait for the availability of Login button on Documentum login Page
				click(MIGNG_Documentum.documentum_btnLogin, "Login button");

				Thread.sleep(3000);
				
				// Verify if Home Page appeared successfully
				if (verifyHomePageDocumentum()) {
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
	
	public boolean verifyHomePageDocumentum() throws Throwable {
		try {
			driver.switchTo().frame("MainEx_titlebar_0");
			Thread.sleep(3000);
			waitForVisibilityOfElement(MIGNG_Documentum.documentum_btnLogout, "Logout button");
			if (isElementPresent(MIGNG_Documentum.documentum_btnLogout, "HomePage")) {
				SuccessReport("Verfiy HomePage", "HomePage loaded successfully");
			} else {
				failureReport("Verfiy HomePage", "HomePage failed to load");
			}

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean logoutDocumentum() throws Throwable {
		boolean flag = true;

		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame("MainEx_titlebar_0");
			// Wait for the availability of Sign out button on Documentum login Page
			click(MIGNG_Documentum.documentum_btnLogout, "Logout button");
			driver.switchTo().defaultContent();
//			driver.switchTo().activeElement();
			Thread.sleep(3000);
			// Wait for the availability of Documentum login Page
			boolean flag1 = waitForVisibilityOfElement(MIGNG_Documentum.documentum_loginScreen,	"Login Page");

			// Wait for the availability of Login Again button on Documentum logout
			boolean flag2 = waitForVisibilityOfElement(MIGNG_Documentum.documentum_btnLoginAgain, "Login Again button");

			flag = flag1 && flag2;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public void validatePropertyInfoFromPDFUsingClipboard(String propertyStreet, 
			String propertyCity, String propertyState, String propertyZip) throws Throwable{
		try{
				String contents = "";
	            Robot robot = new Robot();
	            SuccessReport("Validate PDF report generation", "PDF report generated successfully");
	            Thread.sleep(5000);
	            
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            Thread.sleep(1000);
	            robot.keyPress(KeyEvent.VK_A);
	            Thread.sleep(1000);
	            robot.keyRelease(KeyEvent.VK_A);
	            Thread.sleep(1000);
	            robot.keyPress(KeyEvent.VK_C);
	            Thread.sleep(1000);
	            robot.keyRelease(KeyEvent.VK_C);
	            Thread.sleep(1000);
	            robot.keyRelease(KeyEvent.VK_CONTROL);

	            contents = UploadFileRobot.getClipboardData();
//	            System.out.println(contents);
	            if(contents.contains("Property Address " + propertyStreet) || 
	            		contents.contains("Property Address  " + propertyStreet) || 
	            		contents.contains("Property Address: " + propertyStreet) || 
	            		contents.contains("Property Address:  " + propertyStreet)){
	            	SuccessReport("Validate Property Address", "Property Address validated successfully. " + propertyStreet);
	            }else{
	            	failureReport("Validate Property Address", "Property Address is not validated");
	            }
	
	            if(contents.contains("Property City " + propertyCity) || 
	            		contents.contains("Property City  " + propertyCity) || 
	            		contents.contains("Property City: " + propertyCity) || 
	            		contents.contains("Property City:  " + propertyCity)){
	            	SuccessReport("Validate Property City", "Property City validated successfully. " + propertyCity);
	            }else{
	            	failureReport("Validate Property City", "Property City is not validated");
	            }

	            if(contents.contains("Property State: " + propertyState)){
	            	SuccessReport("Validate Property State", "Property State validated successfully. " + propertyState);
	            }else{
	            	failureReport("Validate Property State", "Property State is not validated");
	            }

	            if(contents.contains("Property ZIP Code: " + propertyZip)){
	            	SuccessReport("Validate Property ZIP Code", "Property ZIP Code validated successfully. " + propertyZip);
	            }else{
	            	failureReport("Validate Property ZIP Code", "Property ZIP Code is not validated");
	            }
	            
	            if(browser.equalsIgnoreCase("ie")){
		            Thread.sleep(3000);
		            robot.keyPress(KeyEvent.VK_ALT);
		            Thread.sleep(1000);
		            robot.keyPress(KeyEvent.VK_F4);
		            Thread.sleep(1000);
		            robot.keyRelease(KeyEvent.VK_F4);
		            Thread.sleep(1000);
		            robot.keyRelease(KeyEvent.VK_C);
		            robot.keyRelease(KeyEvent.VK_ALT);
	            }
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean searchRateQuoteInDocumentum(String rateQuoteNumber) throws Throwable {
		try {
				int objCountBeforeExpand = 0;
				int objCountAfterExpand = 0;
			
				// Login to Documentum application
				if (logInDocumentum()) {
					SuccessReport("Login MIGuide NG",
							"Successfully logged into MIGuide NG");
				} else {
					failureReport("Login MIGuide NG", "Unable to log in");
				}

				driver.switchTo().defaultContent();
				driver.switchTo().frame("MainEx_view_0").switchTo().frame("Classic_browser_0");
				
//				List<WebElement> items = driver.findElements(By.xpath("//span"));
//				for(WebElement ele : items){
//					System.out.println(ele.getText());
//				}
				
				if (isElementPresent(MIGNG_Documentum.documentum_Cabinets, "Cabinets") && 
						isElementPresent(MIGNG_Documentum.documentum_CabinetsExpand, "Cabinets Expand")) {

					objCountBeforeExpand = driver.findElements(By.xpath("//span")).size();
					click(MIGNG_Documentum.documentum_CabinetsExpand, "Cabinets Expand");
					
					do{
						objCountAfterExpand = driver.findElements(By.xpath("//span")).size();
					} while(objCountAfterExpand == objCountBeforeExpand);

					SuccessReport("Cabinets Expand", "Cabinets expanded successfully");
				} else {
					failureReport("Cabinets Expand", "Object Not Found");
				}

				if (isElementPresent(MIGNG_Documentum.documentum_RateQuote, "Rate Quote") && 
						isElementPresent(MIGNG_Documentum.documentum_RateQuoteExpand, "Rate Quote Expand")) {

					objCountBeforeExpand = driver.findElements(By.xpath("//span")).size();
					click(MIGNG_Documentum.documentum_RateQuoteExpand, "Rate Quote Expand");
					
					do{
						objCountAfterExpand = driver.findElements(By.xpath("//span")).size();
					} while(objCountAfterExpand == objCountBeforeExpand);

					SuccessReport("Rate Quote Expand", "Rate Quote expanded successfully");
				} else {
					failureReport("Rate Quote Expand", "Object Not Found");
				}
				
				if (isElementPresent(MIGNG_Documentum.documentum_RateQuotes, "Rate Quotes") && 
						isElementPresent(MIGNG_Documentum.documentum_RateQuotesExpand, "Rate Quotes Expand")) {

					click(MIGNG_Documentum.documentum_RateQuotes, "Rate Quotes");
					
					driver.switchTo().defaultContent();

					driver.switchTo().frame("MainEx_view_0").
						switchTo().frame("Classic_workarea_0").
							switchTo().frame("Form_content_0");
					
					waitForVisibilityOfElement(MIGNG_Documentum.documentum_txtSearch, "Search field");
					waitForVisibilityOfElement(MIGNG_Documentum.documentum_lstDocument,	"Document List");

					SuccessReport("Rate Quotes", "Rate Quotes selected successfully");
				} else {
					failureReport("Rate Quotes", "Rate Quotes not selected");
				}

				// Wait for the availability of Search field on Documentum Page
				type(MIGNG_Documentum.documentum_txtSearch, rateQuoteNumber, "Search field");
				objCountBeforeExpand = driver.findElements(MIGNG_Documentum.documentum_lstDocument).size();
				click(MIGNG_Documentum.documentum_btnSearch, "Search");

				
//				do{
//					objCountAfterExpand = driver.findElements(MIGNG_Documentum.documentum_lstDocument).size();
//				} while(objCountAfterExpand < objCountBeforeExpand);
//				
//				if(driver.findElement(MIGNG_Documentum.documentum_lstDocumentNotFound).isDisplayed()){
//					failureReport("Verify if Quote document found", "Quote document not found.");
//				}else{
//				}
				
				do{
					objCountAfterExpand = driver.findElements(MIGNG_Documentum.documentum_lstDocumentSize).size();
				} while(objCountAfterExpand > 1);
				
				String title = driver.findElement(MIGNG_Documentum.documentum_lstDocumentItem).getAttribute("title");
				if(title.contains(rateQuoteNumber)){
					SuccessReport("Verify if Quote document found", "Quote document found successfully." + title);
				}else{
					failureReport("Verify if Quote document found", "Quote document not found.");
				}
		
				//	Logout from Documentum application
				if (logoutDocumentum()) {
					SuccessReport("Logout Documentum",	"Successfully logged out from Documentum");
				} else {
					failureReport("Logout Documentum", "Unable to log out");
				}
				
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void validatePropertyInfoFromPDF(String quoteNumber, String baseLoanAmount, 
			String propertyStreet, 
			String propertyCity, String propertyState, String propertyZip) throws Throwable{
		try{
			String pdfValue = "";
			
			pdfValue = getText(MIGNG_PDFValidation.quoteNumber,	"PDF Quote Number");
			if (pdfValue.equals(quoteNumber)) {
				SuccessReport("PDF: Validate Quote Number",	"PDF: Quote Number validated successfully");
			} else {
				failureReport("PDF: Validate Quote Number",	"PDF: Quote Number does not match.");
			}
			
			// extract values from PDF
			getPDFValue(MIGNG_PDFValidation.summaryProductType, "Product Type");
			getPDFValue(MIGNG_PDFValidation.summaryMICoverage, "MI Coverage");
			getPDFValue(MIGNG_PDFValidation.summaryPremium, "Summary Premium");
			getPDFValue(MIGNG_PDFValidation.summaryBaseLoanAmount, "Base Loan Amount");
			getPDFValue(MIGNG_PDFValidation.summaryInsuredLoanAmount, "Insured Loan Amount");
			comparePDFValue(MIGNG_PDFValidation.baseLoanAmount, baseLoanAmount,	"", "Base Loan Amount");
			//	Validated values for Kentucky state property
			comparePDFValue(MIGNG_PDFValidation.propertyStreet, propertyStreet, "", "Property Address");
			comparePDFValue(MIGNG_PDFValidation.propertyCity, propertyCity, "", "Property City");
			comparePDFValue(MIGNG_PDFValidation.propertyZipCode, propertyZip, "", "Property Zip Code");
			comparePDFValue(MIGNG_PDFValidation.propertyState, propertyState, "", "Property State");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	
	public void validateLenderDetailsInQuotePDF(String quoteNumber, String borrowerLastName,
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
			String financedPremium, String upFrontPremium, String postPay) throws Throwable{
		String parentWindow = null;
		Set<String> handles = null;
		try{
			if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome")){
				parentWindow = driver.getWindowHandle();
				Thread.sleep(1000);
				// Click Print PDF
				click(MIGNG_NewQuote.MIGNG_printPDF, "Print PDF");
				Thread.sleep(1000);
				handles = driver.getWindowHandles();
				if(handles.size()>1){
					SuccessReport("Validate PDF report genaration", "PDF report generated successfully");
					for (String windowHandle : handles) {
						if (!windowHandle.equals(parentWindow)) {
							driver.switchTo().window(windowHandle);
							if(browser.equalsIgnoreCase("firefox")){
								// perform operation on new window
								validateQuotePDF(quoteNumber, borrowerLastName,
										lenderLoanNumber, baseLoanAmount, amortizationTerm,
										amortizationType, interestRate, ltv, cltv,
										loanPurpose, buyDown, affordableHousing,
										corporateRelocation, auSystem, auDecision,
										originationType, propertyZipCode, propertyType,
										occupancy, hazardInsurance, realEstateTaxes,
										hoaFloodOther, occupySubjectProperty, selfEmployed,
										monthlyIncome, otherMonthlyPayment, creditScore,
										priorBankruptcy, miCoverage, miPaymentType,
										miPaymentOption, financedPremium, upFrontPremium,
										postPay, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
										"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
	
								getPDFValue(MIGNG_PDFValidation.submittingBranchId, "Submitting Branch Id");
								getPDFValue(MIGNG_PDFValidation.submittingBranchName, "Submitting Branch Name");
								getPDFValue(MIGNG_PDFValidation.submittingBranch, "Submitting Branch");
								getPDFValue(MIGNG_PDFValidation.addressLender, "Address");
							}
							// close the child window
							driver.close();
							Thread.sleep(1000);
							// switch back to old window
							driver.switchTo().window(parentWindow);
							Thread.sleep(1000);
						}
					}
				}else{
					failureReport("Validate PDF report genaration", "PDF report could not be generated");
				}
			}
		}catch(Exception e){
			e.getMessage();
		}
	}

	public boolean verifyPortfolioPage() throws Throwable {
		try {
			Thread.sleep(3000);

			if (isElementPresent(MIGNG_HomePage.MIGNG_portfolio, "Portfolio HomePage")) {
				SuccessReport("Verfiy Portfolio HomePage", "Portfolio HomePage loaded successfully");
			} else {
				failureReport("Verfiy Portfolio HomePage", "Portfolio HomePage failed to load");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline")) {
				SuccessReport("Verfiy Pipeline on Header", "Pipeline is available on the Header");
			} else {
				failureReport("Verfiy Pipeline on Header", "Pipeline is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote")) {
				SuccessReport("Verfiy New Quote on Header", "New Quote is available on the Header");
			} else {
				failureReport("Verfiy New Quote on Header", "New Quote is not available on the Header");
			}

			if (getText(MIGNG_HomePage.MIGNG_headerNewFullFile, "New Full-File").contains("New Full-File")) {
				SuccessReport("Verfiy New Full-File Application on Header",	"New Full-File Application is available on the Header");
			} else {
				failureReport("Verfiy New Full-File Application on Header",	"New Full-File Application is not available on the Header");
			}

			if (getText(MIGNG_HomePage.MIGNG_headerDelegated, "Delegated").contains("Delegated")) {
				SuccessReport("Verfiy Delegated (RapidLink) on Header",	"Delegated (RapidLink) is available on the Header");
			} else {
				failureReport("Verfiy Delegated (RapidLink) on Header",	"Delegated (RapidLink) is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerPortfolio, "Portfolio")) {
				SuccessReport("Verfiy Portfolio on Header", "Portfolio is available on the Header");
			} else {
				failureReport("Verfiy Portfolio on Header", "Portfolio is not available on the Header");
			}

			if (assertText(MIGNG_HomePage.MIGNG_headerReports, "Reports")) {
				SuccessReport("Verfiy Reports on Header", "Reports is available on the Header");
			} else {
				failureReport("Verfiy Reports on Header", "Reports is not available on the Header");
			}

			if (isElementPresent(MIGNG_HomePage.MIGNG_portfolioHeader, "Portfolio")) {
				List<WebElement> list = driver.findElements(MIGNG_HomePage.MIGNG_portfolioHeader);
				if(list.get(1).getText().contains("Loan No.") && list.get(2).getText().contains("Borrower") &&
						list.get(3).getText().contains("Certificate No.") && list.get(4).getText().contains("Type") &&
						list.get(5).getText().contains("Status")){
					
					SuccessReport("Verfiy Portfolio", "Portfolio is displayed");
				}
			} else {
				failureReport("Verfiy Portfolio", "Portfolio is not displayed");
			}
			
			Thread.sleep(3000);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void validatePipeline() throws Throwable{
		try{
			Thread.sleep(3000);
			//	validate Pipeline
			String columnName = "";
			
			if(driver.findElements(By.xpath("//div[@id='pipeline']//thead/tr/th")).size() == 5){
				SuccessReport("Columns in Pipeline table", "There are five columns in pipeline table");
			}
			
			columnName = driver.findElement(By.xpath("//div[@id='pipeline']//thead/tr/th[1]")).getText();
			if(columnName.equalsIgnoreCase("Last Update")){
				SuccessReport("Columns in Pipeline table", "Column 1 in pipeline table: " + columnName);
			}
			
			columnName = driver.findElement(By.xpath("//div[@id='pipeline']//thead/tr/th[2]")).getText();
			if(columnName.equalsIgnoreCase("Borrower")){
				SuccessReport("Columns in Pipeline table", "Column 2 in pipeline table: " + columnName);
			}

			columnName = driver.findElement(By.xpath("//div[@id='pipeline']//thead/tr/th[3]")).getText();
			if(columnName.equalsIgnoreCase("UG ID")){
				SuccessReport("Columns in Pipeline table", "Column 3 in pipeline table: " + columnName);
			}

			columnName = driver.findElement(By.xpath("//div[@id='pipeline']//thead/tr/th[4]")).getText();
			if(columnName.equalsIgnoreCase("Type")){
				SuccessReport("Columns in Pipeline table", "Column 4 in pipeline table: " + columnName);
			}

			columnName = driver.findElement(By.xpath("//div[@id='pipeline']//thead/tr/th[5]")).getText();
			if(columnName.equalsIgnoreCase("Status")){
				SuccessReport("Columns in Pipeline table", "Column 5 in pipeline table: " + columnName);
			}
			Thread.sleep(2000);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public void createQuoteAndVerifyStateTaxes(String borrowerLastName,
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
		try{
			String parentWindow = null;
			Set<String> handles = null;
			boolean taxes = true;
			boolean taxPDF = true;

			// Create New Quote
			if (createNewQuote(borrowerLastName, lenderLoanNumber,
					baseLoanAmount, amortizationTerm, amortizationType,
					interestRate, ltv, cltv, loanPurpose, buyDown,
					affordableHousing, corporateRelocation, auSystem,
					auDecision, originationType, propertyZipCode, propertyType,
					occupancy, hazardInsurance, realEstateTaxes, hoaFloodOther,
					occupySubjectProperty, selfEmployed, monthlyIncome,
					otherMonthlyPayment, creditScore, priorBankruptcy,
					miCoverage, miPaymentType, miPaymentOption,
					financedPremium, upFrontPremium, postPay, quoteType)) {
				SuccessReport("Create New Quote",
						"New Quote created successfully");
			} else {
				failureReport("Create New Quote", "Unable to Create New Quote");
			}

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present", "Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present", "Resubmit button is not present");
			}

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");
			
			List<WebElement> list1 = driver.findElements(By.xpath("//div[@class='price-body']//h3/small"));
			List<WebElement> list2 = driver.findElements(By.xpath("//div[@class='price-body']/ul/li[2]"));
			List<WebElement> list3 = driver.findElements(By.xpath("//div[@class='price-body']/ul/li[3]"));
			
			for (int i = 0; i < (list1.size() - 1); i++){
				if(!list1.get(i).getText().contains("incl. taxes")){
					taxes = false;
				}
			}
			for (int i = 0; i < (list2.size() - 1); i++){
				if(!list2.get(i).getText().contains("Tax Rate")){
					taxes = false;
				}
			}
			for (int i = 0; i < (list3.size() - 1); i++){
				if(!list3.get(i).getText().contains("MI Rate w/ Taxes")){
					taxes = false;
				}
			}
			
			if(taxes){
				SuccessReport("Verify Taxes are applied on MI Premium", "Taxes are applied on MI Premium");
			}else{
				failureReport("Verify Taxes are applied on MI Premium", "Taxes are not applied on MI Premium");
			}
			
			if(browser.equalsIgnoreCase("firefox")){
				parentWindow = driver.getWindowHandle();
				Thread.sleep(1000);
				// Click Print PDF
				click(MIGNG_NewQuote.MIGNG_printPDF, "Print PDF");
				Thread.sleep(1000);
				handles = driver.getWindowHandles();
				if(handles.size()>1){
					SuccessReport("Validate PDF report genaration", "PDF report generated successfully");
					for (String windowHandle : handles) {
						if (!windowHandle.equals(parentWindow)) {
							driver.switchTo().window(windowHandle);
							// perform operation on new window
							validateQuotePDF(quoteNumber, borrowerLastName,
									lenderLoanNumber, baseLoanAmount, amortizationTerm,
									amortizationType, interestRate, ltv, cltv,
									loanPurpose, buyDown, affordableHousing,
									corporateRelocation, auSystem, auDecision,
									originationType, propertyZipCode, propertyType,
									occupancy, hazardInsurance, realEstateTaxes,
									hoaFloodOther, occupySubjectProperty, selfEmployed,
									monthlyIncome, otherMonthlyPayment, creditScore,
									priorBankruptcy, miCoverage, miPaymentType,
									miPaymentOption, financedPremium, upFrontPremium,
									postPay, "", "", "", "",	"", "", "", "", "", "", "",
									"", "", "", "", "", "",
									"", "", "", "", "",	"", "",	"", "",	"", "", "",	"", "",
									"", "");

							if(isElementPresent(MIGNG_PDFValidation.taxUpFrontMIPremiumWithTaxes, "Up-front MI Premium with Taxes")){
								SuccessReport("Up-front MI Premium with Taxes", "Up-front MI Premium with Taxes is applied");
							}else{
								taxPDF = false;
							}
							if(isElementPresent(MIGNG_PDFValidation.taxUpFrontMIPremiumRateWithTaxes, "Up-front MI Premium Rate with Taxes")){
								SuccessReport("Up-front MI Premium Rate with Taxes", "Up-front MI Premium Rate with Taxes is applied");
							}else{
								taxPDF = false;
							}
							if(isElementPresent(MIGNG_PDFValidation.taxMonthlyMIPremiumWithTaxes, "Monthly MI Premium with Taxes")){
								SuccessReport("Monthly MI Premium with Taxes", "Monthly MI Premium with Taxes is applied");
							}else{
								taxPDF = false;
							}
							if(isElementPresent(MIGNG_PDFValidation.taxMonthlyMIPremiumRateWithTaxes, "Monthly MI Premium Rate with Taxes")){
								SuccessReport("Monthly MI Premium Rate with Taxes", "Monthly MI Premium Rate with Taxes is applied");
							}else{
								taxPDF = false;
							}
							
							if(!taxPDF){
								failureReport("Verify Taxes are applied on MI Premium", "Taxes are not applied on MI Premium");
							}
							
							// close the child window
							driver.close();
							Thread.sleep(1000);
							// switch back to old window
							driver.switchTo().window(parentWindow);
							Thread.sleep(1000);
						}
					}
				}else{
					failureReport("Validate PDF report genaration", "PDF report could not be generated");
				}
			}
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	public void searchRateQuoteInDocumentumInNewBrowser(String quoteNumber) throws Throwable{
		try{
			String parentWindow = null;
			Set<String> handles = null;

			parentWindow = driver.getWindowHandle();
			if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome")){
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
				handles = driver.getWindowHandles();
				for (String windowHandle : handles) {
					if (!windowHandle.equals(parentWindow)) {
//						System.out.println("switch to child...");
						driver.switchTo().window(windowHandle);
						//	Search Quote in Documentum
						searchRateQuoteInDocumentum(quoteNumber);
						// close the child window
						driver.close();
						Thread.sleep(1000);
					}
				}
			}else if(browser.equalsIgnoreCase("ie")){
				File ieDriver = new File("Drivers\\IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				webDriver = new InternetExplorerDriver(dc);
				driver = new EventFiringWebDriver(webDriver);
//				MyListener myListener = new MyListener();
//				driver.register(myListener);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.navigate().to(configProps.getProperty("Documentum_URL"));
				ImplicitWait();
				//	Search Quote in Documentum
				searchRateQuoteInDocumentum(quoteNumber);
				// close the child window
				driver.close();
				Thread.sleep(1000);
			}
			// switch back to old window
			driver.switchTo().window(parentWindow);
			Thread.sleep(1000);
		}catch(Exception e){
			e.getMessage();
		}
	}
	

	public String renewValues(String value1, String value2) throws Throwable {
		String value = value1;
		try {
			if (!value2.equals("")) {
				value = value2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public static String RandomNumberGeneration(int range) {
		String randomInt = Integer.toString((int) (Math.random() * range)) + 1000;
		return randomInt;
	}
}