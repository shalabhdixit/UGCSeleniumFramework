package com.ugc.objectRepository;

import org.openqa.selenium.By;

public class MIGNG_NewApplication {
	public static By MIGNG_secureQuoteNumber = By.xpath("//input[@id='secureQuoteNumber']");
	
	public static By MIGNG_borrowerFirstName = By.xpath("//input[@id='borrowerFirstName']");
	public static By MIGNG_borrowerLastName = By.xpath("//input[@id='borrowerLastName']");
	
	public static By MIGNG_affordableHousingYes = By.xpath("//input[@id='affordableHousing0']");
	public static By MIGNG_affordableHousingNo = By.xpath("//input[@id='affordableHousing1']");
	
	public static By MIGNG_corporateRelocationYes = By.xpath("//input[@id='corporateRelocation0']");
	public static By MIGNG_corporateRelocationNo = By.xpath("//input[@id='corporateRelocation1']");
	
	public static By MIGNG_originationType = By.xpath("//select[@id='originationType']");
	
	public static By MIGNG_miCoverage = By.xpath("//input[@id='miCoverage']");
	
	public static By MIGNG_miPaymentTypeBP = By.xpath("//input[@id='miPaymentType0']");
	public static By MIGNG_miPaymentTypeLP = By.xpath("//input[@id='miPaymentType1']");
	
	public static By MIGNG_miPaymentOption = By.xpath("//select[@id='miPaymentOption']");
	
	public static By MIGNG_financedPremiumYes	 = By.xpath("//input[@id='financedPremium0']");
	public static By MIGNG_financedPremiumNo	 = By.xpath("//input[@id='financedPremium1']");
	
	public static By MIGNG_postPayYes	 = By.xpath("//input[@id='postPay0']");
	public static By MIGNG_postPayNo	 = By.xpath("//input[@id='postPay1']");

	public static By MIGNG_upfrontPremium	 = By.xpath("//select[@id='upfrontPremium']");
	
	public static By MIGNG_thisSubmissionIsForMI = By.xpath("//input[@id='submissionReason0']");
	public static By MIGNG_thisSubmissionIsForMIC = By.xpath("//input[@id='submissionReason1']");
	
	public static By MIGNG_specialHandlingInstructions = By.xpath("//textarea[@id='specialHandlingInstructions']");
	
	public static By MIGNG_eMail	 = By.xpath("//input[@id='submitterEmail']");
	public static By MIGNG_phone	 = By.xpath("//input[@id='submitterPhone']");
	public static By MIGNG_fax = By.xpath("//input[@id='submitterFax']");
	
//	public static By MIGNG_fileUploadff = By.xpath("//input[@id='fileUploads']");
//	public static By MIGNG_fileUploadcc = By.xpath("//h3[contains(text(),'Documents')]/..//input[@id='fileUploads']");
	public static By MIGNG_fileUploadcc = By.xpath("//a[@class='btn btn-default upload-link fileinput-button']");
	public static By MIGNG_fileUploadff = By.xpath("//h3[contains(text(),'Documents')]/../div/a");
//	public static By MIGNG_fileUploadff = By.xpath("//a[contains(text(),'Add Documents')]//input[2]");
	
	public static By MIGNG_fileUploaded = By.xpath("//tr[@class='received-doc-row']/td[1]");
	public static By MIGNG_fileUploadStatus = By.xpath("//tr[@class='received-doc-row']/td[2]");
	
	public static By MIGNG_submit = By.xpath("//input[@type='submit']");
	public static By MIGNG_reSubmit = By.xpath("//input[@name='submitApplication']");
	
	public static By MIGNG_successText = By.xpath("//div[@class='alert alert-success']/span");
	public static By MIGNG_errorText = By.xpath("//div[@class='alert alert-danger']/span");
	
	public static By MIGNG_docUploadValidation = By.xpath("//h3[contains(text(),'Documents')]/../div/div");
}
