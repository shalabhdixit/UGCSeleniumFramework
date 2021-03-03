package com.ugc.scripts;

import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class QuotePdf_SelectProduct_Inelegible extends BusinessFunctions{
	@Test(dataProvider = "get_TestData")
	public void QuotePdf_Select_Product_Inelegible (String borrowerLastName,
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
			String quoteType, String ineligiblePmtType, String ineligiblePmtOption) throws Throwable {

		String eligiblePaymentType = "";
		String eligiblePaymentOption = "";
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
				SuccessReport("Validate eligible products", "All other products are eligible and priced successfully.");
			}else{
				failureReport("Validate eligible products", "There are more than one ineligible products");
			}
			
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("QuotePDF_ProductInelegible");
	}
}
