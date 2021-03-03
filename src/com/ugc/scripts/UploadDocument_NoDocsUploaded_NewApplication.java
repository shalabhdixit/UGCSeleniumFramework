package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;

public class UploadDocument_NoDocsUploaded_NewApplication extends BusinessFunctions {
	
	@Test(dataProvider = "get_TestData")
	public void Upload_Document_NoDocsUploaded_New_Application (String secureQuoteNumber,
			String borrowerFirstName, String borrowerLastName,
			String affordableHousing, String corporateRelocation,
			String originationType, String miCoverage, String miPaymentType,
			String miPaymentOption, String financedPremium,
			String upfrontPremium, String postPay, String thisSubmission,
			String specialHandlingInstructions, String eMail, String phone,
			String fax, String uploadDocument, String isNegativeTest) throws Throwable {

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (createNewApplication(secureQuoteNumber, borrowerFirstName,
					borrowerLastName, affordableHousing, corporateRelocation,
					originationType, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upfrontPremium, postPay,
					thisSubmission, specialHandlingInstructions, eMail, phone,
					fax, uploadDocument, isNegativeTest)) {
				SuccessReport("Create New Application without Docs Upload",
						"Unable to Create New Application");
			} else {
				failureReport("Create New Application without Docs Upload",
						"New Application created successfully");
			}

			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("UD_NoDocsUpload_NewApp");
	}
}
