package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewApplication;

public class UploadDocument_ErrorDuringUploadBigFile_NewApplication extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Upload_Document_Error_During_UploadBigFile_New_Application (String secureQuoteNumber,
			String borrowerFirstName, String borrowerLastName,
			String affordableHousing, String corporateRelocation,
			String originationType, String miCoverage, String miPaymentType,
			String miPaymentOption, String financedPremium,
			String upfrontPremium, String postPay, String thisSubmission,
			String specialHandlingInstructions, String eMail, String phone,
			String fax, String uploadDocument, String isNegativeTest, String uploadBigFile, String duplicateFile,
			String multipleFile, String uploadDocument2) throws Throwable {

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
			
			if (!uploadDocument.equals("")) {
				String[] files = uploadDocument.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}

			if (!uploadBigFile.equals("")) {
				String[] files = uploadBigFile.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}

			if (!duplicateFile.equals("")) {
				// call upload document method
				uploadDocument(duplicateFile, 0);		//	0 for duplicate file validation
			}
						
			if (!multipleFile.equals("")) {
				String[] files = multipleFile.split(";");
				// call upload document method
				uploadDocument(files[0], 1);	// upload big file
				uploadDocument(files[1], 0);	// upload duplicate file
			}

			if (!uploadDocument2.equals("")) {
				String[] files = uploadDocument2.split(";");
				for (int i = 0; i < files.length; i++) {
					// call upload document method
					uploadDocument(files[i], i + 1);
				}
			}
			
			// Click on Submit button
			click(MIGNG_NewApplication.MIGNG_submit, "Submit Button");
			// Validate message after submitting application
			validateSubmitApplication(isNegativeTest);
			
			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("UD_BigFile_NewApp");
	}	
}
