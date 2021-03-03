package com.ugc.scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

@SuppressWarnings("unused")
public class Pipeline_Application_Submitted_Withquote extends BusinessFunctions{
	@Test(dataProvider = "get_TestData")
	public void Pipeline_Application_Submitted_With_Quote (String borrowerLastName, String lenderLoanNumber, 
			String baseLoanAmount, String amortizationTerm, String amortizationType,
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
			String financedPremium, String upFrontPremium, String postPay, String quoteType,
			String borrowerFirstName, String upfrontPremium, String thisSubmission,
			String specialHandlingInstructions, String eMail, String phone,
			String fax, String uploadDocument, String isNegativeTest)
					throws Throwable {
		
		String quoteNumber = "";

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			//	Create New Quote
			click(MIGNG_HomePage.MIGNG_headerNewQuote, "New Quote");
			Thread.sleep(2000);
			assertText(MIGNG_NewQuote.MIGNG_quoteHeader, "New Quote");

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


			if(isElementPresent(MIGNG_NewQuote.MIGNG_spinner, "Spinner")){
				SuccessReport("Quote in progress spinner", "Quote in progress verified");
				waitForInVisibilityOfElement(MIGNG_NewQuote.MIGNG_spinner, "Spinner");
			}else{
				failureReport("Quote in progress spinner", "Quote in progress could not be verified");
			}
			
			Thread.sleep(500);
			quoteNumber = driver.findElement(MIGNG_NewQuote.MIGNG_quoteNumber).getText().trim();
			Thread.sleep(500);
			if (!quoteNumber.equals("")) {
				SuccessReport("Capture Quote Number", "Quote Number : " + quoteNumber);
			} else {
				failureReport("Capture Quote Number", "Quote Number could not found. " + quoteNumber);
			}

			if (createNewApplication(quoteNumber, borrowerFirstName,
					borrowerLastName, affordableHousing, corporateRelocation,
					originationType, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upfrontPremium, postPay,
					thisSubmission, specialHandlingInstructions, eMail, phone,
					fax, uploadDocument, isNegativeTest)) {
				SuccessReport("Create New Application", "New Application created successfully");
			} else {
				failureReport("Create New Application", "Unable to Create New Application");
			}			
			
			validatePipeline();
			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("Pipeline_001");
	}
	
}
