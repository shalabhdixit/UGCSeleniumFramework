package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class PaymentComparison_SwitchPaymentTypeOption_NewQuote extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void PaymentComparison_SwitchPaymentTypeOption_NewQuote_ (String borrowerLastName,
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
			String quoteType, String ineligiblePmtType, String ineligiblePmtOption, String quoteTypeNew) 
					throws Throwable {

		String eligiblePaymentType = "";
		String eligiblePaymentOption = "";

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
			if(!quoteNumber.equalsIgnoreCase("")){
				SuccessReport("Quote Number", "Quote Number: " + quoteNumber + " generated successfully.");
			}
			
			if(driver.findElement(MIGNG_NewQuote.MIGNG_ineligiblePayment).isDisplayed()){
				SuccessReport("Validate Product priced unsuccessfully", "Product priced unsuccessfully");
			}else{
				failureReport("Validate Product priced unsuccessfully", "Product priced successfully");
			}
			
			if(driver.findElement(MIGNG_NewQuote.MIGNG_ineligiblePaymentType).getText().equalsIgnoreCase(ineligiblePmtType)){
				SuccessReport("Validate Ineligible Payment Type", "Ineligible Payment Type validated successfully");
			}else{
				failureReport("Validate Ineligible Payment Type", "Ineligible Payment Type could not be validated");
			}

			if(driver.findElement(MIGNG_NewQuote.MIGNG_ineligiblePaymentOption).getText().equalsIgnoreCase(ineligiblePmtOption)){
				SuccessReport("Validate Ineligible Payment Option", "Ineligible Payment Option validated successfully");
			}else{
				failureReport("Validate Ineligible Payment Option", "Ineligible Payment Option could not be validated");
			}
			
			if((driver.findElements(MIGNG_NewQuote.MIGNG_ineligiblePayment)).size() == 1) {
				SuccessReport("Validate eligible products", "All other products are eligible, priced successfully and available for selection.");
			}else{
				failureReport("Validate eligible products", "There are more than one ineligible products");
			}
			
		
			eligiblePaymentType = driver.findElement(MIGNG_NewQuote.MIGNG_eligiblePaymentType).getText();
			eligiblePaymentOption = driver.findElement(MIGNG_NewQuote.MIGNG_eligiblePaymentOption).getText();
			if(!eligiblePaymentType.equalsIgnoreCase("") && !eligiblePaymentOption.equalsIgnoreCase("")){
				SuccessReport("Validate eligible Payment Type & Payment Option", "Eligible Payment Type : " + eligiblePaymentType + " ; Eligible Payment Option : " + eligiblePaymentOption);
			}else{
				failureReport("Validate eligible Payment Type & Payment Option", "Eligible Payment Type/Option could not be validated");
			}
			
			click(MIGNG_NewQuote.MIGNG_eligibleSelect, "Eligible Payment Option");
			Thread.sleep(3000);
			miPaymentType = eligiblePaymentType;
			miPaymentOption = eligiblePaymentOption;
			
			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(3000);

			// open existing quote
			if(openExistingQuote(quoteNumber)){
				SuccessReport("Open Existing Quote", "Existing Quote " + quoteNumber + " opened successfully");
			}else{
				failureReport("Open Existing Quote", "Existing Quote " + quoteNumber + " failed to open");
			}			

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present",	"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",	"Resubmit button is not present");
			}

			// Validate message after submitting application
			validateSubmitQuote(quoteTypeNew);
			logoutMIGNG();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("PmtComp_Switch_New");
	}

}
