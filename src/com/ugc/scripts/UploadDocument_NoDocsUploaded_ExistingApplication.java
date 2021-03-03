package com.ugc.scripts;

import org.testng.annotations.Test;

import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewApplication;
import com.ugc.objectRepository.MIGNG_Validation;

public class UploadDocument_NoDocsUploaded_ExistingApplication extends
		BusinessFunctions {
	@Test
	public void Upload_Document_NoDocsUploaded_Existing_Application () throws Throwable {

		try {
			// Login to MIGNG application
			if (logInMIGNG()) {
				SuccessReport("Login MIGuide NG",
						"Successfully logged into MIGuide NG");
			} else {
				failureReport("Login MIGuide NG", "Unable to log in");
			}

			if (openExistingApplication()) {
				SuccessReport("Create New Application",
						"New Application created successfully");
			} else {
				failureReport("Create New Application",
						"Unable to Create New Application");
			}

			// Click on ReSubmit button
			click(MIGNG_NewApplication.MIGNG_reSubmit, "ReSubmit Button");

			// Wait for the availability of Pipeline table
			waitForVisibilityOfElement(MIGNG_HomePage.MIGNG_pipeline,
					"Pipeline page");

			// verify success text
			String successText = getText(
					MIGNG_NewApplication.MIGNG_successText, "Success Message");
			if (!successText.equals("") && successText.contains(MIGNG_Validation.msgSuccessApplicationSubmitted)) {
				SuccessReport("New Application Created", "Success Message : "
						+ successText);
				String applicationNo = successText.replaceAll("[^0-9]", "");
				SuccessReport("New Application Created",
						"Application No : " + applicationNo);
			}

			logoutMIGNG();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
