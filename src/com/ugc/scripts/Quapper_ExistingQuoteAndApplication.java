package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewApplication;
import com.ugc.objectRepository.MIGNG_NewQuote;
import com.ugc.objectRepository.MIGNG_Validation;

public class Quapper_ExistingQuoteAndApplication extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Quapper_ExistingQuoteAndApplication_ (String borrowerLastName,
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
			String quoteType, String borrowerFirstName, String upfrontPremium,
			String thisSubmission, String specialHandlingInstructions,
			String eMail, String phone, String fax, String uploadDocument,
			String isNegativeTest) throws Throwable {

		String quoteNumber_1 = "";
		String applicationNo_1 = "";
		String successText = "";

		try {

			// Login to MIGNG application
			if (logInMIG("Quapper_userName", "Quapper_password") && verifyHomePageMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			//	Open existing quote
			openExistingQuote();
			
			//	Edit Quote details
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

			quoteNumber_1 = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");			

			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			
			//	Search and Open New Quote
			searchAndOpenQuote(quoteNumber_1);
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			
			//	Create New Application
			openExistingApplication();

			fillNewApplicationForm("", "", "", "", "", "", "", "", "", "", "", "",
					thisSubmission, specialHandlingInstructions, eMail, phone, fax);
			
			// Click on Submit button
			click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");

			successText = getText(MIGNG_NewApplication.MIGNG_successText, "Success Message");
//			System.out.println(successText);
			if (!successText.equals("")	&& successText
							.contains(MIGNG_Validation.msgSuccessApplicationSubmitted)) {				
				applicationNo_1 = successText.replaceAll("[^0-9]", "");
				SuccessReport("Edit Existing Application",	"Existing Application edited successfully");
			} else {
				failureReport("Edit Existing Application",	"Unable to Edit Existing Application");
			}
			
			//	Search and Open New Application
			searchAndOpenApplication(applicationNo_1);
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			logoutMIGNG();

			//	Part 2 of the test case 
			// Login to MIGNG application
			if (logInMIG("MIGNG_userName", "MIGNG_passWord") && verifyHomePageMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			//	search quote and application on Pipeline
			if(searchQuoteOrApplicationFromPipeline("", applicationNo_1, "Coworker", "")){
				SuccessReport("Search Application", "Search successful");
				click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
				Thread.sleep(2000);
			}

			if(!searchQuoteOrApplicationFromPipeline("", applicationNo_1, "Mine", "")){
				SuccessReport("Search Application (Negative)", "Application Not found");
				click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
				Thread.sleep(2000);
			}

			if(searchQuoteOrApplicationFromPipeline("", applicationNo_1, "", "Search")){
				SuccessReport("Search Application", "Search successful");
				click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
				Thread.sleep(2000);
			}
			
			if(searchQuoteOrApplicationFromPipeline(quoteNumber_1, "", "Coworker", "")){
				SuccessReport("Search Quote", "Search successful");
				click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
				Thread.sleep(2000);
			}
			
			if(!searchQuoteOrApplicationFromPipeline(quoteNumber_1, "", "Mine", "")){
				SuccessReport("Search Quote (Negative)", "Quote Not found");
				click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
				Thread.sleep(2000);
			}

			if(searchQuoteOrApplicationFromPipeline(quoteNumber_1, "", "", "Search")){
				SuccessReport("Search Quote", "Search successful");
				click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
				Thread.sleep(2000);
			}
			
			logoutMIGNG();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("Quapper_001");
	
	}
}
