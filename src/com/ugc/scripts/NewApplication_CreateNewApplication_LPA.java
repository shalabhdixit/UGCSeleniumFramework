package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;

public class NewApplication_CreateNewApplication_LPA extends BusinessFunctions {

	@Test(dataProvider = "get_TestData")
	public void New_Application_Create_New_Application_LPA(String secureQuoteNumber,
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
				SuccessReport("Login MIGuide NG", "Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (createNewApplication(secureQuoteNumber, borrowerFirstName,
					borrowerLastName, affordableHousing, corporateRelocation,
					originationType, miCoverage, miPaymentType,
					miPaymentOption, financedPremium, upfrontPremium, postPay,
					thisSubmission, specialHandlingInstructions, eMail, phone,
					fax, uploadDocument, isNegativeTest)) {
				SuccessReport("Create New Application", "New Application created successfully");
			} else {
				failureReport("Create New Application", "Unable to Create New Application");
			}

			logoutMIGNG();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("CreateNewApplication_LPA");
	}
}