package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_NewQuote {
	public static By MIGNG_quoteHeader = By.xpath("//h1[@class='entity-title'][contains(text(),'New Quote')]");

	public static By MIGNG_borrowerLastName = By.xpath("//input[@id='borrowerLastName']");
	public static By MIGNG_lenderLoanNumber = By.xpath("//input[@id='lenderLoanNumber']");
	public static By MIGNG_baseLoanAmount = By.xpath("//input[@id='baseLoanAmount']");
	public static By MIGNG_amortizationTerm = By.xpath("//input[@id='amortizationTerm']");
	public static By MIGNG_amortizationType = By.xpath("//select[@id='amortizationType']");

	public static By MIGNG_interestRate = By.xpath("//input[@id='interestRate']");
	public static By MIGNG_ltv = By.xpath("//input[@id='ltv']");
	public static By MIGNG_cltv = By.xpath("//input[@id='cltv']");
	public static By MIGNG_loanPurpose = By.xpath("//select[@id='loanPurpose']");
	public static By MIGNG_buyDown	 = By.xpath("//select[@id='buydown']");

	public static By MIGNG_affordableHousingYes	 = By.xpath("//input[@id='affordableHousing0']");
	public static By MIGNG_affordableHousingNo	 = By.xpath("//input[@id='affordableHousing1']");
	public static By MIGNG_affordableHousingUnknown	 = By.xpath("//input[@id='affordableHousing2']");
	
	public static By MIGNG_corporateRelocationYes	 = By.xpath("//input[@id='corporateRelocation0']");
	public static By MIGNG_corporateRelocationNo	 = By.xpath("//input[@id='corporateRelocation1']");
	
	public static By MIGNG_auSystem	 = By.xpath("//select[@id='auSystem']");
	public static By MIGNG_duDecision	 = By.xpath("//select[@id='duDecision']");
	public static By MIGNG_originationType	 = By.xpath("//select[@id='originationType']");
	
	public static By MIGNG_propertyZipCode	 = By.xpath("//input[@id='propertyZipCode']");
	public static By MIGNG_propertyState	 	 = By.xpath("//input[@id='propertyStateInput']");
	public static By MIGNG_propertyStreet	 = By.xpath("//input[@id='propertyStreetAddress']");
	public static By MIGNG_propertyType	 	 = By.xpath("//select[@id='propertyType']");
	
	public static By MIGNG_occupancy	 			= By.xpath("//select[@id='occupancy']");
	public static By MIGNG_hazardInsurance		 	= By.xpath("//input[@id='hazardInsurance']");
	public static By MIGNG_realEstateTaxes		 	= By.xpath("//input[@id='realEstateTaxes']");
	public static By MIGNG_hoaFloodOther	 	 	= By.xpath("//input[@id='hoaFloodOther']");
	
	public static By MIGNG_occupySubjectPropertyYes	= By.xpath("//input[@id='borrowers0.occupySubjectProperty0']");
	public static By MIGNG_occupySubjectPropertyNo	= By.xpath("//input[@id='borrowers0.occupySubjectProperty1']");
	
	public static By MIGNG_selfEmployedYes	 		= By.xpath("//input[@id='borrowers0.selfEmployed0']");
	public static By MIGNG_selfEmployedNo	 		= By.xpath("//input[@id='borrowers0.selfEmployed1']");
	public static By MIGNG_selfEmployedUnknown	 	= By.xpath("//input[@id='borrowers0.selfEmployed2']");
	
	public static By MIGNG_monthlyIncome	 		= By.xpath("//div[@id='monthly-income']/input");
	public static By MIGNG_otherMonthlyPayment	 	= By.xpath("//div[@id='all-other-monthly-payments']/input");
	public static By MIGNG_creditScore	 			= By.xpath("//div[@id='credit-score']/input");
	
	public static By MIGNG_priorBankruptcyYes	 	= By.xpath("//input[@id='borrowers0.priorBankruptcy0']");
	public static By MIGNG_priorBankruptcyNo	 	= By.xpath("//input[@id='borrowers0.priorBankruptcy1']");
	public static By MIGNG_priorBankruptcyUnknown	= By.xpath("//input[@id='borrowers0.priorBankruptcy2']");
	
	public static By MIGNG_addBorrower	 			= By.xpath("//button[@name='addBorrower']");
	public static By MIGNG_miCoverage	 			= By.xpath("//input[@id='miCoverage']");
	public static By MIGNG_miPaymentTypeBP	 		= By.xpath("//input[@id='miPaymentType0']");//	-	Borrower-Paid
	public static By MIGNG_miPaymentTypeLP	 		= By.xpath("//input[@id='miPaymentType1']");//	-	Lender-Paid
	public static By MIGNG_miPaymentOption	 		= By.xpath("//select[@id='miPaymentOption']");

	public static By MIGNG_financedPremiumYes	 	= By.xpath("//input[@id='financedPremium0']");
	public static By MIGNG_financedPremiumNo	 	= By.xpath("//input[@id='financedPremium1']");
	public static By MIGNG_postPayYes	 			= By.xpath("//input[@id='postPay0']");
	public static By MIGNG_postPayNo	 			= By.xpath("//input[@id='postPay1']");
	public static By MIGNG_upfrontPremium	 		= By.xpath("//select[@id='upfrontPremium']");
	public static By MIGNG_getQuote	 				= By.xpath("//input[@name='getQuote' and @type='submit']");

	public static By MIGNG_reSubmit					= By.xpath("//input[@value='Resubmit' and @type='submit']");
	public static By MIGNG_orderFullFile			= By.xpath("//input[@value='Order Full-File']");
	public static By MIGNG_quoteNumber				= By.xpath("//h1[@class='entity-title']");
	public static By MIGNG_printPDF					= By.xpath("//input[@id='print_pdf']");
	
	public static By MIGNG_gridPaymentComparison	= By.xpath("//div[@class='pricing row']");
	public static By MIGNG_optPaymentComparison		= By.xpath("//div[@class='pricing row']/ul/li");

	public static By MIGNG_quotePaymentType			= By.
			xpath("//div[contains(@class, 'price-selected')]/div[@class='price-title']/h3");//		- Borrower-Paid
	public static By MIGNG_quotePaymentOption		= By.
			xpath("//div[contains(@class, 'price-selected')]/div[@class='price-title']/h5");//		- Annual
	public static By MIGNG_quotePremium				= By.
			xpath("//div[contains(@class, 'price-selected')]/div[@class='price-body']//h3");//		- text
	public static By MIGNG_quotePremiumOption		= By.
			xpath("//div[contains(@class, 'price-selected')]/div[@class='price-body']//h3/small");
	
	public static By MIGNG_propertyStateInput		= By.
			xpath("//input[@id='propertyStateInput']");
	public static By MIGNG_firstMortgagePi			= By.
			xpath("//p[@id='firstMortgagePi']");
	
	public static By MIGNG_msgUnsuccessfulQuoteHead	= By.
			xpath("//div[@class='row unsuccessful-message']//h3");
	public static By MIGNG_msgUnsuccessfulQuote		= By.
			xpath("//div[@class='row unsuccessful-message']//h3/following-sibling::ul/li");

	public static By MIGNG_spinner					= By.
			xpath("//div[@class='spinner']");


	public static By MIGNG_ineligiblePayment		= By.
			xpath("//h3[@class='price' and contains(text(),'Ineligible')]");
	public static By MIGNG_ineligiblePaymentType	= By.
			xpath("//h3[@class='price' and contains(text(),'Ineligible')]/ancestor::div[contains(@class,'price-selected')]/div[1]/h3");
	public static By MIGNG_ineligiblePaymentOption	= By.
			xpath("//h3[@class='price' and contains(text(),'Ineligible')]/ancestor::div[contains(@class,'price-selected')]/div[1]/h5");
	public static By MIGNG_eligiblePaymentType		= By.
			xpath("(//div[contains(@class,'price-unselected')])[1]/div[1]/h3");
	public static By MIGNG_eligiblePaymentOption	= By.
			xpath("(//div[contains(@class,'price-unselected')])[1]/div[1]/h5");
	public static By MIGNG_eligibleSelect			= By.
			xpath("(//div[contains(@class,'price-unselected')])[1]/following-sibling::div//input[@value='Select']");

	public static By MIGNG_premiumAPO3 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']//h3");
	public static By MIGNG_premiumBPO3 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']//h4");
	public static By MIGNG_premiumCPO3 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']//h4/small");
	public static By MIGNG_premiumPO31 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']/ul/li[1]");
	public static By MIGNG_premiumPO32 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']/ul/li[2]");
	public static By MIGNG_premiumPO33 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']/ul/li[3]");
	public static By MIGNG_premiumPO34 = By.xpath("//div[@class='pricing row']/ul/li[3]/div[1]//div[@class='price-body']/ul/li[4]");

	
	public static By MIGNG_ChooseFile = By.xpath("//input[@id='fannie-mae-file']");
	public static By MIGNG_CloseButton = By.xpath("//button[@id='closeButton']");
	public static By MIGNG_UploadButton = By.xpath("//button[@id='uploadButton']");
	public static By MIGNG_PreFillFile = By.xpath("//a[contains(text(),'Pre-fill details with a Fannie Mae file')]");
	public static By MIGNG_alartMessage= By.xpath("//div[@role='alert' and @class='alert alert-danger']");
	
	
}
