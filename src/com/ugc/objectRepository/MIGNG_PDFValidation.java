package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_PDFValidation {
		
	public static By quoteNumber = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Quote Number')]/following-sibling::div[1]");
	public static By summaryProductType = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[5]");
	public static By summaryMICoverage = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[6]");
	public static By summaryPremium = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Premium')]/following-sibling::div[1]");
	public static By summaryBaseLoanAmount = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Base Loan Amount')]/following-sibling::div[1]");
	public static By summaryInsuredLoanAmount = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Insured Loan Amount')]/following-sibling::div[1]");
	public static By summaryBorrowerLastName = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Borrower Last Name')]/following-sibling::div[1]");
	
	public static By taxUpFrontMIPremiumWithTaxes = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Up-front MI Premium with taxes')]");
	public static By taxUpFrontMIPremiumRateWithTaxes = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Up-front MI Premium Rate % with taxes')]");
	public static By taxMonthlyMIPremiumWithTaxes = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Monthly MI Premium with taxes')]");
	public static By taxMonthlyMIPremiumRateWithTaxes = By
			.xpath("//div[@id='pageContainer1']/div[2]/div[contains(text(),'Monthly MI Premium Rate % with taxes')]");

	// Page 2 validations
	public static By baseLoanAmount = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Base Loan Amount')]/following-sibling::div[1]");
	public static By loanPurpose = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Loan Purpose')]/following-sibling::div[1]");
	public static By amortizationTerm = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Amortization Term')]/following-sibling::div[1]");
	public static By amortizationType = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Amortization Type')]/following-sibling::div[1]");
	public static By ltv = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'LTV')]/following-sibling::div[1]");
	public static By cltv = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'CLTV')]/following-sibling::div[1]");
	public static By interestRate = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Interest Rate')]/following-sibling::div[1]");
	public static By buyDown = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Buydown')]/following-sibling::div[1]");
	public static By corporateRelocation = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Corporate Relocation')]/following-sibling::div[1]");
	public static By auSystem = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'AU System')]/following-sibling::div[1]");
	public static By originationType = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Origination Type')]/following-sibling::div[1]");
	public static By auDecision = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'AU Decision')]/following-sibling::div[1]");
	public static By affordableHousing = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Affordable Housing')]/following-sibling::div[1]");
	public static By housingFinanceAgency = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Housing Finance Agency')]/following-sibling::div[1]");
	
	public static By propertyStreet = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Property Address')]/following-sibling::div[1]");
	public static By propertyCity = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Property City')]/following-sibling::div[1]");
	public static By propertyZipCode = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Property ZIP Code')]/following-sibling::div[1]");
	public static By propertyType = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Property Type')]/following-sibling::div[1]");
	public static By propertyState = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Property State')]/following-sibling::div[1]");

	public static By occupancy = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Occupancy')]/following-sibling::div[1]");
	public static By hazardInsurance = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Hazard Insurance')]/following-sibling::div[1]");
	public static By selfEmployed = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Self-Employed')]/following-sibling::div[1]");
	public static By monthlyIncome = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Monthly Income')]/following-sibling::div[1]");
	public static By realEstateTaxes = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Real Estate Taxes')]/following-sibling::div[1]");
	public static By hoaFloodOther = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'HOA, Flood, Other')]/following-sibling::div[1]");
	public static By otherMonthlyPayments = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'All Other Monthly Payments')]/following-sibling::div[1]");
	public static By creditScore = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Credit Score')]/following-sibling::div[1]");
	public static By priorBankruptcy = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Prior Bankruptcy')]/following-sibling::div[1]");
	public static By miCoverage = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'MI Coverage')]/following-sibling::div[1]");
	public static By occupySubjectProperty = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Occupy Subject Property')]/following-sibling::div[1]");
	public static By miPaymentType = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'MI Payment Type')]/following-sibling::div[1]");
	public static By miPaymentOption = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'MI Payment Option')]/following-sibling::div[1]");

	public static By submittingBranchId = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Submitting Branch Id')]/following-sibling::div[1]");
	public static By submittingBranchName = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Submitting Branch Name')]/following-sibling::div[1]");
	public static By submittingBranch = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Submitting Branch')]/following-sibling::div[1]");
	public static By addressLender = By
			.xpath("//div[@id='pageContainer2']/div[2]/div[contains(text(),'Address')]/following-sibling::div[1]");

	
	
}
