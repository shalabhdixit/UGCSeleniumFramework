package com.ugc.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cigniti.utilities.Data_Provider;
import com.ugc.businessLibrary.BusinessFunctions;
import com.ugc.objectRepository.MIGNG_HomePage;
import com.ugc.objectRepository.MIGNG_NewApplication;
import com.ugc.objectRepository.MIGNG_Validation;

public class UploadDocument_MultipleDocs_ExistingApplication extends BusinessFunctions {
	@Test(dataProvider = "get_TestData")
	public void Upload_Document_Multiple_Docs_Existing_Application (
			String UploadDocument) throws Throwable {

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

			if(!UploadDocument.equals("")){
				String[] files = UploadDocument.split(";");
				for(int i=0; i<files.length;i++){
					//	call upload document method
					uploadDocument(files[i], i+1);
				}
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

	@DataProvider()
	public Object[][] get_TestData() throws Exception {
		return Data_Provider.getTableArray("UD_MultipleDocs_ExistingApp");
	}
}
