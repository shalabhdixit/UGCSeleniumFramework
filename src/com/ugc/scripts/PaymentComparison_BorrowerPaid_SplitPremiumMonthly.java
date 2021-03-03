package com.ugc.scripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewQuote;
import com.ugc.objectRepository.MIGNG_RAPidLink;

public class PaymentComparison_BorrowerPaid_SplitPremiumMonthly extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void PaymentComparison_BorrowerPaid_SplitPremiumMonthly_(String borrowerLastName, String lenderLoanNumber,
			String baseLoanAmount, String amortizationTerm, String amortizationType, String interestRate, String ltv,
			String cltv, String loanPurpose, String buyDown, String affordableHousing, String corporateRelocation,
			String auSystem, String auDecision, String originationType, String propertyZipCode, String propertyType,
			String occupancy, String hazardInsurance, String realEstateTaxes, String hoaFloodOther,
			String occupySubjectProperty, String selfEmployed, String monthlyIncome, String otherMonthlyPayment,
			String creditScore, String priorBankruptcy, String miCoverage, String miPaymentType, String miPaymentOption,
			String financedPremium, String upFrontPremium, String postPay, String quoteType, String productType1,
			String productOption1, String productType2, String productOption2, String productType3,
			String productOption3, String productType4, String productOption4) throws Throwable {

		String className = "";
		String productType = "";
		String productOption = "";
		String value = "";
		String value2 = "";

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

			fillNewQuoteForm(borrowerLastName, lenderLoanNumber, baseLoanAmount, amortizationTerm, amortizationType,
					interestRate, ltv, cltv, loanPurpose, buyDown, affordableHousing, corporateRelocation, auSystem,
					auDecision, originationType, propertyZipCode, propertyType, occupancy, hazardInsurance,
					realEstateTaxes, hoaFloodOther, occupySubjectProperty, selfEmployed, monthlyIncome,
					otherMonthlyPayment, creditScore, priorBankruptcy, miCoverage, miPaymentType, miPaymentOption,
					financedPremium, upFrontPremium, postPay, quoteType);

			// Click on Get Quote button
			click(MIGNG_NewQuote.MIGNG_getQuote, "Get Quote");

			// Validate message after submitting application
			validateSubmitQuote(quoteType, "FULL");

			String quoteNumber = getText(MIGNG_NewQuote.MIGNG_quoteNumber, "Quote Number");
			if (!quoteNumber.equalsIgnoreCase("")) {
				SuccessReport("Quote Number", "Quote Number: " + quoteNumber + " generated successfully.");
			}

			// Validate first element
			className = driver.findElement(By.xpath("//div[@class='pricing row']/ul/li[1]/div[1]"))
					.getAttribute("class");
			productType = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[1]/div[1]//div[@class='price-title']/h3"))
					.getText();
			productOption = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[1]/div[1]//div[@class='price-title']/h5"))
					.getText();

			if (className.contains("price-unselected") && productType.equalsIgnoreCase(productType1)
					&& productOption.equalsIgnoreCase(productOption1)) {
				SuccessReport("Validate payment comparison grid",
						productType + " is not selected and is in first position");
			} else {
				failureReport("Validate payment comparison grid",
						"First payment option could not be validated. Product Type:" + productType + ", Payment option:"
								+ productOption);
			}

			// Validate second element
			className = driver.findElement(By.xpath("//div[@class='pricing row']/ul/li[2]/div[1]"))
					.getAttribute("class");
			productType = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[2]/div[1]//div[@class='price-title']/h3"))
					.getText();
			productOption = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[2]/div[1]//div[@class='price-title']/h5"))
					.getText();

			if (className.contains("price-unselected") && productType.equalsIgnoreCase(productType2)
					&& productOption.equalsIgnoreCase(productOption2)) {
				SuccessReport("Validate payment comparison grid",
						productType + " is not selected and is in second position");
			} else {
				failureReport("Validate payment comparison grid",
						"First payment option could not be validated. Product Type:" + productType + ", Payment option:"
								+ productOption);
			}

			// Validate third element
			className = driver.findElement(By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]"))
					.getAttribute("class");
			productType = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-title']/h3"))
					.getText();
			productOption = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-title']/h5"))
					.getText();

			if (className.contains("price-selected") && productType.equalsIgnoreCase(productType3)
					&& productOption.equalsIgnoreCase(productOption3)) {
				SuccessReport("Validate payment comparison grid",
						productType + " is selected and is in third position");
			} else {
				failureReport("Validate payment comparison grid",
						"First payment option could not be validated. Product Type:" + productType + ", Payment option:"
								+ productOption);
			}

			value = driver.findElement(MIGNG_NewQuote.MIGNG_premiumAPO3).getText();
			if (!value.equalsIgnoreCase("")) {
				SuccessReport("MI Premium Up-front", "MI Premium Up-front : " + value);
			} else {
				failureReport("MI Premium Up-front", "MI Premium Up-front : " + value);
			}

			value = driver.findElement(MIGNG_NewQuote.MIGNG_premiumBPO3).getText();
			value2 = driver.findElement(MIGNG_NewQuote.MIGNG_premiumCPO3).getText();
			if (!value.equalsIgnoreCase("")) {
				SuccessReport("MI Premium Monthly", "MI Premium Monthly : " + value + " " + value2);
			} else {
				failureReport("MI Premium Monthly", "MI Premium Monthly : " + value + " " + value2);
			}

			value = driver.findElement(MIGNG_NewQuote.MIGNG_premiumPO31).getText();
			if (!value.equalsIgnoreCase("")) {
				SuccessReport("MI Up-front", "MI Up-front : " + value);
			} else {
				failureReport("MI Up-front", "MI Up-front : " + value);
			}

			value = driver.findElement(MIGNG_NewQuote.MIGNG_premiumPO32).getText();
			if (!value.equalsIgnoreCase("")) {
				SuccessReport("MI Rate Up-front", "MI Rate Up-front : " + value);
			} else {
				failureReport("MI Rate Up-front", "MI Rate Up-front : " + value);
			}

			value = driver.findElement(MIGNG_NewQuote.MIGNG_premiumPO33).getText();
			if (!value.equalsIgnoreCase("")) {
				SuccessReport("MI Rate Monthly", "MI Rate Monthly : " + value);
			} else {
				failureReport("MI Rate Monthly", "MI Rate Monthly : " + value);
			}

			value = driver.findElement(MIGNG_NewQuote.MIGNG_premiumPO33).getText();
			if (!value.equalsIgnoreCase("")) {
				SuccessReport("Financed Staus", "Financed Staus" + value);
			} else {
				failureReport("Financed Staus", "Financed Staus : " + value);
			}

			// Validate fourth element
			className = driver.findElement(By.xpath("//div[@class='pricing row']/ul/li[4]/div[1]"))
					.getAttribute("class");
			productType = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[4]/div[1]//div[@class='price-title']/h3"))
					.getText().trim();
			productOption = driver
					.findElement(By.xpath("//div[@class='pricing row']/ul/li[4]/div[1]//div[@class='price-title']/h5"))
					.getText().trim();

			if (className.contains("price-unselected") && productType.equalsIgnoreCase(productType4)
					&& productOption.equalsIgnoreCase(productOption4)) {
				SuccessReport("Validate payment comparison grid",
						productType + " is not selected and is in fourth position");
			} else {
				failureReport("Validate payment comparison grid",
						"First payment option could not be validated. Product Type:" + productType + ", Payment option:"
								+ productOption);
			}

			// The user navigates back to the pipeline, selects an existing
			click(MIGNG_HomePage.MIGNG_headerPipeline, "Pipeline");
			Thread.sleep(2000);

			// open existing quote
			if (openExistingQuote(quoteNumber)) {
				SuccessReport("Open Existing Quote", "Existing Quote " + quoteNumber + " opened successfully");
			} else {
				failureReport("Open Existing Quote", "Existing Quote " + quoteNumber + " failed to open");
			}

			// Verify ResubmitQuote is present
			if (isElementPresent(MIGNG_NewQuote.MIGNG_reSubmit, "ReSubmit")) {
				SuccessReport("Verify Resubmit button is present", "Resubmit button is present");
			} else {
				failureReport("Verify Resubmit button is present", "Resubmit button is not present");
			}

			logoutMIGNG();

			if (logInMIG("RR_userName", "RR_password") & verifyHomePageRR()) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}
			
			if (MIGNG_PaymentQuote(quoteNumber)) {
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (logoutMIG(MIGNG_RAPidLink.MIGNG_RAPidLink_Signout)) {
				SuccessReport("Logout MIGuide NG", "Successfully logged out from MIGuide NG ");
			} else {
				failureReport("Logout MIGuide NG RAPID lINK", "Unable to log out");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("PmtComp_BPSPM");
	}

}
