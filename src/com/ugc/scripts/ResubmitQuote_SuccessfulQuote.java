package com.ugc.scripts;

import java.util.Set;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;

public class ResubmitQuote_SuccessfulQuote extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Resubmit_Quote_Successful_Quote(String borrowerLastName,
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

			String borrowerLastName2, String lenderLoanNumber2,
			String baseLoanAmount2, String amortizationTerm2,
			String amortizationType2, String interestRate2, String ltv2,
			String cltv2, String loanPurpose2, String buyDown2,
			String affordableHousing2, String corporateRelocation2,
			String auSystem2, String auDecision2, String originationType2,
			String propertyZipCode2, String propertyType2, String occupancy2,
			String hazardInsurance2, String realEstateTaxes2,
			String hoaFloodOther2, String occupySubjectProperty2,
			String selfEmployed2, String monthlyIncome2,
			String otherMonthlyPayment2, String creditScore2,
			String priorBankruptcy2, String miCoverage2, String miPaymentType2,
			String miPaymentOption2, String financedPremium2,
			String upFrontPremium2, String postPay2,

			String borrowerLastName3, String lenderLoanNumber3,
			String baseLoanAmount3, String amortizationTerm3,
			String amortizationType3, String interestRate3, String ltv3,
			String cltv3, String loanPurpose3, String buyDown3,
			String affordableHousing3, String corporateRelocation3,
			String auSystem3, String auDecision3, String originationType3,
			String propertyZipCode3, String propertyType3, String occupancy3,
			String hazardInsurance3, String realEstateTaxes3,
			String hoaFloodOther3, String occupySubjectProperty3,
			String selfEmployed3, String monthlyIncome3,
			String otherMonthlyPayment3, String creditScore3,
			String priorBankruptcy3, String miCoverage3, String miPaymentType3,
			String miPaymentOption3, String financedPremium3,
			String upFrontPremium3, String postPay3) throws Throwable {

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
					financedPremium, upFrontPremium, postPay, "BPM")) {
				SuccessReport("Create New Quote",
						"New Quote created successfully");
			} else {
				failureReport("Create New Quote", "Unable to Create New Quote");
			}

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present",	"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",	"Resubmit button is not present");
			}

			// Edit Quote
			if (fillNewQuoteForm(borrowerLastName2, lenderLoanNumber2,
					baseLoanAmount2, amortizationTerm2, amortizationType2,
					interestRate2, ltv2, cltv2, loanPurpose2, buyDown2,
					affordableHousing2, corporateRelocation2, auSystem2,
					auDecision2, originationType2, propertyZipCode2,
					propertyType2, occupancy2, hazardInsurance2,
					realEstateTaxes2, hoaFloodOther2, occupySubjectProperty2,
					selfEmployed2, monthlyIncome2, otherMonthlyPayment2,
					creditScore2, priorBankruptcy2, miCoverage2,
					miPaymentType2, miPaymentOption2, financedPremium2,
					upFrontPremium2, postPay2, "BPM")) {
				SuccessReport("Edit Quote", "Quote edited successfully");
			} else {
				failureReport("Edit Quote", "Unable to Edit Quote");
			}

			// Click on Resubmit button
			click(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit");

			// Validate message after re-submitting application
			validateSubmitQuote("BPM");

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber,
					"Quote Number");

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
									postPay, borrowerLastName2, lenderLoanNumber2,
									baseLoanAmount2, amortizationTerm2,
									amortizationType2, interestRate2, ltv2, cltv2,
									loanPurpose2, buyDown2, affordableHousing2,
									corporateRelocation2, auSystem2, auDecision2,
									originationType2, propertyZipCode2, propertyType2,
									occupancy2, hazardInsurance2, realEstateTaxes2,
									hoaFloodOther2, occupySubjectProperty2,
									selfEmployed2, monthlyIncome2,
									otherMonthlyPayment2, creditScore2,
									priorBankruptcy2, miCoverage2, miPaymentType2,
									miPaymentOption2, financedPremium2,
									upFrontPremium2, postPay2);
							
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
			
			borrowerLastName 		= renewValues(borrowerLastName, borrowerLastName2);
			lenderLoanNumber 		= renewValues(lenderLoanNumber, lenderLoanNumber2);
			baseLoanAmount 			= renewValues(baseLoanAmount, baseLoanAmount2);
			amortizationTerm 		= renewValues(amortizationTerm, amortizationTerm2);
			amortizationType 		= renewValues(amortizationType, amortizationType2);
			interestRate 			= renewValues(interestRate, interestRate2);
			ltv 					= renewValues(ltv, ltv2);
			cltv 					= renewValues(cltv, cltv2);
			loanPurpose 			= renewValues(loanPurpose, loanPurpose2);
			buyDown 				= renewValues(buyDown, buyDown2);
			affordableHousing 		= renewValues(affordableHousing, affordableHousing2);
			corporateRelocation 	= renewValues(corporateRelocation, corporateRelocation2);
			auSystem 				= renewValues(auSystem, auSystem2);
			auDecision 				= renewValues(auDecision, auDecision2);
			originationType 		= renewValues(originationType, originationType2);
			propertyZipCode 		= renewValues(propertyZipCode, propertyZipCode2);
			propertyType 			= renewValues(propertyType, propertyType2);
			occupancy 				= renewValues(occupancy, occupancy2);
			hazardInsurance 		= renewValues(hazardInsurance, hazardInsurance2);
			realEstateTaxes 		= renewValues(realEstateTaxes, realEstateTaxes2);
			hoaFloodOther 			= renewValues(hoaFloodOther, hoaFloodOther2);
			occupySubjectProperty 	= renewValues(occupySubjectProperty, occupySubjectProperty2);
			selfEmployed 			= renewValues(selfEmployed, selfEmployed2);
			monthlyIncome 			= renewValues(monthlyIncome, monthlyIncome2);
			otherMonthlyPayment 	= renewValues(otherMonthlyPayment, otherMonthlyPayment2);
			priorBankruptcy 		= renewValues(priorBankruptcy, priorBankruptcy2);
			miCoverage 				= renewValues(miCoverage, miCoverage2);
			miPaymentType 			= renewValues(miPaymentType, miPaymentType2);
			miPaymentOption 		= renewValues(miPaymentOption, miPaymentOption2);
			financedPremium 		= renewValues(financedPremium, financedPremium2);
			upFrontPremium 			= renewValues(upFrontPremium, upFrontPremium2);
			postPay 				= renewValues(postPay, postPay2);

			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);
			// successful RR quote, and views/opens the quote.
			openExistingQuote(quoteNumber);

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present",
						"Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present",
						"Resubmit button is not present");
			}

			// Edit Quote
			if (fillNewQuoteForm(borrowerLastName3, lenderLoanNumber3,
					baseLoanAmount3, amortizationTerm3, amortizationType3,
					interestRate3, ltv3, cltv3, loanPurpose3, buyDown3,
					affordableHousing3, corporateRelocation3, auSystem3,
					auDecision3, originationType3, propertyZipCode3,
					propertyType3, occupancy3, hazardInsurance3,
					realEstateTaxes3, hoaFloodOther3, occupySubjectProperty3,
					selfEmployed3, monthlyIncome3, otherMonthlyPayment3,
					creditScore3, priorBankruptcy3, miCoverage3,
					miPaymentType3, miPaymentOption3, financedPremium3,
					upFrontPremium3, postPay3, "BPA")) {
				SuccessReport("Edit Quote", "Quote edited successfully");
			} else {
				failureReport("Edit Quote", "Unable to Edit Quote");
			}

			// Click on Resubmit button
			click(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit");

			// Validate message after re-submitting application
			validateSubmitQuote("BPA");

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
									postPay, borrowerLastName3, lenderLoanNumber3,
									baseLoanAmount3, amortizationTerm3,
									amortizationType3, interestRate3, ltv3, cltv3,
									loanPurpose3, buyDown3, affordableHousing3,
									corporateRelocation3, auSystem3, auDecision3,
									originationType3, propertyZipCode3, propertyType3,
									occupancy3, hazardInsurance3, realEstateTaxes3,
									hoaFloodOther3, occupySubjectProperty3,
									selfEmployed3, monthlyIncome3,
									otherMonthlyPayment3, creditScore3,
									priorBankruptcy3, miCoverage3, miPaymentType3,
									miPaymentOption3, financedPremium3,
									upFrontPremium3, postPay3);
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
			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("ResubmitQuote_Successful");
	}
}
