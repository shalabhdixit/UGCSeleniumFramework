package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewApplication;
import com.ugc.objectRepository.MIGNG_Validation;

public class UploadDocument_RemoveDocumentFromApplication extends
		BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void UploadDocument_RemoveDocumentFromApplication_(
			String secureQuoteNumber, String borrowerFirstName,
			String borrowerLastName, String affordableHousing,
			String corporateRelocation, String originationType,
			String miCoverage, String miPaymentType, String miPaymentOption,
			String financedPremium, String upfrontPremium, String postPay,
			String thisSubmission, String specialHandlingInstructions,
			String eMail, String phone, String fax, String uploadDocument1,
			String isNegativeTest, String uploadDocument2,
			String uploadDocument3) throws Throwable {

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			click(MIGNG_HomePage.MIGNG_headerNewFullFile,
					"New Full-file Application");

			Thread.sleep(3000);
			assertText(MIGNG_HomePage.MIGNG_applicationHeader,
					"New Full-file Application");

			fillNewApplicationForm(secureQuoteNumber, borrowerFirstName,
					borrowerLastName, affordableHousing, corporateRelocation,
					originationType, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upfrontPremium, postPay,
					thisSubmission, specialHandlingInstructions, eMail, phone,
					fax);

			if (!uploadDocument1.equals("")) {
				String[] files = uploadDocument1.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
				removeUploadedDocument();
/*				for (int i = files.length - 1; i >= 0; i--) {
					// call remove document method
					RemoveDocument(files[i], i + 1);
				}*/
			}

			if (!uploadDocument2.equals("")) {
				String[] files = uploadDocument2.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
				removeUploadedDocument();
/*				for (int i = files.length - 1; i >= 0; i--) {
					// call remove document method
					RemoveDocument(files[i], i + 1);
				}*/
			}

			if (!uploadDocument3.equals("")) {
				String[] files = uploadDocument3.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}

			// Click on Submit button
			click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");

			// Validate message after submitting application
			validateSubmitApplication(isNegativeTest);
			// verify success text
			String applicationNo = getText(
					MIGNG_NewApplication.MIGNG_successText, "Success Message")
					.replaceAll("[^0-9]", "");
			openExistingApplication(applicationNo);

			if (!uploadDocument2.equals("")) {
				String[] files = uploadDocument2.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
				removeUploadedDocument();
/*				for (int i = files.length - 1; i >= 0; i--) {
					// call remove document method
					RemoveDocument(files[i], i + 1);
				}*/
			}

			// Click on ReSubmit button
			click(MIGNG_NewApplication.MIGNG_reSubmit, "ReSubmit Button");

			// Wait for the availability of Pipeline table
			waitForVisibilityOfElement(MIGNG_HomePage.MIGNG_pipeline,
					"Pipeline page");

			// verify success text
			String successText = getText(MIGNG_NewApplication.MIGNG_successText, "Success Message");
			if (!successText.equals("")
					&& successText.contains(MIGNG_Validation.msgSuccessApplicationSubmitted)) {
				SuccessReport("New Application Created", "Success Message : " + successText);
				String applicationNum = successText.replaceAll("[^0-9]", "");
				SuccessReport("New Application Created", "Application No : " + applicationNum);
			}

			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("RemoveDocFromApp");
	}
}
