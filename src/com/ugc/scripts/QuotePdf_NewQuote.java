package com.ugc.scripts;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class QuotePdf_NewQuote extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Quote_Pdf_New_Quote (String borrowerLastName,
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
			String financedPremium, String upFrontPremium, String postPay, String quoteType,
			String creditScore2, String miPaymentType2, String miPaymentOption2,
			String financedPremium2, String upFrontPremium2) throws Throwable {

		String eligiblePaymentType = "";
		String eligiblePaymentOption = "";

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
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
				SuccessReport("Verify Resubmit button is present",
						"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",
						"Resubmit button is not present");
			}

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");

			validateLenderDetailsInQuotePDF(quoteNumber, borrowerLastName,
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
					postPay);

			searchRateQuoteInDocumentumInNewBrowser(quoteNumber);
			
			eligiblePaymentType = driver.findElement(MIGNG_NewQuote.MIGNG_eligiblePaymentType).getText();
			eligiblePaymentOption = driver.findElement(MIGNG_NewQuote.MIGNG_eligiblePaymentOption).getText();
			if(!eligiblePaymentType.equalsIgnoreCase("") && !eligiblePaymentOption.equalsIgnoreCase("")){
				SuccessReport("Validate eligible Payment Type & Payment Option", "Eligible Payment Type : " + eligiblePaymentType + " ; Eligible Payment Option : " + eligiblePaymentOption);
			}else{
				failureReport("Validate eligible Payment Type & Payment Option", "Eligible Payment Type/Option could not be validated");
			}
			
			click(MIGNG_NewQuote.MIGNG_eligibleSelect, "Eligible Payment Option");
			Thread.sleep(3000);
			// Click on Resubmit button
			click(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit");
			Thread.sleep(3000);
			if(isElementPresent(MIGNG_NewQuote.MIGNG_spinner, "Spinner")){
				SuccessReport("Quote in progress spinner", "Quote in progress verified");
				waitForInVisibilityOfElement(MIGNG_NewQuote.MIGNG_spinner, "Spinner");
			}

			miPaymentType = eligiblePaymentType;
			miPaymentOption = eligiblePaymentOption;
			
			validateLenderDetailsInQuotePDF(quoteNumber, borrowerLastName,
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
					postPay);
			
			searchRateQuoteInDocumentumInNewBrowser(quoteNumber);

			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);

			//	create a new quote with product inelegible
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
					otherMonthlyPayment, creditScore2, priorBankruptcy,
					miCoverage, miPaymentType2, miPaymentOption2,
					financedPremium2, upFrontPremium2, postPay, "");

			// Click on Get Quote button
			click(MIGNG_NewQuote.MIGNG_getQuote, "Get Quote");

			if(isElementPresent(MIGNG_NewQuote.MIGNG_spinner, "Spinner")){
				SuccessReport("Quote in progress spinner", "Quote in progress verified");
				waitForInVisibilityOfElement(MIGNG_NewQuote.MIGNG_spinner, "Spinner");
			}
			
			Thread.sleep(500);
			quoteNumber = driver.findElement(MIGNG_NewQuote.MIGNG_quoteNumber).getText().trim();
			Thread.sleep(500);
			if (!quoteNumber.equals("")) {
				SuccessReport("Capture Quote Number", "Quote Number : " + quoteNumber);
			} else {
				failureReport("Capture Quote Number", "Quote Number could not found. " + quoteNumber);
			}
			
//			if (driver.findElement(MIGNG_NewQuote.MIGNG_printPDF).isDisplayed()) {
//				failureReport("Verify presence of Print PDF link", "Print PDF link is present");
//			} else {
//				SuccessReport("Verify presence of Print PDF link", "Print PDF link is not present");
//			}

			try{
				// Verify Payment Comparison grid is displayed
				if (driver.findElement(MIGNG_NewQuote.MIGNG_gridPaymentComparison).isDisplayed()) {
					failureReport("Verify Payment Comparison grid is not displayed", "Payment Comparison grid is displayed");
				}
			}catch(NoSuchElementException e){
				// Do nothing
				SuccessReport("Verify Payment Comparison grid is not displayed", "Payment Comparison grid is not displayed");
			}
			
			logoutMIGNG();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("QuotePDF_New");
	}
}