package com.ugc.scripts;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class QuotePdf_ExistingQuote extends BusinessFunctions{
	@Test(dataProvider = "get_TestData")
	public void Quote_Pdf_Existing_Quote (String borrowerLastName,
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
			String creditScore2) throws Throwable {

		String parentWindow = null;
		Set<String> handles = null;
		String eligiblePaymentType = "";
		String eligiblePaymentOption = "";

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			// successful RR quote, and views/opens the quote.
			openExistingQuote();

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present", "Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present", "Resubmit button is not present");
			}

			// Edit Quote
			if (fillNewQuoteForm(borrowerLastName, lenderLoanNumber,
					baseLoanAmount, amortizationTerm, amortizationType,
					interestRate, ltv, cltv, loanPurpose, buyDown,
					affordableHousing, corporateRelocation, auSystem,
					auDecision, originationType, propertyZipCode,
					propertyType, occupancy, hazardInsurance,
					realEstateTaxes, hoaFloodOther, occupySubjectProperty,
					selfEmployed, monthlyIncome, otherMonthlyPayment,
					creditScore, priorBankruptcy, miCoverage,
					miPaymentType, miPaymentOption, financedPremium,
					upFrontPremium, postPay, quoteType)) {
				SuccessReport("Edit Quote", "Quote edited successfully");
			} else {
				failureReport("Edit Quote", "Unable to Edit Quote");
			}

			// Click on Resubmit button
			click(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit");

			// Validate message after re-submitting application
			validateSubmitQuote("");

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");

			validateLenderDetailsInQuotePDF(quoteNumber, borrowerLastName,
					lenderLoanNumber, baseLoanAmount, amortizationTerm,
					amortizationType, interestRate, ltv, cltv,
					loanPurpose, buyDown, affordableHousing,
					corporateRelocation, auSystem, auDecision,
					originationType, propertyZipCode, propertyType,
					occupancy, hazardInsurance, realEstateTaxes,
					hoaFloodOther, occupySubjectProperty, selfEmployed,
					monthlyIncome, otherMonthlyPayment, creditScore2,
					priorBankruptcy, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upFrontPremium,
					postPay);

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
					monthlyIncome, otherMonthlyPayment, creditScore2,
					priorBankruptcy, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upFrontPremium,
					postPay);

			// Edit Quote
			if (fillNewQuoteForm(borrowerLastName, lenderLoanNumber,
					baseLoanAmount, amortizationTerm, amortizationType,
					interestRate, ltv, cltv, loanPurpose, buyDown,
					affordableHousing, corporateRelocation, auSystem,
					auDecision, originationType, propertyZipCode,
					propertyType, occupancy, hazardInsurance,
					realEstateTaxes, hoaFloodOther, occupySubjectProperty,
					selfEmployed, monthlyIncome, otherMonthlyPayment,
					creditScore2, priorBankruptcy, miCoverage,
					miPaymentType, miPaymentOption, financedPremium,
					upFrontPremium, postPay, quoteType)) {
				SuccessReport("Edit Quote", "Quote edited successfully");
			} else {
				failureReport("Edit Quote", "Unable to Edit Quote");
			}

			// Click on Resubmit button
			click(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit");

			// Validate message after re-submitting application
			validateSubmitQuote("");

			quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,	"Quote Number");

			validateLenderDetailsInQuotePDF(quoteNumber, borrowerLastName,
					lenderLoanNumber, baseLoanAmount, amortizationTerm,
					amortizationType, interestRate, ltv, cltv,
					loanPurpose, buyDown, affordableHousing,
					corporateRelocation, auSystem, auDecision,
					originationType, propertyZipCode, propertyType,
					occupancy, hazardInsurance, realEstateTaxes,
					hoaFloodOther, occupySubjectProperty, selfEmployed,
					monthlyIncome, otherMonthlyPayment, creditScore2,
					priorBankruptcy, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upFrontPremium,
					postPay);
			
			parentWindow = driver.getWindowHandle();
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
			handles = driver.getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					driver.switchTo().window(windowHandle);
					//	Search Quote in Documentum
					searchRateQuoteInDocumentum(quoteNumber);
					// close the child window
					driver.close();
					Thread.sleep(1000);
				}
			}
			// switch back to old window
			driver.switchTo().window(parentWindow);
			Thread.sleep(1000);
			logoutMIGNG();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("QuotePDF_Existing");
	}	
}
