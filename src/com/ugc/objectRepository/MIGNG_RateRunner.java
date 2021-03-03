package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_RateRunner {

	public static By MIGNG_rateRunnerLogo		= By.xpath("//font[@class='raterunnerlogo']");
	public static By MIGNG_rrMiCompare			= By.xpath("//div[@id='sb4off']//a[contains(text(),'Rate Runner - MI Compare')]");
	public static By MIGNG_rrGetQuote			= By.xpath("//div[@id='sb4on']//a[contains(text(),'Get a quote')]");
	public static By MIGNG_quoteNumber			= By.xpath("//input[@id='ugQuoteNumber']");
	public static By MIGNG_btnFindQuote			= By.xpath("//button[@name='btnFindMyQuote']");

	public static By MIGNG_spinSearching		= By.xpath("//div[@id='searching']");
	public static By MIGNG_lblQuoteNo			= By.xpath("//span[@id='quoteNo']");
	public static By MIGNG_lblName				= By.xpath("//span[contains(text(),'Name:')]/following-sibling::span[@id='Span1']");
	public static By MIGNG_lblLoan				= By.xpath("//span[contains(text(),'Loan #:')]/following-sibling::span[@id='Span2']");

	public static By MIGNG_optPaymentGrid		= By.xpath("//table[@id='quotes']/tbody/tr/td");
//	public static By MIGNG_btnFindQuote			= By.xpath("//table[@id='quotes']/tbody/tr/td");
	public static By MIGNG_paymentOpt1			= By.xpath("//table[@id='quotes']//td[@id='quote1']");
	public static By MIGNG_paymentOpt2			= By.xpath("//table[@id='quotes']//td[@id='quote2']");
	public static By MIGNG_paymentOpt3			= By.xpath("//table[@id='quotes']//td[@id='quote3']");
	public static By MIGNG_paymentOpt4			= By.xpath("//table[@id='quotes']//td[@id='quote4']");
	public static By MIGNG_btnPrintQuote		= By.xpath("//span[contains(text(),'Print Quote')]");
	public static By MIGNG_btnModifyQuote		= By.xpath("//span[contains(text(),'Modify Quote')]");

	public static By MIGNG_lblpaymentOpt11		= By.xpath("//table[@id='quotes']//td[@id='quote1']//b[1]");
	public static By MIGNG_lblpaymentOpt12		= By.xpath("//table[@id='quotes']//td[@id='quote1']//b[2]");
	public static By MIGNG_lblpaymentOpt13		= By.xpath("//table[@id='quotes']//td[@id='quote1']//b[3]");
	public static By MIGNG_lblpaymentOpt14		= By.xpath("//table[@id='quotes']//td[@id='quote1']//b[4]");
	public static By MIGNG_lblpaymentOpt120		= By.xpath("//table[@id='quotes']//td[@id='quote1']//b[20]");
	
	public static By MIGNG_affordableHousingY	= By.xpath("//input[@id='affordableHousing1']");
	public static By MIGNG_affordableHousingN	= By.xpath("//input[@id='affordableHousing2']");
	public static By MIGNG_affordableHousingU	= By.xpath("//input[@id='affordableHousing3']");
	public static By MIGNG_originationType		= By.xpath("//select[@id='thirdPartyOriginator']");
	public static By MIGNG_propertyType			= By.xpath("//select[@id='propertyType']");
	public static By MIGNG_selfEmployedUn		= By.xpath("//td[@id='tblBorrower1']//input[@id='selfemployedun']");
	public static By MIGNG_bankruptcyUn			= By.xpath("//td[@id='tblBorrower1']//input[@id='bankruptcyun']	");
	public static By MIGNG_btnGetQuote			= By.xpath("//button[@name='btnGetQuote' and @type='submit']/span");
	public static By MIGNG_spinCalculating		= By.xpath("//div[@id='calculating']");
}
