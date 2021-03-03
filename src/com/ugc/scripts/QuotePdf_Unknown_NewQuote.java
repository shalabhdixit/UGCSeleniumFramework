package com.ugc.scripts;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;

import com.cigniti.utilities.Data_Provider;
import com.cigniti.utilities.UploadFileRobot;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;
import com.ugc.objectRepository.MIGNG_RateRunner;

@SuppressWarnings("unused")
public class QuotePdf_Unknown_NewQuote extends BusinessFunctions{
	@Test(dataProvider = "get_TestData")
	public void Quote_Pdf_Unknown_New_Quote (String borrowerLastName,
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
			String financedPremium, String upFrontPremium, String postPay, String quoteType) throws Throwable {

		String parentWindow = null;
		Set<String> handles = null;

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

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
				SuccessReport("Create New Quote", "New Quote created successfully");
			} else {
				failureReport("Create New Quote", "Unable to Create New Quote");
			}

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present",	"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",	"Resubmit button is not present");
			}

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");

//			browser = configProps.getProperty("browserType");
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
							validatePDFUnknown(quoteNumber);
							
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
			logoutMIGNG();

			// Login to RateRunner application
			logInMIG("RR_userName", "RR_password");
			verifyHomePageRR();
		
			//	Click on Rate Runner
			waitForVisibilityOfElement(MIGNG_RateRunner.MIGNG_rrMiCompare, "Rate Runner");
			click(MIGNG_RateRunner.MIGNG_rrMiCompare, "Rate Runner");
			//	Click on Get A Quote
			waitForVisibilityOfElement(MIGNG_RateRunner.MIGNG_rrGetQuote, "Get Quote");
			click(MIGNG_RateRunner.MIGNG_rrGetQuote, "Get Quote");
			
			// Wait for the availability of Login button on MIGNG login Page
			waitForVisibilityOfElement(MIGNG_RateRunner.MIGNG_btnFindQuote,	"Find Quote button");
			//	Enter quote number to be searched
			type(MIGNG_RateRunner.MIGNG_quoteNumber, quoteNumber, "Quote Number");
			//	Click Find Quote
			click(MIGNG_RateRunner.MIGNG_btnFindQuote, "Find Quote button");

			waitForInVisibilityOfElement(MIGNG_RateRunner.MIGNG_spinSearching, "Searching...");
			validateQuoteInfo_RR();
			
//			browser = configProps.getProperty("browserType");
			if(browser.equalsIgnoreCase("firefox")){
				click(MIGNG_RateRunner.MIGNG_btnPrintQuote, "Print Quote");
				Thread.sleep(10000);
				copyDataFromPdfToClipboardAndValidateUnknown();
			}else{
				informationReport("Validate PDF report", "PDF report validation cannot be done on Chrome & IE");
			}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("QuotePDF_UNK_NewQuote");
	}
}
