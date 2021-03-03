package com.ugc.scripts;

import java.util.Set;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class Quoting_VerifyProperty_StreetAddressCity_Kentucky extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Quoting_Verify_Property_Street_Address_City_Kentucky (String borrowerLastName,
			String lenderLoanNumber, String baseLoanAmount,
			String amortizationTerm, String amortizationType,
			String interestRate, String ltv, String cltv, String loanPurpose,
			String buyDown, String affordableHousing,
			String corporateRelocation, String auSystem, String auDecision,
			String originationType, String propertyZipCode, String propertyStreet, String propertyCity, 
			String propertyType, String occupancy, String hazardInsurance,
			String realEstateTaxes, String hoaFloodOther,
			String occupySubjectProperty, String selfEmployed,
			String monthlyIncome, String otherMonthlyPayment,
			String creditScore, String priorBankruptcy, String miCoverage,
			String miPaymentType, String miPaymentOption,
			String financedPremium, String upFrontPremium, String postPay, String quoteType,
			String propertyZipCode2, String propertyStreet2, String propertyCity2, 
			String propertyZipCode3, String propertyStreet3, String propertyCity3) throws Throwable {

		String parentWindow = null;
		Set<String> handles = null;

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			
			// Create New Quote
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

			// Enter value for Property Street
			type(MIGNG_NewQuote.MIGNG_propertyStreet, propertyStreet, "Property Street");
			
			// Click on Get Quote button
			click(MIGNG_NewQuote.MIGNG_getQuote, "Get Quote");
			
			// Validate message after submitting application
			validateSubmitQuote(quoteType);

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present",	"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",	"Resubmit button is not present");
			}

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");
			if(!quoteNumber.equalsIgnoreCase("")){
				SuccessReport("Quote Number", "Quote Number: " + quoteNumber + " generated successfully.");
			}
			
//			browser = configProps.getProperty("browserType");
			if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("chrome")){
				parentWindow = driver.getWindowHandle();
				Thread.sleep(1000);
				// Click Print PDF
				click(MIGNG_NewQuote.MIGNG_printPDF, "Print PDF");
				Thread.sleep(1000);
				handles = driver.getWindowHandles();
//				System.out.println(handles.size());
				if(handles.size()>1){
					SuccessReport("Validate PDF report genaration", "PDF report generated successfully");
					for (String windowHandle : handles) {
						if (!windowHandle.equals(parentWindow)) {
							driver.switchTo().window(windowHandle);
							
							if(browser.equalsIgnoreCase("firefox")){
								// perform operation on new window
								validatePropertyInfoFromPDF(quoteNumber, baseLoanAmount, propertyStreet, propertyCity, "KENTUCKY", propertyZipCode);
							}else if(browser.equalsIgnoreCase("chrome")){
//								Thread.sleep(5000);
//								MIGNG_validatePropertyInfoFromPDFUsingClipboard(propertyStreet, propertyCity, "Kentucky", propertyZipCode);
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
			}else if(browser.equalsIgnoreCase("ie")){
				// Click Print PDF
				click(MIGNG_NewQuote.MIGNG_printPDF, "Print PDF");
				Thread.sleep(5000);
				validatePropertyInfoFromPDFUsingClipboard(propertyStreet, propertyCity, "Kentucky", propertyZipCode);
			}
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);

			// Create New Quote with new set of data
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
					auDecision, originationType, propertyZipCode2, propertyType,
					occupancy, hazardInsurance, realEstateTaxes, hoaFloodOther,
					occupySubjectProperty, selfEmployed, monthlyIncome,
					otherMonthlyPayment, creditScore, priorBankruptcy,
					miCoverage, miPaymentType, miPaymentOption,
					financedPremium, upFrontPremium, postPay, quoteType);

			// Enter value for Property Zip Code
			type(MIGNG_NewQuote.MIGNG_propertyStreet, propertyStreet2, "Property Street");
			
			// Click on Get Quote button
			click(MIGNG_NewQuote.MIGNG_getQuote, "Get Quote");
			
			// Validate message after submitting application
			validateSubmitQuote(quoteType);

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present",	"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",	"Resubmit button is not present");
			}

			quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");
			if(!quoteNumber.equalsIgnoreCase("")){
				SuccessReport("Quote Number", "Quote Number: " + quoteNumber + " generated successfully.");
			}
			
//			browser = configProps.getProperty("browserType");
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
								validatePropertyInfoFromPDF(quoteNumber, baseLoanAmount, propertyStreet2, propertyCity2, "KENTUCKY", propertyZipCode2);
							}else if(browser.equalsIgnoreCase("chrome")){
//								Thread.sleep(5000);
//								MIGNG_validatePropertyInfoFromPDFUsingClipboard(propertyStreet2, propertyCity2, "Kentucky", propertyZipCode2);
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
			}else if(browser.equalsIgnoreCase("ie")){
				// Click Print PDF
				click(MIGNG_NewQuote.MIGNG_printPDF, "Print PDF");
				Thread.sleep(5000);
				validatePropertyInfoFromPDFUsingClipboard(propertyStreet2, propertyCity2, "Kentucky", propertyZipCode2);
			}
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);

			// successful RR quote, and views/opens the quote.
			openExistingQuote(quoteNumber);

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present", "Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present", "Resubmit button is not present");
			}

			// Edit Quote
			// Enter value for Property Zip Code
			type(MIGNG_NewQuote.MIGNG_propertyZipCode, propertyZipCode3, "Property ZIP Code");
			// Enter value for Credit Score
			type(MIGNG_NewQuote.MIGNG_creditScore, creditScore,	"Credit Score");
			// Enter value for Property Street
			type(MIGNG_NewQuote.MIGNG_propertyStreet, propertyStreet3, "Property Street");

			// Click on Resubmit button
			click(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit");

			// Validate message after submitting application
			validateSubmitQuote(quoteType);

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
								validatePropertyInfoFromPDF(quoteNumber, baseLoanAmount, propertyStreet3, propertyCity3, "KENTUCKY", propertyZipCode3);
							}else if(browser.equalsIgnoreCase("chrome")){
//								Thread.sleep(5000);
//								MIGNG_validatePropertyInfoFromPDFUsingClipboard(propertyStreet3, propertyCity3, "Kentucky", propertyZipCode3);
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
			}else if(browser.equalsIgnoreCase("ie")){
				// Click Print PDF
				click(MIGNG_NewQuote.MIGNG_printPDF, "Print PDF");
				Thread.sleep(5000);
				validatePropertyInfoFromPDFUsingClipboard(propertyStreet3, propertyCity3, "Kentucky", propertyZipCode3);
			}
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			
			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("QuotePDF_VerifyPropertyKY");
	}
}
